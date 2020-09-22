package za.co.discovery.assignment.service;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;

import za.co.discovery.assignment.model.planet.Planet;
import za.co.discovery.assignment.model.response.PlanetListResponse;
import za.co.discovery.assignment.repository.EdgeRepository;
import za.co.discovery.assignment.repository.PlanetRepository;
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
	@DisplayName("Given Existing planets "
			+ "When retrieving all planet "
			+ "Then Should return 38 planets")
	public void givenExistingPlanets_whenGetAll_thenReturnAllPlanets() {
		PlanetListResponse planetListResponse =  planetService.getAllPlanets();
		
		assertEquals("Number of planets should match ",
                38, planetListResponse.getPlanets().size());
	}
	
	@Test
	@DisplayName("Given Existing planets A and B"
			+ "When calculating shortest path From A to B"
			+ "Then Should return list containing 2 Element A and B")
	public void givenPlanetAAndB_whenCalculateShortestPath_thenReturnShortestpath() {
		
		String source = "A";
		String destination = "B";
		
		PlanetListResponse planetListResponse =  planetService.getShortestPath(source, destination);
		
		assertEquals("Successful Response",
                "Success", planetListResponse.getResponse().getStatus());
		
		assertEquals("Only 2 planets from source to destination",
                2, planetListResponse.getPlanets().size());
		
		List<Planet> planets = planetListResponse.getPlanets();
		
		assertEquals("First planet should be A",
                "A", planets.get(0).getPlanetId());
		
		assertEquals("Second planet should be B",
                "B", planets.get(1).getPlanetId());
		
	}
	
	@Test
	@DisplayName("Given Existing planets A and B"
			+ "When calculating shortest path From A to B"
			+ "Then Should return list containing 2 Element B and A")
	public void givenPlanetBAndA_whenCalculateShortestPath_thenReturnShortestpath() {
		
		String source = "B";
		String destination = "A";
		
		PlanetListResponse planetListResponse =  planetService.getShortestPath(source, destination);
		
		assertEquals("Successful Response",
                "Success", planetListResponse.getResponse().getStatus());
		
		assertEquals("Only 2 planets from source to destination",
                2, planetListResponse.getPlanets().size());
		
		List<Planet> planets = planetListResponse.getPlanets();
		
		assertEquals("First planet should be A",
                "B", planets.get(0).getPlanetId());
		
		assertEquals("Second planet should be B",
                "A", planets.get(1).getPlanetId());
		
	}

	@Test
	@DisplayName("Given Existing Non existing planets Z' and Existing planet B"
			+ "When calculating shortest path From Z' to B"
			+ "Then Should return error: Planet not found")
	public void givenRandomPlanetAndPlanetB_whenCalculateShortestPath_thenReturnPlanetNotFound() {
		
		String source = "Z'";
		String destination = "B";
		
		PlanetListResponse planetListResponse =  planetService.getShortestPath(source, destination);
		
		assertEquals("Planet Not found",
                "The planet cannot be found", planetListResponse.getResponse().getMessage());
		
	}
	
	@Test
	@DisplayName("Given Existing planets A and Non Existing planet Z'"
			+ "When calculating shortest path From A to Z'"
			+ "Then Should return error: Planet not found")
	public void givenPlanetAndRandomPlanetB_whenCalculateShortestPath_thenReturnPlanetNotFound() {
		
		String source = "A";
		String destination = "Z'";
		
		PlanetListResponse planetListResponse =  planetService.getShortestPath(source, destination);
		
		assertEquals("Planet Not found",
                "The planet cannot be found", planetListResponse.getResponse().getMessage());
		
	}
	
	@Test
	@DisplayName("Given New planet Z' and Existing planet A"
			+ "When calculating shortest path From Z' to A"
			+ "Then Should return no path ")
	public void givenANewPlanetAndPlanetB_whenCalculateShortestPath_thenReturnNoPath() {
		
		Planet planet = planetRepository.save(Planet.builder().planetId("Z'").planetName("Test").build());
		String destination = "A";
		
		PlanetListResponse planetListResponse =  planetService.getShortestPath(planet.getPlanetId(), destination);
		
		assertEquals("Path Path/planet linked",
                0, planetListResponse.getPlanets().size());
		
	}

}
