package za.co.discovery.assignment.model.response;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import za.co.discovery.assignment.model.edge.Edge;

@Data
@EqualsAndHashCode(callSuper=false)
@ApiModel(value = "The list of edges response", description = "The list of edges response")
public class EdgeListResponse extends GeneralResponse {

	@ApiModelProperty(value = "List of Edge Object", position = 1)
	private List<Edge> edges;
	
	public EdgeListResponse (ResponseCode responseCode) {
		super(responseCode);
	}
	
	public EdgeListResponse (ResponseCode responseCode, List<Edge> edges) {
		super(responseCode);
		this.edges = edges;
	}
	
	public EdgeListResponse(Response response) {
		super(response);
	}
	
}
