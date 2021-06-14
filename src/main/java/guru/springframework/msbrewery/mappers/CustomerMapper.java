package guru.springframework.msbrewery.mappers;

import org.mapstruct.Mapper;

import guru.springframework.msbrewery.domain.Customer;
import guru.springframework.msbrewery.web.model.CustomerDto;

@Mapper
public interface CustomerMapper {
	
	CustomerDto custTocustDto(Customer cust);
	Customer custDtoToCust(CustomerDto custDto);

}
