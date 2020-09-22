package za.co.discovery.assignment.controller;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import za.co.discovery.assignment.model.request.ShortestPathRequest;
import za.co.discovery.assignment.model.response.PlanetListResponse;
import za.co.discovery.assignment.service.planet.PlanetService;

@Slf4j
@RestController
@RequestMapping(PlanetController.BASE_URL)
@Api(tags = "Planet API", value = "Planet API")
public class PlanetController {
	
	public static final String BASE_URL = "/planet";
	
	private final PlanetService planetService;
	
	@Autowired
	public PlanetController(final PlanetService planetService) {
		this.planetService = planetService;
	}

	@ApiOperation(httpMethod = "GET", value = "Get All Planets", response = PlanetListResponse.class)
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
	@ApiResponses(value = { 
			@io.swagger.annotations.ApiResponse(code = 0, message = "Success"),
	})
    public ResponseEntity<PlanetListResponse> getAllEdges() {
        log.debug("Request to get All edges");
        
        PlanetListResponse response = planetService.getAllPlanets();
       
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getResponse().getHttpStatus()));
    }
	
	@ApiOperation(httpMethod = "POST", value = "Calculate shortest Path", response = PlanetListResponse.class)
    @PostMapping("/path")
    @ResponseStatus(HttpStatus.OK)
	@ApiResponses(value = { 
			@io.swagger.annotations.ApiResponse(code = 0, message = "Success"),
			@io.swagger.annotations.ApiResponse(code = 404, message = "The planet cannot be found"),
	})
    public ResponseEntity<PlanetListResponse> calculateShortestPath(@NotNull(message = "Request body is required") @RequestBody ShortestPathRequest request ) {
        log.debug("Getting shortest path");
        
        PlanetListResponse response = planetService.getShortestPath(request.getSource(), request.getDestination());
       
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getResponse().getHttpStatus()));
    }
}
