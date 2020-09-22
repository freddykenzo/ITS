package za.co.discovery.assignment.service.planet;

import za.co.discovery.assignment.model.response.PlanetListResponse;

public interface PlanetService {

	/**
	 * @return list of all planets
	 */
	PlanetListResponse getAllPlanets();
	
	/**
	 * 
	 * @param planetSource
	 * @param planetDestination
	 * @return path from planetSource to planetDestination
	 */
	PlanetListResponse getShortestPath(String planetSource, String planetDestination);
}
