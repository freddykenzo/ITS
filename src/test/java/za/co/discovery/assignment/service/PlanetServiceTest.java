package za.co.discovery.assignment.service;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import za.co.discovery.assignment.dao.EdgeRepository;
import za.co.discovery.assignment.dao.PlanetRepository;
import za.co.discovery.assignment.model.response.PlanetListResponse;
import za.co.discovery.assignment.service.planet.PlanetService;
import za.co.discovery.assignment.service.planet.impl.PlanetServiceImpl;

public class PlanetServiceTest extends ServiceBaseTest {

	@Autowired
	private PlanetRepository planetRepository;

	@Autowired
	private EdgeRepository edgeRepository;

	private PlanetService planetService;

	@Before
	public void setup() {
		this.planetService = new PlanetServiceImpl(planetRepository, edgeRepository);
	}

	@Test
	public void givenExistingPlanets_whenGetAll_thenReturnAllPlanets() {
		PlanetListResponse planetListResponse =  planetService.getAllPlanets();
		
		assertEquals("Number of planets should match ",
                38, planetListResponse.getPlanets().size());
	}
	
	@Test
	public void givenPlanetAAndB_whenCalculateShortestPath_thenReturnShortestpath() {
		
		String source = "A";
		String destination = "B";
		
		PlanetListResponse planetListResponse =  planetService.getShortestPath(source, destination);
		
		assertEquals("Successful Response",
                "Success", planetListResponse.getResponse().getStatus());
		
		assertEquals("Only 2 planets from source to destination",
                2, planetListResponse.getPlanets().size());
		
	}

	@Test
	public void givenRandomPlanetAndPlanetB_whenCalculateShortestPath_thenReturnPlanetNotFound() {
		
		String source = "Z'";
		String destination = "B";
		
		PlanetListResponse planetListResponse =  planetService.getShortestPath(source, destination);
		
		assertEquals("Planet Not found",
                "The planet cannot be found", planetListResponse.getResponse().getMessage());
		
	}

}
