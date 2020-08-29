package za.co.discovery.assignment.service.planet.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import za.co.discovery.assignment.dao.EdgeRepository;
import za.co.discovery.assignment.dao.PlanetRepository;
import za.co.discovery.assignment.model.edge.Edge;
import za.co.discovery.assignment.model.planet.Planet;
import za.co.discovery.assignment.model.response.PlanetListResponse;
import za.co.discovery.assignment.model.response.ResponseCode;
import za.co.discovery.assignment.service.planet.PlanetService;

@Service
@Slf4j
public class PlanetServiceImpl implements PlanetService {

	private final PlanetRepository planetRepository;

	private final EdgeRepository edgeRepository;

	@Autowired
	public PlanetServiceImpl(final PlanetRepository planetRepository, final EdgeRepository edgeRepository) {
		this.planetRepository = planetRepository;
		this.edgeRepository = edgeRepository;
	}

	/**
	 *@return list of pl
	 */
	@Override
	public PlanetListResponse getAllPlanets() {
		log.debug("Retrieving all Planets >>> ");
		List<Planet> planets = planetRepository.findAll();

		return new PlanetListResponse(ResponseCode.OK, planets);
	}

	@Override
	public PlanetListResponse getShortestPath(String source, String destination) {

		if (!planetRepository.existsById(source) || !planetRepository.existsById(destination)) {
			return new PlanetListResponse(ResponseCode.PLANET_NOT_FOUND);
		}
		
		List<Edge> edges = edgeRepository.findAll();

		PlanetListResponse response = new PlanetListResponse(ResponseCode.OK);
		response.setPlanets(calculateShortestDistances(source, destination, edges));
		return response;
	}
 
	/**
	 * For more input amout Dijkstra's algorithm, please visit this link
	 * https://www.youtube.com/watch?v=gdmfOwyQlcI
	 * @param source
	 * @param destination
	 * @param edges
	 * @return path from source planet to destination
	 */
	private List<Planet> calculateShortestDistances(String source, String destination, List<Edge> edges) {

		Optional<Edge> optEdgeSource = edges.stream().filter(t -> t.getSource().getPlanetId().equals(source) || t.getDestination().getPlanetId().equals(source)).findFirst();

		if (!optEdgeSource.isPresent()) {
			return Collections.emptyList();
		}
		
		//Set distance from source to 0 and visited as true for initial point
		Planet planetSource = optEdgeSource.get().getSource().getPlanetId().equals(source) ? optEdgeSource.get().getSource() : optEdgeSource.get().getDestination();
		
		planetSource.setDistanceFromSource(0F);
		planetSource.setVisited(Boolean.TRUE);

		Planet planetDestination = null;

		// Priority queue to always get the Planet with the lowest distance from the source
		PriorityQueue<Planet> priorityQueue = new PriorityQueue<>();
		priorityQueue.add(planetSource);

		while (!priorityQueue.isEmpty()) {
			Planet currentPlanet = priorityQueue.poll();

			// Check if current planet is destination planet
			if (currentPlanet.getPlanetId().equals(destination)) {
				planetDestination = currentPlanet;
			}

			// Find edges linked to the current planet
			Set<Edge> currentPlanetEdges = findPlanetEdges(edges, currentPlanet);

			for (Edge currentEdge : currentPlanetEdges) {
				Planet neighbourPlanet = currentEdge.getDestination().equals(currentPlanet) ? currentEdge.getSource() : currentEdge.getDestination();

				// If a planet has not been visited, calculate the distance to the source and only update if the value is less that the current distance to source
				if (!neighbourPlanet.isVisited()) {
					float newDistance = currentPlanet.getDistanceFromSource() + currentEdge.getDistance();
					if (newDistance < neighbourPlanet.getDistanceFromSource()) {

						priorityQueue.remove(neighbourPlanet);

						neighbourPlanet.setDistanceFromSource(newDistance);

						neighbourPlanet.setParentPlanet(currentPlanet);

						priorityQueue.add(neighbourPlanet);
					}
				}
			}
			currentPlanet.setVisited(true);
		}
		
		return buildPath(planetDestination);
	}

	
	/**
	 * Filter the provided set of edges and return a new set of edges with source planet provided
	 * @param edges
	 * @param planet
	 * @return set of edges with source planet same as requested planet
	 */
	private Set<Edge> findPlanetEdges(List<Edge> edges, Planet planet) {
		return edges.stream().filter(s -> s.getSource().equals(planet) || s.getDestination().equals(planet)).collect(Collectors.toSet());
	}

	
	/**
	 * For the specified planet, add the parent planet to a list until there is no parent planet
	 * The last planet added will be the source of the path
	 * Reverse the list to have the path from the source planet to the destination
	 * @param destinationPlanet
	 * @return  
	 */
	private List<Planet> buildPath(Planet destinationPlanet) {
		log.debug("building path >>> ");
		
		List<Planet> path = new ArrayList<>();

		Planet planet = destinationPlanet;

		do {
			path.add(planet);
			planet = planet.getParentPlanet();
		} while (planet != null);

		Collections.reverse(path);

		return path;
	}
}
