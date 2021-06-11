package guru.springframework.msbrewery.services;

import java.util.UUID;

import org.springframework.stereotype.Service;

import guru.springframework.msbrewery.web.model.BeerDto;

@Service
public class BeerServiceImpl implements BeerService {

	@Override
	public BeerDto getBeerById(UUID id) {
		// TODO Auto-generated method stub
		return BeerDto.builder().id(UUID.randomUUID())
				.beerName("abc")
				.beerStyle("style1")
				.build();
	}

	@Override
	public BeerDto saveNewBeer(BeerDto beerDto) {
		// TODO Auto-generated method stub
		return BeerDto.builder()
                .id(UUID.randomUUID())
                .build();
	}

	@Override
	public void updateBeer(UUID beerId, BeerDto beerDto) {
		// TODO Auto-generated method stub
		
	}

}
