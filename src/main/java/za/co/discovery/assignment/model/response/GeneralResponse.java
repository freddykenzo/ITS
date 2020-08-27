package za.co.discovery.assignment.model.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "The general response object", description = "The general response object")
public class GeneralResponse {

	@ApiModelProperty(value = "The Response object", position = 1)
    private Response response;
	
	public GeneralResponse(ResponseCode responseCode) {
		this.response = new Response(responseCode);
	}
	
	public GeneralResponse(String status, Integer code, String message, Integer httpStatus) {
        this.response = new Response(status, code, message, httpStatus);
    }
	
	public GeneralResponse(Response response) {
		this.response = response;
	}
}
