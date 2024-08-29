package com.example.capstone_3.Repository;

import com.example.capstone_3.Model.*;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
    Order findOrderById(Integer id);
    List<Order> findOrderByCustom(Customer customer);
    List<Order> findOrderByFabric(Fabric fabric);
    List<Order> findOrderByDesigner(Designer designer);
    List<Order> findOrderByMerchant(Merchant merchant);
    List<Order> findOrderByTailor(Tailor tailor);
    //made by Omar Alshehri
    @Query("SELECT o.fabric FROM Order o GROUP BY o.fabric.id ORDER BY COUNT(o.id) DESC")
    List<Fabric> findBestSellingFabric(Pageable pageable);
    //made by Omar Alshehri
    List<Order> findOrderByOrderStatus(String orderStatus);
    ///
    List<Order> findOrderByFabricId(Integer fabricId);
    List<Order> findOrderByCustomId(Integer customerId);

}
