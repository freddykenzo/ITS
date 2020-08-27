package za.co.discovery.assignment.service.edge.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import za.co.discovery.assignment.dao.EdgeRepository;
import za.co.discovery.assignment.model.edge.Edge;
import za.co.discovery.assignment.model.response.EdgeListResponse;
import za.co.discovery.assignment.model.response.ResponseCode;
import za.co.discovery.assignment.service.edge.EdgeService;

@Slf4j
@Service
public class EdgeServiceImpl implements EdgeService {
	
	private final EdgeRepository edgeRepository;
	
	@Autowired
	public EdgeServiceImpl(final EdgeRepository edgeRepository) {
		this.edgeRepository = edgeRepository;
	}

	public EdgeListResponse getAllEdges() {
		
		log.debug("Retrieving all edges >>> ");
		
		List<Edge> edges = edgeRepository.findAll();
		
		return new EdgeListResponse(ResponseCode.OK, edges);
	}
}
