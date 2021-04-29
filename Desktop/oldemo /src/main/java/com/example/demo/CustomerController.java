package com.example.demo;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class CustomerController {
	
	@Autowired
	private CustomerService customerservice;
	
	@RequestMapping(path="/customers")
	public List<Customer> getAllCustomers()
	{
		return customerservice.findAll();
	}
	
	@RequestMapping(path="/customers/{accountnumber}")
	public Customer getCustomer(@PathVariable String accountnumber)
	{
		return customerservice.findByAccount(accountnumber);
	}
	
	@RequestMapping(method=RequestMethod.POST,value="/addcustomer")
	public void addCustomer(@RequestBody Customer customer)
	{
			customerservice.insert(customer);
	}
	@RequestMapping(method=RequestMethod.PUT,value="/customers/{accountnumber}")
	public void updateCustomer(@RequestBody Customer customer, @PathVariable String accountnumber)
	{
		customerservice.update(customer,accountnumber);
	}
	@RequestMapping(method=RequestMethod.DELETE,value="/customers/{accountnumber}")
	public void deleteCustomer( @PathVariable String accountnumber)
	{
		customerservice.deleteByAccount(accountnumber);
	}
	
	

}
