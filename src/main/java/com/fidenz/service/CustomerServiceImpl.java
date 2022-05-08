package com.fidenz.service;

import com.fidenz.dao.CustomerRepository;
import com.fidenz.model.Customers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("customerService")
@Transactional
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public List<Customers> findById(String id) {
        return customerRepository.findAllCustomersById(id);
    }

    @Override
    public List<Customers> findAllByName(String name) {
    	return customerRepository.findAllCustomersByName(name);
    }

    @Override
    public  Map<String,List<Customers>> getCustomersGroupByZipCode() {
        Map<String,List<Customers>> groupByResult= new HashMap<>();
        List<String> zipCodes = customerRepository.findAllCustomersGroupByZipCode();
        zipCodes.stream().forEach(zipCode -> {
            List<Customers> allCustomersByZipCode = customerRepository.
                    findAllCustomersByZipCode(zipCode);
            groupByResult.put(zipCode,allCustomersByZipCode);
        });
        return groupByResult;
    }
}
