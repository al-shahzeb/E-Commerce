package com.example.Great_Indian_Bazaar.service.impl;

import com.example.Great_Indian_Bazaar.converter.OrderConverter;
import com.example.Great_Indian_Bazaar.dto.requestDto.BuyNowRequest;
import com.example.Great_Indian_Bazaar.dto.responseDto.BuyNowResponse;
import com.example.Great_Indian_Bazaar.dto.responseDto.OrderResponse;
import com.example.Great_Indian_Bazaar.enums.ProductStatus;
import com.example.Great_Indian_Bazaar.exception.ResourceNotFoundException;
import com.example.Great_Indian_Bazaar.model.*;
import com.example.Great_Indian_Bazaar.repository.CardRepository;
import com.example.Great_Indian_Bazaar.repository.CustomerRepository;
import com.example.Great_Indian_Bazaar.repository.OrderedRepository;
import com.example.Great_Indian_Bazaar.repository.ProductRepository;
import com.example.Great_Indian_Bazaar.service.OrderedService;
import org.hibernate.id.uuid.UuidGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class OrderedServiceImpl implements OrderedService {


    @Autowired
    OrderedRepository orderedRepository;
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    CardRepository cardRepository;
    @Autowired
    ProductRepository productRepository;
    @Override
    public Ordered placeOrder(Customer customer,Card card) throws ResourceNotFoundException {
        Cart cart = customer.getCart();

        List<Item> newList = new ArrayList<>();
        for(Item item: cart.getItems()){
            try{
                decreaseQuantity(item);
                newList.add(item);
            }catch (Exception e){
                throw new ResourceNotFoundException(e.getMessage());
            }
        }
        Ordered ordered = new Ordered();

        String masked = generateMaskedCardNumber(card.getCardNumber());

        ordered.setCardUsed(masked);
        ordered.setTotal(cart.getTotal());
        ordered.setCustomer(customer);
        ordered.setItems(newList);
        ordered.setOrderNo(String.valueOf(UUID.randomUUID()));

        customer.getOrders().add(ordered);

        return orderedRepository.save(ordered);

    }

    @Override
    public BuyNowResponse placeOrder(BuyNowRequest buyNowRequest) throws ResourceNotFoundException {
        Product product = productRepository.findById(buyNowRequest.getProductId()).get();

        if(product==null || product.getProductStatus()==ProductStatus.OUT_OF_STOCK || product.getQuantity()< buyNowRequest.getQuantity())
            throw new ResourceNotFoundException("Sorry! Product is unavailable");

        Customer customer = customerRepository.findById(buyNowRequest.getCustomerId()).get();
        Card card = cardRepository.findByCardNumber(buyNowRequest.getCardNumber());
        if(customer==null || !card.getCustomer().getContact().equals(customer.getContact()) || card.getCvv()!=buyNowRequest.getCvv())
            throw new ResourceNotFoundException("Invalid customer!!");



        String maskedCardNumber=generateMaskedCardNumber(buyNowRequest.getCardNumber());
        Ordered ordered = new Ordered();
        ordered.setTotal(product.getPrice()* buyNowRequest.getQuantity());
        ordered.setCustomer(customer);
        ordered.setOrderNo(String.valueOf(UUID.randomUUID()));
        ordered.setCardUsed(maskedCardNumber);

        Item item = new Item();
        item.setProduct(product);
        item.setName(product.getName());
        item.setPrice(product.getPrice());
        item.setQuantity(buyNowRequest.getQuantity());
        item.setOrdered(ordered);

        ordered.getItems().add(item);
        customer.getOrders().add(ordered);
        product.setQuantity(product.getQuantity()- buyNowRequest.getQuantity());
        if(product.getQuantity()==0)
            product.setProductStatus(ProductStatus.OUT_OF_STOCK);

        Ordered saved = orderedRepository.save(ordered);

        BuyNowResponse response = OrderConverter.OrderToBuyNowResponse(saved);
        response.setOrderedBy(customer.getName());
        response.setProduct(product.getName());

        return response;

    }

    @Override
    public List<OrderResponse> getListOfCustomer(int customerId) {
        List<Ordered> orders =orderedRepository.findByCustomerId(customerId);

        List<OrderResponse> responses = new ArrayList<>();

        for(Ordered ordered: orders)
            responses.add(OrderConverter.OrderToOrderResponse(ordered));

        return responses;
    }

    @Override
    public List<OrderResponse> getRecentOrders() {
        List<Ordered> orders = orderedRepository.findRecentOrders();
        List<OrderResponse> responses = new ArrayList<>();

        for(Ordered ordered: orders)
            responses.add(OrderConverter.OrderToOrderResponse(ordered));

        return responses;
    }

    @Override
    public OrderResponse deleteOrder(int id) throws ResourceNotFoundException {
        Ordered ordered = orderedRepository.findById(id).get();
        if(ordered==null)
            throw new ResourceNotFoundException("No such order");

        OrderResponse response = OrderConverter.OrderToOrderResponse(ordered);
        orderedRepository.delete(ordered);

        return response;
    }



    private void decreaseQuantity(Item item) throws ResourceNotFoundException {
        Product product = item.getProduct();
        int newQuantity = product.getQuantity()-item.getQuantity();
        if(newQuantity<0)
            throw new ResourceNotFoundException("Invalid order!!");
        product.setQuantity(newQuantity);
        if(product.getQuantity()==0)
            product.setProductStatus(ProductStatus.OUT_OF_STOCK);

    }

    /***   Not working hibernate error
    private boolean validOrder(List<Item> items) {
        int x=0;
        for(Item item:items){
            Product product = item.getProduct();
            int newQuantity = product.getQuantity()-item.getQuantity();
            if(newQuantity<0) {
                increaseQuantity(items, x);
                return false;
            }
            product.setQuantity(newQuantity);
            if(product.getQuantity()==0)
                product.setProductStatus(ProductStatus.OUT_OF_STOCK);
            x++;
        }
        return true;
    }

    private void increaseQuantity(List<Item> items, int x) {

        for(int i=0; i<x; i++) {
            Product product = items.get(i).getProduct();
            int newQuantity = product.getQuantity() + items.get(i).getQuantity();
            product.setQuantity(newQuantity);
        }
    }
    ***/

    private String generateMaskedCardNumber(String cardNumber) {
        String masked="";
        for(int i=0; i<cardNumber.length(); i++){
            int n = cardNumber.length()-4;
            if(i>=n)
                masked+=""+cardNumber.charAt(i);
            else masked+="X";
        }
        return masked;
    }
}
