package guru.springframework.msbrewery.services;

import java.util.UUID;

import guru.springframework.msbrewery.web.model.CustomerDto;

public interface CustomerService {
	CustomerDto getCustomerById(UUID id);
}
