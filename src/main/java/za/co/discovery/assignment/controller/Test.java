package za.co.discovery.assignment.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import za.co.discovery.assignment.model.planet.Planet;

@Slf4j
@RestController
@RequestMapping(value = "/test")
public class Test {

	@ApiOperation(httpMethod = "GET", value = "Erase All trades", response = Planet.class)
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Planet> deleteAllTrades() {
        log.debug("Request to erase all trades");
        
       
        return new ResponseEntity<>(new Planet("", ""), HttpStatus.OK);
    }
}
