package guru.springframework.msbrewery.services;

import java.util.UUID;

import org.springframework.stereotype.Service;

import guru.springframework.msbrewery.web.model.BeerDto;
import guru.springframework.msbrewery.web.model.CustomerDto;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Override
	public CustomerDto getCustomerById(UUID id) {
		// TODO Auto-generated method stub
		return CustomerDto.builder().id(UUID.randomUUID())
				.customerName("Harry Crisp")
				.build();
	}

	@Override
	public CustomerDto saveNewCustomer(CustomerDto customerDto) {
		// TODO Auto-generated method stub
		return CustomerDto.builder()
                .id(UUID.randomUUID())
                .build();
	}

	@Override
	public void updateCustomer(UUID custid, CustomerDto customerDto) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteCustomer(UUID custid) {
		// TODO Auto-generated method stub
		
	}

}
