package za.co.discovery.assignment.service.edge.impl;

import java.util.List;
import java.util.Optional;
import java.util.PriorityQueue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;
import za.co.discovery.assignment.dao.EdgeRepository;
import za.co.discovery.assignment.dao.PlanetRepository;
import za.co.discovery.assignment.model.edge.Edge;
import za.co.discovery.assignment.model.planet.Planet;
import za.co.discovery.assignment.model.response.EdgeListResponse;
import za.co.discovery.assignment.model.response.ResponseCode;
import za.co.discovery.assignment.service.edge.EdgeService;

@Slf4j
@Service
@Transactional
public class EdgeServiceImpl implements EdgeService {

	private final EdgeRepository edgeRepository;

	private final PlanetRepository planetRepository;

	@Autowired
	public EdgeServiceImpl(final EdgeRepository edgeRepository, PlanetRepository planetRepository) {
		this.edgeRepository = edgeRepository;
		this.planetRepository = planetRepository;
	}

	public EdgeListResponse getAllEdges() {

		log.debug("Retrieving all edges >>> ");

		List<Edge> edges = edgeRepository.findAll();

		return new EdgeListResponse(ResponseCode.OK, edges);
	}

	
	//TODO: TEst this if it works and clean it
	public void calculateShortestDistances(String sourceId, String destination) {
		List<Planet> planets = planetRepository.findAll();

		Optional<Planet> planetSource = planets.stream().filter(p -> p.getPlanetId().equals(sourceId)).findFirst();

		if (planetSource.isPresent()) {
			planetSource.get().setDistanceFromSource(0F);

			PriorityQueue<Planet> priorityQueue = new PriorityQueue<>();
			priorityQueue.add(planetSource.get());
			planetSource.get().setVisited(true);

			while (!priorityQueue.isEmpty()) {
				Planet actualPlanet = priorityQueue.poll();
				for (Edge edge : edgeRepository.findBySourcePlanetId(actualPlanet.getPlanetId())) {
					Planet destinationPlanet = edge.getDestination();

					if (Boolean.FALSE.equals(destinationPlanet.getVisited())) {
						float newDistance = actualPlanet.getDistanceFromSource() + edge.getDistance();
						if (newDistance < destinationPlanet.getDistanceFromSource()) {
							priorityQueue.remove(destinationPlanet);
							destinationPlanet.setDistanceFromSource(newDistance);
							priorityQueue.add(destinationPlanet);
						}
					}
				}
				actualPlanet.setVisited(true);
			}
		}

	}
}
