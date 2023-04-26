package com.adeskmath.backend.shop.service;

import com.adeskmath.backend.shop.entity.Customer;

import java.math.BigDecimal;
import java.util.List;

public interface CustomerService {
    Customer add(Customer customer);

    List<Customer> findByLowestRank(Integer lowestRank);

    List<Customer>findByProductMinTimes(String productName, Integer minTimes);

    List<Customer> findByExpenseRange(BigDecimal minValue, BigDecimal maxValue);

    List<Customer> findBylastName(String lastName);
//    JSONObject findByCriterias();
}