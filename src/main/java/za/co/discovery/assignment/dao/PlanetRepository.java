package za.co.discovery.assignment.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import za.co.discovery.assignment.model.planet.Planet;

@Repository
public interface PlanetRepository extends JpaRepository<Planet, String> {

}
