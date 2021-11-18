package org.iesfm.rest.controller;

import org.iesfm.rest.Flight;
import org.iesfm.rest.Passenger;
import org.iesfm.rest.dao.FlightDAO;
import org.iesfm.rest.dao.PassengerDAO;
import org.iesfm.rest.exceptions.FlightNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class PassengerController {

    private PassengerDAO passengerDAO;
    private FlightDAO flightDAO;

    public PassengerController(PassengerDAO passengerDAO, FlightDAO flightDAO) {
        this.passengerDAO = passengerDAO;
        this.flightDAO = flightDAO;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/flights/{flightNumber}/passengers")
    public List<Passenger> list(@PathVariable("flightNumber") String flightNumber) {
        List<Passenger> passengers = passengerDAO.getFlightPassengers(flightNumber);
        if (passengers.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "El vuelo no existe");
        } else {
            return passengers;
        }
    }

    @RequestMapping(method = RequestMethod.GET, path = "/flights/{flightNumber}/passengers/{nif}")
    public Passenger listPassenger(@PathVariable("flightNumber") String flightNumber, @PathVariable("nif") String nif) {
        try {
            Flight flight = flightDAO.getFlight(flightNumber);
        } catch (FlightNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "El vuelo no existe");
        }
        Passenger passenger = passengerDAO.getFlightPassenger(flightNumber, nif);
        if (passenger == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "El pasajero no existe");
        } else {
            return passenger;
        }
    }

    @RequestMapping(method = RequestMethod.POST, path = "/flights/{flightNumber}/passengers")
    public void addPassenger(@PathVariable("flightNumber") String flightNumber, @RequestBody Passenger passenger) {

        try {
            Flight flight = flightDAO.getFlight(flightNumber);
        } catch (FlightNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "El vuelo no existe");
        }
        if (!passengerDAO.addPassenger(flightNumber, passenger)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "El pasajero ya existe");
        }
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/flights/{flightNumber}/passengers/{nif}")
    public void delete(@PathVariable("flightNumber") String flightNumber, @PathVariable("nif") String nif) {
        try {
            Flight flight = flightDAO.getFlight(flightNumber);
        } catch (FlightNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "El vuelo no existe");
        }
        if (!passengerDAO.deletePassenger(flightNumber, nif)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "El pasajero ya existe");
        }
    }
}
