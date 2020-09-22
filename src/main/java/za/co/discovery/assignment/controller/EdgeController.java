package za.co.discovery.assignment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import za.co.discovery.assignment.model.response.EdgeListResponse;
import za.co.discovery.assignment.service.edge.EdgeService;

@Slf4j
@RestController
@RequestMapping(EdgeController.BASE_URL)
@Api(tags = "Edge API", value = "Edge API")
public class EdgeController {
	
	public static final String BASE_URL = "/edge";
	
	private final EdgeService edgeService;
	
	@Autowired
	public EdgeController(final EdgeService edgeService) {
		this.edgeService = edgeService;
	}

	@ApiOperation(httpMethod = "GET", value = "Get All Edges", response = EdgeListResponse.class)
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
	@ApiResponses(value = { 
			@io.swagger.annotations.ApiResponse(code = 0, message = "Success"),
	})
    public ResponseEntity<EdgeListResponse> getAllEdges() {
        log.debug("Request to get All edges");
        
        EdgeListResponse response = edgeService.getAllEdges();
       
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getResponse().getHttpStatus()));
    }
}
