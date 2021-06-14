package guru.springframework.msbrewery.domain;

import java.util.UUID;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;

import guru.springframework.msbrewery.web.model.CustomerDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Customer {
	
	@Null
	private UUID id;
	
	@NotBlank
    private String customerName;

}
