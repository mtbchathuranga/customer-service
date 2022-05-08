package com.fidenz.service;

import java.util.List;
import java.util.Map;

import com.fidenz.model.Customers;


public interface CustomerService {

	/**
	 * Get Customer using customer id
	 * @param id
	 * @return
	 */
	List<Customers> findById(String id);

	/**
	 * Get Customers by name
	 * @param name
	 * @return
	 */
	List<Customers> findAllByName(String name);

	/**
	 * Get Customers grouped by Zip Code
	 * @return
	 */
	Map<String,List<Customers>> getCustomersGroupByZipCode();

}
