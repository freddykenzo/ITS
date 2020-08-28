package za.co.discovery.assignment.controller;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;

import za.co.discovery.assignment.model.planet.Planet;
import za.co.discovery.assignment.model.request.ShortestPathRequest;
import za.co.discovery.assignment.model.response.PlanetListResponse;
import za.co.discovery.assignment.model.response.ResponseCode;
import za.co.discovery.assignment.service.planet.PlanetService;

public class PlanetControllerTest extends ControllerBaseTest {
	
	private PlanetListResponse successResponse;

	@MockBean
	private PlanetService planetService;
	
	@Before
	public void init() {
		List<Planet> planets = new ArrayList<Planet>();
		planets.add(new Planet());
		successResponse = new PlanetListResponse(ResponseCode.OK, planets);
	}
	
	@Test
    public void givenExistingPlanets_whenfetchingAllPlanets_thenReturnStatus200() throws Exception {

        when(planetService.getAllPlanets()).thenReturn(successResponse);

        mockMvc.perform(get(PlanetController.BASE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.response.status").value("Success"))
                .andExpect(jsonPath("$.response.code").value("0"));
        
    }
	
	@Test
    public void givenExistingPlanets_whenCalculatePath_thenReturnStatus200() throws Exception {

        when(planetService.getShortestPath(any(String.class), any(String.class))).thenReturn(successResponse);

        mockMvc.perform(post(PlanetController.BASE_URL + "/path")
                .contentType(MediaType.APPLICATION_JSON)
                .content(String.valueOf(new JSONObject(new ShortestPathRequest("", ""))))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.response.status").value("Success"))
                .andExpect(jsonPath("$.response.code").value("0"));
        
    }
}
