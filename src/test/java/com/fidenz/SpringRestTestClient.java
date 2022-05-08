package com.fidenz;

import com.fidenz.model.Customers;
import org.springframework.web.client.RestTemplate;

import java.util.LinkedHashMap;
import java.util.List;

public class SpringRestTestClient {

	public static final String REST_SERVICE_URI = "http://localhost:8080/CustomerService_war/api/v1/customers";
	
	/* GET */
	private static void getCustomer(){
		System.out.println("Testing Customer By ID API----------");
		RestTemplate restTemplate = new RestTemplate();
        Customers customer = restTemplate.getForObject(REST_SERVICE_URI+"/5aa252beeb3c8eb482f3eb6f", Customers.class);
        System.out.println(customer);
	}

	/* GET */
	private static void getCustomersByName(){
		System.out.println("Testing getCustomersByName API-----------");

		RestTemplate restTemplate = new RestTemplate();
		List<LinkedHashMap<String, Object>> usersMap = restTemplate.getForObject(REST_SERVICE_URI+"?name=Cain", List.class);

		if(usersMap!=null){
			for(LinkedHashMap<String, Object> map : usersMap){
				System.out.println("Customer : _id="+map.get("id")+", " +
						"Name="+map.get("name")+", " +
						"Age="+map.get("age")+", " +
						"Email="+map.get("email"));;
			}
		}else{
			System.out.println("No Customer exist----------");
		}
	}

	/* GET */
	private static void getCustomersZipCode(){
		System.out.println("Testing getCustomersZipCode API-----------");

		RestTemplate restTemplate = new RestTemplate();
		List<LinkedHashMap<String, Object>> usersMap = restTemplate.getForObject(REST_SERVICE_URI+"/zip", List.class);

		if(usersMap!=null){
			for(LinkedHashMap<String, Object> map : usersMap){
				System.out.println(map);;
			}
		}else{
			System.out.println("No Customer exist----------");
		}
	}

    public static void main(String args[]){
		getCustomer();
		getCustomersByName();
		getCustomersZipCode();
    }
}