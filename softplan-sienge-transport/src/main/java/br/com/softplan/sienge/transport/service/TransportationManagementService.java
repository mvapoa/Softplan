package br.com.softplan.sienge.transport.service;

import java.math.BigDecimal;
import java.util.List;

import br.com.softplan.sienge.transport.model.Vehicle;
import br.com.softplan.sienge.transport.model.dto.CalculateTransportDTO;

public interface TransportationManagementService {

	List<Vehicle> findAllVehicle();

	Vehicle insertVehicle(Vehicle vehicle);

	BigDecimal calculateTransport(CalculateTransportDTO request);

}
