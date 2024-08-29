package com.example.capstone_3.Repository;

import com.example.capstone_3.Model.Designer;
import com.example.capstone_3.Model.Merchant;
import com.example.capstone_3.Model.Rating;
import com.example.capstone_3.Model.Tailor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Integer> {

    Rating findRatingById(Integer id);

    List<Rating> findRatingByMerchant(Merchant merchant);

    List<Rating> findRatingByTailors(Tailor tailor);



    List<Rating> findRatingByDesigner(Designer designer);

}
