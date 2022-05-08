package com.fidenz.controller;

import com.fidenz.model.Customers;
import com.fidenz.service.CustomerService;
import com.fidenz.model.dto.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class CustomerController {

	@Autowired
	CustomerService customerService;

	@RequestMapping(value = "/customers/{id}", method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Customers> getCustomerById(@PathVariable("id") String id) {
		List<Customers> customers = customerService.findById(id);
		if (customers == null && customers.isEmpty()) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity(customers.get(0), HttpStatus.OK);
	}

	@RequestMapping(value = "/customers", method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Customers>> getCustomersByName(@RequestParam("name") String name) {
		List<Customers> customer = customerService.findAllByName(name);
		if (customer == null && !customer.isEmpty()) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity(customer, HttpStatus.OK);
	}

	@RequestMapping(value = "/customers/zip", method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<String,List<Customers>>> getCustomersGroupByZipCode() {
		Map<String,List<Customers>> groupByResult = customerService.getCustomersGroupByZipCode();
		if (groupByResult == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity(groupByResult, HttpStatus.OK);
	}

}
