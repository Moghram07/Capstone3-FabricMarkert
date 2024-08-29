package com.example.capstone_3.Service;
import com.example.capstone_3.Api.ApiException;
import com.example.capstone_3.DTO.DesignerInfoDTO;
import com.example.capstone_3.Model.Designer;
import com.example.capstone_3.Model.Order;
import com.example.capstone_3.Model.Rating;
import com.example.capstone_3.Repository.DesignerRepository;
import com.example.capstone_3.Repository.OrderRepository;
import com.example.capstone_3.Repository.RatingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class DesignerService {

    private final DesignerRepository designerRepository;
    private final OrderRepository orderRepository;
    private final RatingRepository ratingRepository;

    //Get Designers
    public List<Designer> getAllDesigners() {
        return designerRepository.findAll();
    }

    //Add Designer
    public void addDesigner(Designer designer) {
        designerRepository.save(designer);
    }

    //Update Designer
    public void updateDesigner(Integer id,Designer designer) {
        Designer d = designerRepository.findDesignerById(id);
        if(d==null) {
            throw new ApiException("Designer with id '" + id + "' not found");
        }
        d.setName(designer.getName());
        d.setEmail(designer.getEmail());
        d.setPhone(designer.getPhone());
        d.setPrice(designer.getPrice());
        d.setCity(designer.getCity());
        d.setStreet(designer.getStreet());
        designerRepository.save(d);
    }

    //Delete Designer
    public void deleteDesigner(Integer id) {
        Designer d = designerRepository.findDesignerById(id);
        if(d==null) {
            throw new ApiException("Designer with id '" + id + "' not found");
        }
        designerRepository.delete(d);
    }

    public void acceptOrder(Integer orderId,Integer designerId) {
        Order o = orderRepository.findOrderById(orderId);
        if (o==null){
            throw new ApiException("Order not found");
        }
        Designer designer = designerRepository.findDesignerById(designerId);
        if(designer==null) {
            throw new ApiException("Designer not found");
        }
        if (!o.getDesigner().getId().equals(designerId)){
            throw new ApiException("Designer with id '" + designerId + "' does not belong to this order");
        }
        if (!o.getOrderStatus().equals("Processing in Merchant")){
            throw new ApiException("Order status is not accept from Merchant");
        }
        o.setOrderStatus("Processing in Designer");
        orderRepository.save(o);
    }

    public void rejectOrder(Integer orderId,Integer designerId) {
        Order o = orderRepository.findOrderById(orderId);
        if (o==null){
            throw new ApiException("Order not found");
        }
        Designer designer = designerRepository.findDesignerById(designerId);
        if(designer==null) {
            throw new ApiException("Designer not found");
        }
        if (!o.getDesigner().getId().equals(designerId)){
            throw new ApiException("Designer with id '" + designerId + "' does not belong to this order");
        }
        if (!o.getOrderStatus().equals("Processing in Merchant")){
            throw new ApiException("Order status is not accept from Merchant");
        }
        o.setOrderStatus("Reject By Designer");
        orderRepository.save(o);
    }

    public Double getAverageRatingForDesigner(Integer designerId) {
        Designer designer = designerRepository.findDesignerById(designerId);
        Set<Rating> ratings = designer.getRatings();

        if (ratings.isEmpty()) {
            throw new ApiException("there are no ratings for merchant with id " + designerId);
        }

        int totalRating = 0;
        for (Rating rating : ratings) {
            totalRating += rating.getValue();
        }

        return (double) totalRating / ratings.size();
    }

    //All Ratings
    public List<Rating> getRatingsForDesigner(Integer designerId) {
        Designer designer = designerRepository.findDesignerById(designerId);
        return ratingRepository.findRatingByDesigner(designer);
    }

    public double totalRevenue(Integer designerId){
        Designer designer = designerRepository.findDesignerById(designerId);
        if (designer == null){
            throw new ApiException("Designer not found");
        }
        List<Order> orders = orderRepository.findOrderByDesigner(designer);
        double total = orders.size()*designer.getPrice();
        return total;
    }

    public List<DesignerInfoDTO> getDesignerOrderHistory(Integer designerId) {
        Designer designer = designerRepository.findDesignerById(designerId);

        List<Order> orders = orderRepository.findOrderByDesigner(designer);

        List<DesignerInfoDTO> designerInfoList = new ArrayList<>();
        for (Order order : orders) {
            DesignerInfoDTO designerInfo = new DesignerInfoDTO(
                    order.getId(),
                    order.getOrderStatus(),
                    order.getDesigner().getPrice()
            );
            designerInfoList.add(designerInfo);
        }

        return designerInfoList;
    }

    public String getTopDesignerName() {
        List<Designer> designers = designerRepository.findAll();

        String topDesignerName = null;
        double highestAverageRating = 0.0;

        for (Designer designer : designers) {
            List<Rating> ratings = ratingRepository.findRatingByDesigner(designer);

            if (!ratings.isEmpty()) {
                double sum = 0.0;
                for (Rating rating : ratings) {
                    sum += rating.getValue();
                }

                double averageRating = sum / ratings.size();

                if (averageRating > highestAverageRating) {
                    highestAverageRating = averageRating;
                    topDesignerName = designer.getName();
                }
            }
        }

        return topDesignerName + " with average rating: " + highestAverageRating;
    }




}
