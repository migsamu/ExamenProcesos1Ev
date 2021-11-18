package org.iesfm.rest.controller;

import org.iesfm.rest.Flight;
import org.iesfm.rest.dao.FlightDAO;
import org.iesfm.rest.exceptions.FlightNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class FlightController {

    private FlightDAO flightDAO;

    public FlightController(FlightDAO flightDAO) {
        this.flightDAO = flightDAO;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/flights")
    public List<Flight> list(@RequestParam(name = "origin", required = false) String origin) {
        if (origin == null) {
            return flightDAO.list();
        } else {
            return flightDAO.list(origin);
        }
    }

    @RequestMapping(method = RequestMethod.GET, path = "/flights/{flightNumber}")
    public Flight listFlight(@PathVariable("flightNumber") String flightNumber) {
        try {
            Flight flight = flightDAO.getFlight(flightNumber);
            return flight;
        } catch (FlightNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "El vuelo no existe");
        }
    }

    @RequestMapping(method = RequestMethod.POST, path = "/flights")
    public void add(@RequestBody Flight flight) {
        if (!flightDAO.addFlight(flight)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "El vuelo ya existe");
        }
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/flights/{flightNumber}")
    public void update(@RequestBody Flight flight) {
        try {
            flightDAO.updateFlight(flight);
        } catch (FlightNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

}
