package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Date;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.example.demo.entities.Customer;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.service.CustomerService;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
@SpringBootTest
public class CustomerApplicationTests {

	@Test
	void contextLoads() {
	}

	@Autowired
	private CustomerService service;

	@MockBean
	private CustomerRepository repository;

	@Test
	public void getAllCustomerTest() {
		when(repository.findAll()).thenReturn(Stream.of(
				new Customer(1l, "Raviraj12345", "jadya", "Raviraj@gmail", "Bangalore", "Bangalore", "india",
						"7890110025", new Date(), new Date()),
				new Customer(2l, "Raviraj1234", "jadya", "Raviraj@gmail", "Bangalore", "Bangalore", "india",
						"7890110025", new Date(), new Date()))
				.collect(Collectors.toList()));
		assertEquals(2, service.getAllCustomer().size());
	}

	@Test
	public void getCustomerByIDTest() {
		Long id = (long) 1;
		Optional<Customer> cust = Optional.ofNullable(new Customer(1l, "Raviraj1234", "jadya", "Raviraj@gmail",
				"Bangalore", "Bangalore", "india", "7890110025", new Date(), new Date()));
		when(repository.findById(id)).thenReturn(cust);

		assertEquals(1, service.getCustomerByID(id).get().getId());
	}

	@Test
	public void saveTest() {
		Customer cust = new Customer(1l, "Raviraj1234", "jadya", "Raviraj@gmail", "Bangalore", "Bangalore", "india",
				"7890110025", new Date(), new Date());
		when(repository.save(cust)).thenReturn(cust);
		assertEquals(cust, service.save(cust));
	}

	@Test
	public void deleteCustomerTest() {
		Long id = (long) 1;
		Customer customer = new Customer(1l, "Raviraj1234", "jadya", "Raviraj@gmail", "Bangalore", "Bangalore", "india",
				"7890110025", new Date(), new Date());
		service.deleteByEntity(id);
		verify(repository, times(0)).delete(customer);
	}

	@Test
	public void updateTest() {
		Customer cust = new Customer(1l, "Raviraj1234", "jadya", "Raviraj@gmail", "Bangalore", "Bangalore", "india",
				"7890110025", new Date(), new Date());
		when(repository.save(cust)).thenReturn(cust);

		assertEquals(cust, service.update(cust));
	}
}
