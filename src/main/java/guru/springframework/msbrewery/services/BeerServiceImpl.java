package guru.springframework.msbrewery.services;

import java.util.UUID;

import org.springframework.stereotype.Service;

import guru.springframework.msbrewery.web.model.BeerDto;

@Service
public class BeerServiceImpl implements BeerService {

	@Override
	public BeerDto getBeerById(UUID id) {
		// TODO Auto-generated method stub
		return BeerDto.builder().id(id)
				.beerName("abc")
				.beerStyle("style1")
				.build();
	}

}
