package za.co.discovery.assignment.model.edge;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import za.co.discovery.assignment.model.planet.Planet;

@Data
@Entity
@Table(name = "edge")
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "Edge", description = "An Edge in the Interstellar Transport System")
public class Edge {

	@Id
	@Column
	@NotNull(message = "Edge ID cannot be null")
	@ApiModelProperty(required = true, value = "The Id of the edge")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull(message = "Source cannot be null")
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "source")
	@ApiModelProperty(required = true, value = "The planet source")
	private Planet source;
	
	@NotNull(message = "Destination cannot be null")
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "destination")
	@ApiModelProperty(required = true, value = "The planet destination")
	private Planet destination;
	
	@Column(name = "distance")
	@ApiModelProperty(required = false, value = "The distance between 2 planets")
	private Float distance;
	
	@Column(name = "traffic_delay")
	@ApiModelProperty(required = false, value = "The Traffic delay between 2 planets")
	private Float trafficDelay;
	
	
	// Get the neighbourin planet of the edge
	public Planet getNeighbourPlanet(Planet planet) {
		
		if(this.source.equals(planet)) {
			return destination;
		}
		
		return source;
	}
}
