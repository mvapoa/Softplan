package br.com.softplan.sienge.transport.controller;

import java.math.BigDecimal;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import br.com.softplan.sienge.transport.model.Vehicle;
import br.com.softplan.sienge.transport.model.dto.CalculateTransportDTO;
import br.com.softplan.sienge.transport.model.dto.ResultDTO;
import br.com.softplan.sienge.transport.service.TransportationManagementService;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransportController {

	private static final Logger log = LoggerFactory.getLogger(TransportController.class);
	
	@Autowired
	private TransportationManagementService service;

	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/findAllVehicle", method = RequestMethod.POST)
	public ResultDTO findAllVehicle() {
		ResultDTO result = new ResultDTO();
		try {
			List<Vehicle> vehicles = service.findAllVehicle();

			result.setBody(vehicles);
			result.setStatus(HttpStatus.OK.value());
		} catch (Exception e) {
			log.error(">> {}", e.toString());
			result.setStatus(HttpStatus.BAD_REQUEST.value());
		}
		return result;
	}

	@RequestMapping(value = "/insertVehicle", method = RequestMethod.POST)
	public ResultDTO insertVehicle(@RequestBody Vehicle request) {
		ResultDTO result = new ResultDTO();
		try {
			Vehicle vehicle = service.insertVehicle(request);

			result.setBody(vehicle);
			result.setStatus(HttpStatus.OK.value());
		} catch (Exception e) {
			log.error(">> {}", e.toString());
			result.setStatus(HttpStatus.BAD_REQUEST.value());
		}
		return result;
	}

	@RequestMapping(value = "/calculateTransport", method = RequestMethod.POST)
	public ResultDTO calculateTransport(@RequestBody CalculateTransportDTO request) {
		ResultDTO result = new ResultDTO();
		try {
			BigDecimal total = service.calculateTransport(request);

			result.setBody(total.setScale(2, BigDecimal.ROUND_DOWN));
			result.setStatus(HttpStatus.OK.value());
		} catch (Exception e) {
			log.error(">> {}", e.toString());
			result.setStatus(HttpStatus.BAD_REQUEST.value());
		}
		return result;
	}

}
