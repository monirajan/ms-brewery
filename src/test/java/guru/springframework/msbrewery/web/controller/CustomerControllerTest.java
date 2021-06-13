package guru.springframework.msbrewery.web.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import guru.springframework.msbrewery.services.CustomerService;
import guru.springframework.msbrewery.web.model.BeerDto;
import guru.springframework.msbrewery.web.model.CustomerDto;

class CustomerControllerTest {

	@Mock
	CustomerService customerService;
	
	MockMvc mockMvc;
	CustomerController customerController;
	ObjectMapper objectMapper;
	CustomerDto validCust;
	
	@BeforeEach
	public void setup() throws Exception{
        
		MockitoAnnotations.initMocks(this);
		customerController = new CustomerController(customerService);
		mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();
		
		objectMapper = new ObjectMapper();
		validCust = CustomerDto.builder().id(UUID.randomUUID())
				.customerName("Rachel")
				.build();
	}
	
	@Test
	public void getCustomer() throws Exception{
		
		when(customerService.getCustomerById(any(UUID.class))).thenReturn(validCust);
		 mockMvc.perform(get("/api/v1/customer/" + validCust.getId().toString()).accept(MediaType.APPLICATION_JSON))
         .andExpect(status().isOk())
         .andExpect(content().contentType(MediaType.APPLICATION_JSON))
         .andExpect(jsonPath("$.id", is(validCust.getId().toString())))
         .andExpect(jsonPath("$.customerName", is("Rachel")));
	}
	
	@Test
	public void handlePost() throws Exception {
		
		CustomerDto customerDto = validCust;
		customerDto.setId(null);
		CustomerDto savedDto = CustomerDto.builder().id(UUID.randomUUID())
				.customerName("Ross")
				.build();
		String custDtoJson = objectMapper.writeValueAsString(customerDto);
		
		when(customerService.saveNewCustomer(any())).thenReturn(savedDto);
		
		mockMvc.perform(post("/api/v1/customer/")
				.contentType(MediaType.APPLICATION_JSON)
				.content(custDtoJson))
				.andExpect(status().isCreated());
	}
	
	@Test
    public void handleUpdate() throws Exception {
        //given
        CustomerDto customerDto = validCust;
        customerDto.setId(null);
        String custDtoJson = objectMapper.writeValueAsString(customerDto);

        //when
        mockMvc.perform(put("/api/v1/customer/" + UUID.randomUUID())
                .contentType(MediaType.APPLICATION_JSON)
                .content(custDtoJson))
                .andExpect(status().isNoContent());

        verify(customerService,times(1)).updateCustomer(any(), any());

    }
	
	@Test
	public void handleDelete() throws Exception {
		
		mockMvc.perform(delete("/api/v1/customer/" + validCust.getId()))
				.andExpect(status().isNoContent());
		
		verify(customerService,times(1)).deleteCustomer(any());
				
	}

}
