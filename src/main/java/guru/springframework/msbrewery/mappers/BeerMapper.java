package guru.springframework.msbrewery.mappers;

import org.mapstruct.Mapper;

import guru.springframework.msbrewery.domain.Beer;
import guru.springframework.msbrewery.web.model.BeerDto;

@Mapper(uses = {DateMapper.class})
public interface BeerMapper {
	
	Beer beerDtoToBeer(BeerDto beerDto);
	BeerDto beerToBeerDto(Beer beer);

}
