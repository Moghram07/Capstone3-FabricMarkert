package com.example.capstone_3.Service;

import com.example.capstone_3.Api.ApiException;
import com.example.capstone_3.Model.Designer;
import com.example.capstone_3.Model.Merchant;
import com.example.capstone_3.Model.Order;
import com.example.capstone_3.Model.Rating;
import com.example.capstone_3.Repository.MerchantRepository;
import com.example.capstone_3.Repository.OrderRepository;
import com.example.capstone_3.Repository.RatingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MerchantService {
    //All made by Omar Alshehri
    private final MerchantRepository merchantRepository;
    private final OrderRepository orderRepository;
    private final RatingRepository ratingRepository;

    public List<Merchant> getMerchant() {
        return merchantRepository.findAll();
    }

    public void addMerchant(Merchant merchant) {
        merchantRepository.save(merchant);
    }

    public void updateMerchant(Integer id, Merchant merchant) {
        Merchant m = merchantRepository.findMerchantById(id);
        if(m == null) {
            throw new ApiException("Customer with id " + id + " not found");
        }
        m.setEmail(merchant.getEmail());
        m.setPhone(merchant.getPhone());
        m.setOwnerName(merchant.getOwnerName());
        m.setStoreName(m.getStoreName());
        m.setCity(merchant.getCity());
        m.setStreet(merchant.getStreet());
        merchantRepository.save(m);
    }
    //All made by Omar Alshehri
    public void deleteMerchant(Integer id) {
        Merchant m = merchantRepository.findMerchantById(id);
        if ( m == null) {
            throw new ApiException("Customer with id " + id + " not found");
        }
        merchantRepository.delete(m);
    }

    public void acceptOrder(Integer orderId,Integer merchantId) {
        Order o = orderRepository.findOrderById(orderId);
        if (o==null){
            throw new ApiException("Order not found");
        }
        Merchant merchant = merchantRepository.findMerchantById(merchantId);
        if(merchant==null) {
            throw new ApiException("Merchant not found");
        }
        if (!o.getMerchant().getId().equals(merchantId)){
            throw new ApiException("Merchant with id '" + merchantId + "' does not belong to this order");
        }
        if (!o.getOrderStatus().equals("Pending")){
            throw new ApiException("Order status is not Pending");
        }
        o.setOrderStatus("Processing in Merchant");
        orderRepository.save(o);
    }

    public void rejectOrder(Integer orderId,Integer merchantId) {
        Order o = orderRepository.findOrderById(orderId);
        if (o==null){
            throw new ApiException("Order not found");
        }
        Merchant merchant = merchantRepository.findMerchantById(merchantId);
        if(merchant==null) {
            throw new ApiException("Merchant not found");
        }
        if (!o.getMerchant().getId().equals(merchantId)){
            throw new ApiException("Merchant with id '" + merchantId + "' does not belong to this order");
        }
        if (!o.getOrderStatus().equals("Pending")){
            throw new ApiException("Order status is not Pending");
        }
        o.setOrderStatus("Reject By Merchant");
        orderRepository.save(o);
    }
    public Merchant getMerchantById(Integer id) {
        return merchantRepository.findMerchantById(id);
    }

    public Merchant getMerchantByOwnerName(String ownerName) {
        return merchantRepository.findByOwnerName(ownerName);
    }

    public Merchant getMerchantByEmail(String email) {
        return merchantRepository.findByEmail(email);
    }

    public Merchant getMerchantByPhone(String phone) {
        return merchantRepository.findByPhone(phone);
    }

    //Average Rating
    public Double getAverageRatingForMerchant(Integer merchantId) {
        Merchant merchant = merchantRepository.findMerchantById(merchantId);
        List<Rating> ratings = merchant.getRatings();

        if (ratings.isEmpty()) {
            throw new ApiException("there are no ratings for merchant with id " + merchantId);
        }

        double totalRating = 0;
        for (Rating rating : ratings) {
            totalRating += rating.getValue();
        }

        return (double) totalRating / ratings.size();
    }

    //All Ratings
    public List<Rating> getRatingsForMerchant(Integer merchantId) {
        Merchant merchant = merchantRepository.findMerchantById(merchantId);
        return ratingRepository.findRatingByMerchant(merchant);
    }

    public double totalRevenue(Integer merchantId) {
        Merchant merchant = merchantRepository.findMerchantById(merchantId);
        if (merchant == null) {
            throw new ApiException("Merchant not found");
        }
        List<Order> orders = orderRepository.findOrderByMerchant(merchant);
        double totalRevenue = 0;
        for (Order order : orders) {
            totalRevenue = totalRevenue+order.getFabric().getPrice();
        }
        return totalRevenue;
    }


}