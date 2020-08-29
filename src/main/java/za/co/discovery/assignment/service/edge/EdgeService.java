package za.co.discovery.assignment.service.edge;

import za.co.discovery.assignment.model.response.EdgeListResponse;

public interface EdgeService {

	/**
	 * @return list of all Edges
	 */
	EdgeListResponse getAllEdges();
}
