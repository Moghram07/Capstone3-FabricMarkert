package com.example.capstone_3.Repository;

import com.example.capstone_3.Model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
//All made by Omar Alshehri
@Repository
public interface StockRepository extends JpaRepository<Stock, Integer> {

    Stock findStockById(Integer id);

    Stock findByFabricId(Integer fabricId);

    Stock findByMerchantId(Integer merchantId);

    Stock findStockByFabricIdAndMerchantId(Integer fabricId, Integer merchantId);

    Stock findByQuantity(Integer quantity);

    @Query("select s from Stock s where s.fabric.id = :id")
    Stock findStockByFabricId(Integer id);

    @Query("select s from Stock s where s.merchant.id = :id")
    Stock findStockByMerchantId(Integer id);

    List<Stock> findByQuantityGreaterThanEqual(Integer quantity);

}