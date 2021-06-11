package guru.springframework.msbrewery.web.controller;

import java.util.UUID;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
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
	
	@PostMapping
	public ResponseEntity handlePostCust(@RequestBody CustomerDto customerDto)
	{
		CustomerDto savedDto = customerService.saveNewCustomer(customerDto);
		HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "/api/v1/customer/" + savedDto.getId().toString());
        return new ResponseEntity(headers, HttpStatus.CREATED);
	}
	
	@PutMapping("/{custid}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void handleUpdateCust(@PathVariable("custid") UUID custid, @RequestBody CustomerDto customerDto)
	{
		customerService.updateCustomer(custid, customerDto);
	}
	
	@DeleteMapping("/{custid}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void handleDeleteCust(@PathVariable("custid") UUID custid)
	{
		customerService.deleteCustomer(custid);
	}
}
