package za.co.discovery.assignment.model.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "Response", description = "The response details of a request")
public class Response {
	
	@ApiModelProperty(value = "The Response Status", position = 1)
    private String status;

    @ApiModelProperty(value = "The Response Code", position = 2)
    private Integer code;

    @ApiModelProperty(value = "The Response Message", position = 3)
    private String message;
    
    @ApiModelProperty(value = "The Response Http Status code", position = 4)
    private Integer httpStatus;

    public Response(final ResponseCode responseCode) {
        this.status = responseCode.getStatus();
        this.code = responseCode.getCode();
        this.message = responseCode.getMessage();
        this.httpStatus = responseCode.getHttpStatus().value();
    }
}
