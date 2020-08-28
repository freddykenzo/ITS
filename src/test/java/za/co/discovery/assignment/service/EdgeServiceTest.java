package za.co.discovery.assignment.service;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import za.co.discovery.assignment.dao.EdgeRepository;
import za.co.discovery.assignment.model.response.EdgeListResponse;
import za.co.discovery.assignment.service.edge.EdgeService;
import za.co.discovery.assignment.service.edge.impl.EdgeServiceImpl;

public class EdgeServiceTest extends ServiceBaseTest {

	@Autowired
	private EdgeRepository edgeRepository;

	private EdgeService edgeService;

	@Before
	public void setup() {
		this.edgeService = new EdgeServiceImpl(edgeRepository);
	}

	@Test
	public void givenExistingPlanets_whenGetAll_thenReturnAllPlanets() {
		EdgeListResponse edgeListResponse =  edgeService.getAllEdges();
		
		assertEquals("Number of edge should match ",
                44, edgeListResponse.getEdges().size());
	}

}
