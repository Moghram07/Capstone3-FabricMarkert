package com.example.capstone_3.Service;

import com.example.capstone_3.Api.ApiException;
import com.example.capstone_3.Model.Merchant;
import com.example.capstone_3.Model.Rating;
import com.example.capstone_3.Model.Designer;
import com.example.capstone_3.Model.Tailor;
import com.example.capstone_3.Repository.DesignerRepository;
import com.example.capstone_3.Repository.MerchantRepository;
import com.example.capstone_3.Repository.RatingRepository;
import com.example.capstone_3.Repository.TailorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RatingService {

    private final RatingRepository ratingRepository;
    private final DesignerRepository designerRepository;
    private final MerchantRepository merchantRepository;
    private final TailorRepository tailorRepository;

    //Get Ratings
    public List<Rating> getAllRatings() {
        return ratingRepository.findAll();
    }

    //Add Rating
    public void addRating(Rating rating) {
        ratingRepository.save(rating);
    }

    //Update Rating
    public void updateRating(Integer id , Rating rating) {
        Rating r = ratingRepository.findRatingById(id);
        if(r == null) {
            throw new ApiException("Rating with id '" + id + "' not found");
        }
        r.setValue(rating.getValue());
        r.setReview(rating.getReview());
        ratingRepository.save(r);
    }

    //Delete Rating
    public void deleteRating(Integer id) {
        Rating r = ratingRepository.findRatingById(id);
        if(r == null) {
            throw new ApiException("Rating with id '" + id + "' not found");
        }
        ratingRepository.delete(r);
    }

    // Save a new rating for a merchant
    public Rating rateMerchant(Integer merchantId,Rating rating) {
        Merchant merchant = merchantRepository.findMerchantById(merchantId);
        if(merchant == null) {
            throw new ApiException("Merchant with id '" + merchantId + "' not found");
        }
        Rating r = new Rating();
        r.setMerchant(merchant);
        if(rating.getValue()>5){
            throw new ApiException("Rating value must be from 1-5 ");
        }else{
            r.setValue(rating.getValue());
        }
        r.setReview(rating.getReview());
        merchant.getRatings().add(r);
        return ratingRepository.save(r);
    }

    // Save a new rating for a Tailor
    public Rating rateTailor(Integer tailorId,Rating rating) {
        Tailor tailor = tailorRepository.findTailorById(tailorId);
        if(tailor == null) {
            throw new ApiException("Tailor with id '" + tailorId + "' not found");
        }
        Rating r = new Rating();
        r.setTailors(tailor);
        if(rating.getValue()>5){
            throw new ApiException("Rating value must be from 1-5 ");
        }else{
            r.setValue(rating.getValue());
        }
        r.setReview(rating.getReview());
        tailor.getRatings().add(r);
        return ratingRepository.save(r);
    }

    // Save a new rating for a Tailor
    public Rating rateDesigner(Integer designerId,Rating rating) {
        Designer designer = designerRepository.findDesignerById(designerId);
        if(designer == null) {
            throw new ApiException("Designer with id '" + designerId + "' not found");
        }
        Rating r = new Rating();
        r.setDesigner(designer);
        if(rating.getValue()>5){
            throw new ApiException("Rating value must be from 1-5 ");
        }else{
            r.setValue(rating.getValue());
        }

        r.setReview(rating.getReview());
        designer.getRatings().add(r);
        return ratingRepository.save(r);
    }
}
