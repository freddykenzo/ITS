package za.co.discovery.assignment.service;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;

import za.co.discovery.assignment.model.response.EdgeListResponse;
import za.co.discovery.assignment.repository.EdgeRepository;
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
	@DisplayName("Given Existing Edges "
			+ "When retrieving all Edges "
			+ "Then Should return 44 Edges")
	public void givenExistingEdges_whenGetAll_thenReturnAllEdges() {
		EdgeListResponse edgeListResponse =  edgeService.getAllEdges();
		
		assertEquals("Number of edge should match ",
                44, edgeListResponse.getEdges().size());
	}

}
