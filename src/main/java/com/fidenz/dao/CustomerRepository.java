package com.fidenz.dao;


import com.fidenz.model.Customers;

import java.util.List;

/**
 * @author Kalana on May, 2018
 */
public interface CustomerRepository {

    /**
     *
     * @param id
     * @return
     */
    List<Customers> findAllCustomersById(String id);

    /**
     *
     * @param name
     * @return
     */
    List<Customers> findAllCustomersByName(String name);

    /**
     *
     *
     * @return
     */
    List<String> findAllCustomersGroupByZipCode();


    /**
     *
     * @param zipCode
     * @return
     */
    List<Customers> findAllCustomersByZipCode(String zipCode);
}
