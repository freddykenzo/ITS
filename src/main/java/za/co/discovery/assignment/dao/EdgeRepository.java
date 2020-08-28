package za.co.discovery.assignment.dao;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import za.co.discovery.assignment.model.edge.Edge;

@Repository
public interface EdgeRepository extends JpaRepository<Edge, Long> {

	Set<Edge> findBySourcePlanetId(String sourceId);
	
}
