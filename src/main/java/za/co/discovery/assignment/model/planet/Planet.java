package za.co.discovery.assignment.model.planet;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "planet")
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "Planet", description = "A planet in the Interstellar Transport System")
public class Planet {
	
	@Id
	@Column(name = "planet_id")
	@NotNull(message = "Planet ID cannot be null")
	@ApiModelProperty(value = "The ID of the planet", required = true)
	private String planetId;

	@ApiModelProperty(required = true, value = "The name of the planet")
	@NotNull(message = "Planet Name cannot be null")
	@Column(name = "planet_name")
	private String planetName;

}
