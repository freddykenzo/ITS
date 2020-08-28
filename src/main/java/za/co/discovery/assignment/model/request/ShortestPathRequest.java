package za.co.discovery.assignment.model.request;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "ShortestPathRequest", description = "The shortest path request payload")
public class ShortestPathRequest {
	
	@NotNull(message = "destination is required")
	@ApiModelProperty(value = "Starting point", required = true, position = 1)
    private String source;

	@NotNull(message = "destination is required")
    @ApiModelProperty(value = "Destination point",required = true, position = 2)
    private String destination;

}
