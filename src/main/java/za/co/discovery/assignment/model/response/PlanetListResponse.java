package za.co.discovery.assignment.model.response;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import za.co.discovery.assignment.model.planet.Planet;

@Data
@EqualsAndHashCode(callSuper=false)
@ApiModel(value = "The list of planets response", description = "The list of planets response")
public class PlanetListResponse extends GeneralResponse {

	@ApiModelProperty(value = "List of Planet Object", position = 1)
	private List<Planet> planets;
	
	public PlanetListResponse (ResponseCode responseCode) {
		super(responseCode);
	}
	
	public PlanetListResponse (ResponseCode responseCode, List<Planet> planets) {
		super(responseCode);
		this.planets = planets;
	}
	
	public PlanetListResponse(Response response) {
		super(response);
	}
	
}
