package za.co.discovery.assignment.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;

import za.co.discovery.assignment.model.edge.Edge;
import za.co.discovery.assignment.model.response.EdgeListResponse;
import za.co.discovery.assignment.model.response.ResponseCode;
import za.co.discovery.assignment.service.edge.EdgeService;

public class EdgeControllerTest extends ControllerBaseTest {
	
	private EdgeListResponse successResponse;

	@MockBean
	private EdgeService edgeService;
	
	@Before
	public void init() {
		List<Edge> edges = new ArrayList<Edge>();
		edges.add(new Edge());
		successResponse = new EdgeListResponse(ResponseCode.OK, edges);
	}
	
	@Test
    public void whenfetchingAllEdges_thenReturnStatus200() throws Exception {

        when(edgeService.getAllEdges()).thenReturn(successResponse);

        mockMvc.perform(get(EdgeController.BASE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.response.status").value("Success"))
                .andExpect(jsonPath("$.response.code").value("0"));
        
    }
}
