package guru.springframework.msbrewery.web.controller;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import guru.springframework.msbrewery.services.CustomerService;
import guru.springframework.msbrewery.web.model.CustomerDto;

@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController {

	private final CustomerService customerService;
	
	public CustomerController(CustomerService customerService) {
		this.customerService = customerService;
	}
	
	@GetMapping("/{custid}")
	public ResponseEntity<CustomerDto> getCustomer(@PathVariable("custid") UUID custid)
	{
		return new ResponseEntity<>(customerService.getCustomerById(custid), HttpStatus.OK);
	}
}
