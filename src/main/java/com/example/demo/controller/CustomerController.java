package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.Customer;
import com.example.demo.service.CustomerService;

@RequestMapping("/customer")
@RestController
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@RequestMapping(value = "/{ID}", method = RequestMethod.GET)
	public Optional<Customer> getCustomerByID(@PathVariable("ID") Long ID) {
		return customerService.getCustomerByID(ID);
	}

	@RequestMapping(method = RequestMethod.GET)
	public List<Customer> getAllCustomer() {
		return customerService.getAllCustomer();
	}

	@RequestMapping(method = RequestMethod.POST)
	public Customer save(@Valid @RequestBody Customer customer) {
		return customerService.save(customer);
	}

	@RequestMapping(method = RequestMethod.PUT)
	public Customer update(@RequestBody Customer customer) {
		return customerService.update(customer);
	}

	@RequestMapping(value = "/{ID}", method = RequestMethod.DELETE)
	public String delete(@PathVariable("ID") Long ID) {
		return customerService.deleteByEntity(ID);
	}

}
