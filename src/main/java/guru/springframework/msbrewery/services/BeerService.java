package guru.springframework.msbrewery.services;

import java.util.UUID;

import guru.springframework.msbrewery.web.model.BeerDto;

public interface BeerService {

	BeerDto getBeerById(UUID id);
	BeerDto saveNewBeer(BeerDto beerDto);
	void updateBeer(UUID beerId, BeerDto beerDto);
}
