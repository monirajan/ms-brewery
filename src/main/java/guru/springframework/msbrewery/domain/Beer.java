package guru.springframework.msbrewery.domain;

import java.sql.Timestamp;
import java.util.UUID;

import guru.springframework.msbrewery.web.model.BeerDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Beer {
	
	private UUID id;
	private String beerName;
	private String beerStyle;
	private Long upc;
	
    private Timestamp createdDate;
    private Timestamp lastUpdatedDate;
}
