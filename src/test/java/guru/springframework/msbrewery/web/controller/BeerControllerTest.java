package guru.springframework.msbrewery.web.controller;

import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.hamcrest.CoreMatchers.is;
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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import guru.springframework.msbrewery.services.BeerService;
import guru.springframework.msbrewery.web.model.BeerDto;

public class BeerControllerTest {
	
	@Mock
	BeerService beerService;
	
	MockMvc mockMvc;
	BeerController beerController;
	ObjectMapper objectMapper;
	BeerDto validBeer;
	
	@BeforeEach
	public void setup() throws Exception{
        
		MockitoAnnotations.initMocks(this);
		beerController = new BeerController(beerService);
		mockMvc = MockMvcBuilders.standaloneSetup(beerController).build();
		
		objectMapper = new ObjectMapper();
		validBeer = BeerDto.builder().id(UUID.randomUUID())
				.beerName("Beer1")
				.beerStyle("style1")
				.upc(123L)
				.build();
	}
	
	@Test
	public void getBeer() throws Exception{
		
		when(beerService.getBeerById(any(UUID.class))).thenReturn(validBeer);
		 mockMvc.perform(get("/api/v1/beer/" + validBeer.getId().toString()).accept(MediaType.APPLICATION_JSON))
         .andExpect(status().isOk())
         .andExpect(content().contentType(MediaType.APPLICATION_JSON))
         .andExpect(jsonPath("$.id", is(validBeer.getId().toString())))
         .andExpect(jsonPath("$.beerName", is("Beer1")));
	}
	
	@Test
	public void handlePost() throws Exception {
		
		BeerDto beerDto = validBeer;
		beerDto.setId(null);
		BeerDto savedDto = BeerDto.builder().id(UUID.randomUUID())
				.beerName("New Beer")
				.beerStyle("style2")
				.build();
		String beerDtoJson = objectMapper.writeValueAsString(beerDto);
		
		when(beerService.saveNewBeer(any())).thenReturn(savedDto);
		
		mockMvc.perform(post("/api/v1/beer/")
				.contentType(MediaType.APPLICATION_JSON)
				.content(beerDtoJson))
				.andExpect(status().isCreated());
	}
	
	@Test
    public void handleUpdate() throws Exception {
        //given
        BeerDto beerDto = validBeer;
        beerDto.setId(null);
        String beerDtoJson = objectMapper.writeValueAsString(beerDto);

        //when
        mockMvc.perform(put("/api/v1/beer/" + UUID.randomUUID())
                .contentType(MediaType.APPLICATION_JSON)
                .content(beerDtoJson))
                .andExpect(status().isNoContent());

        verify(beerService,times(1)).updateBeer(any(), any());

    }
	
	@Test
	public void handleDelete() throws Exception {
		
		mockMvc.perform(delete("/api/v1/beer/" + validBeer.getId()))
				.andExpect(status().isNoContent());
		
		verify(beerService,times(1)).deleteById(any());
				
	}
}
