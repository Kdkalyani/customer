package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Customer;
import com.example.demo.error.RecordNotFoundException;
import com.example.demo.repository.CustomerRepository;

@Service
public class CustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	public List<Customer> getAllCustomer() {
		Iterable<Customer> list = customerRepository.findAll();
		return (List<Customer>) list;
	}

	public Optional<Customer> getCustomerByID(Long ID) {
		Optional<Customer> record = customerRepository.findById(ID);
		if (record.isPresent()) {
			return record;
		}
		throw new RecordNotFoundException("Record not found");
	}

	public Customer save(Customer customer) {
		Customer entityCreated = customerRepository.save(customer);
		return entityCreated;

	}

	public Customer update(Customer customer) {
		Optional<Customer> object = customerRepository.findById(customer.getId());
		if (object.isPresent()) {
			Customer cust = object.get();
			cust.setAddress(customer.getAddress());
			cust.setLastName(customer.getLastName());
			Customer cust1 = customerRepository.save(cust);
			return cust1;
		} else {
			return null;
		}

	}

	public String deleteByEntity(Long ID) {
		customerRepository.deleteById(ID);
		return "Record successfully deleted";
	}

}
