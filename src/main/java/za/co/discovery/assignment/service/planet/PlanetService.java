package za.co.discovery.assignment.service.planet;

import za.co.discovery.assignment.model.response.PlanetListResponse;

public interface PlanetService {

	/**
	 * @return list of all planets
	 */
	public PlanetListResponse getAllPlanets();
	
	/**
	 * 
	 * @param planetSource
	 * @param planetDestination
	 * @return path from planetSource to planetDestination
	 */
	public PlanetListResponse getShortestPath(String planetSource, String planetDestination);
}
