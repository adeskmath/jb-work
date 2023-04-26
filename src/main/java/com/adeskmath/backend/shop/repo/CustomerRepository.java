package com.adeskmath.backend.shop.repo;

import com.adeskmath.backend.shop.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    List<Customer> findByLastName(String lastName);

    @Query("SELECT c FROM Customer c " +
            "INNER JOIN Purchasing p ON c = p.customer " +
            "INNER JOIN Product prod ON p.product = prod " +
            "GROUP BY c " +
            "HAVING SUM(prod.price) between :minValue and :maxValue")
    List<Customer> findByExpenseRange(@Param("minValue") BigDecimal minValue,
                                      @Param("maxValue") BigDecimal maxValue);

    @Query("SELECT c FROM Customer c " +
            "INNER JOIN Purchasing p ON c = p.customer " +
            "INNER JOIN Product prod ON p.product = prod " +
            "WHERE prod.name = :productName " +
            "GROUP BY c " +
            "HAVING COUNT(prod) >= :minTimes")
    List<Customer> findByProductMinTimes(@Param("productName") String productName,
                                         @Param("minTimes") Integer minTimes);

    @Query("SELECT c FROM Customer c " +
            "INNER JOIN Purchasing p ON c = p.customer " +
            "INNER JOIN Product prod ON p.product = prod " +
            "GROUP BY c " +
            "ORDER BY COUNT(prod) DESC " +
            "LIMIT :lowestRank")
    List<Customer> findByLowestRank(@Param("lowestRank") Integer lowestRank);
}