package za.co.discovery.assignment.model.planet;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Builder
@Entity
@Table(name = "planet")
@NoArgsConstructor
@EqualsAndHashCode(exclude = { "distanceFromSource", "visited" })
@AllArgsConstructor
@ApiModel(value = "Planet", description = "A planet in the Interstellar Transport System")
public class Planet implements Comparable<Planet> {

	@Id
	@Column(name = "planet_id")
	@NotNull(message = "Planet ID cannot be null")
	@ApiModelProperty(value = "The ID of the planet", required = true)
	private String planetId;

	@ApiModelProperty(required = true, value = "The name of the planet")
	@NotNull(message = "Planet Name cannot be null")
	@Column(name = "planet_name")
	private String planetName;

	@Transient
	@Builder.Default
	@ApiModelProperty(required = false, value = "Distance from the source Planet")
	@NotNull(message = "Distance from the source Planet can not be null")
	@JsonIgnore
	private Float distanceFromSource = Float.MAX_VALUE;

	@ApiModelProperty(required = false, value = "Flag stating if a planed has been visited yet.")
	@Transient
	@JsonIgnore
	private boolean visited;

	@Transient
	@JsonIgnore
	@ApiModelProperty(required = false, value = "Direct parent planet in the path")
	private Planet parentPlanet;

	@Override
	public int compareTo(Planet planet) {
		return Double.compare(this.distanceFromSource, planet.distanceFromSource);
	}

}
