package br.com.softplan.sienge.transport.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import br.com.softplan.sienge.transport.model.Parameter;
import br.com.softplan.sienge.transport.model.Roadway;
import br.com.softplan.sienge.transport.model.RoadwayDistance;
import br.com.softplan.sienge.transport.model.Transport;
import br.com.softplan.sienge.transport.model.Vehicle;
import br.com.softplan.sienge.transport.model.dto.CalculateTransportDTO;
import br.com.softplan.sienge.transport.repository.ParameterRepository;
import br.com.softplan.sienge.transport.repository.RoadwayRepository;
import br.com.softplan.sienge.transport.repository.VehicleRepository;

@Service
public class TransportationManagementServiceImpl implements TransportationManagementService {

	@Value("${environment.key.tolerance.ton.quantity}")
	private String keyToleranceTon;

	@Value("${environment.key.tolerance.ton.additional-cost}")
	private String keyToleranceTonCost;

	@Value("${environment.key.paved}")
	private String keyPaved;

	@Value("${environment.key.unpaved}")
	private String keyUnpaved;

	@Autowired
	private RoadwayRepository roadwayRepository;

	@Autowired
	private VehicleRepository vehicleRepository;

	@Autowired
	private ParameterRepository parameterRepository;

	public List<Vehicle> findAllVehicle() {
		return vehicleRepository.findAll();
	}

	public Vehicle insertVehicle(Vehicle vehicle) {
		vehicle.setNameLowerCase();
		vehicle.generateKey();
		return vehicleRepository.save(vehicle);
	}

	public BigDecimal calculateTransport(CalculateTransportDTO request) {
		// Roadway
		Roadway paved = roadwayRepository.findByKey(keyPaved);
		Roadway unpaved = roadwayRepository.findByKey(keyUnpaved);
		RoadwayDistance distancePaved = new RoadwayDistance(paved, request.getDistancePaved());
		RoadwayDistance distanceUnpaved = new RoadwayDistance(unpaved, request.getDistanceUnpaved());
		List<RoadwayDistance> roadways = new ArrayList<>();
		roadways.add(distancePaved);
		roadways.add(distanceUnpaved);

		// Vehicle
		Vehicle vehicle = vehicleRepository.findByKey(request.getVehicle());

		// Freight
		Integer freight = request.getFreigth();

		// FreightTolerante
		Parameter toleranceTon = parameterRepository.findByKey(keyToleranceTon);
		Integer freightTolerante = Integer.parseInt(toleranceTon.getValue());

		// FreightToleranteCost
		Parameter toleranceTonAdditionalCost = parameterRepository.findByKey(keyToleranceTonCost);
		BigDecimal freightToleranteCost = new BigDecimal(toleranceTonAdditionalCost.getValue());

		Transport transport = new Transport(roadways, vehicle, freight, freightTolerante, freightToleranteCost);
		return transport.getTotalCost();
	}

}
