package guru.springframework.msbrewery.services;

import java.util.UUID;

import org.springframework.stereotype.Service;

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

}
