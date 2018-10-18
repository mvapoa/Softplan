package br.com.softplan.sienge.transport.bootstrap;

import java.math.BigDecimal;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import br.com.softplan.sienge.transport.model.Parameter;
import br.com.softplan.sienge.transport.model.Roadway;
import br.com.softplan.sienge.transport.model.Vehicle;
import br.com.softplan.sienge.transport.repository.ParameterRepository;
import br.com.softplan.sienge.transport.repository.RoadwayRepository;
import br.com.softplan.sienge.transport.repository.VehicleRepository;

@Component
public class ApplicationBootstrap implements ApplicationListener<ContextRefreshedEvent> {

	private final VehicleRepository vehicleRepository;
	private final RoadwayRepository roadwayRepository;
	private final ParameterRepository parameterRepository;

	public ApplicationBootstrap(VehicleRepository vehicleRepository, RoadwayRepository roadwayRepository,
			ParameterRepository parameterRepository) {
		super();
		this.vehicleRepository = vehicleRepository;
		this.roadwayRepository = roadwayRepository;
		this.parameterRepository = parameterRepository;
	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {

		if (!vehicleRepository.findAll().iterator().hasNext()) {
			loadDataVehicle();
		}

		if (!roadwayRepository.findAll().iterator().hasNext()) {
			loadDataRoadway();
		}

		if (!parameterRepository.findAll().iterator().hasNext()) {
			loadDataParameter();
		}
	}

	private void loadDataParameter() {
		Parameter parameter1 = new Parameter();
		parameter1.setKey("tolerancia_tonelada_quantidade");
		parameter1.setValue("5");
		parameterRepository.save(parameter1);

		Parameter parameter2 = new Parameter();
		parameter2.setKey("tolerancia_tonelada_custo_adicional");
		parameter2.setValue("0.02");
		parameterRepository.save(parameter2);

	}

	private void loadDataVehicle() {
		Vehicle vehicle1 = new Vehicle();
		vehicle1.setKey("caminhao_bau");
		vehicle1.setName("caminhão baú");
		vehicle1.setCost(new BigDecimal("1"));
		vehicleRepository.save(vehicle1);

		Vehicle vehicle2 = new Vehicle();
		vehicle2.setKey("caminhao_cacamba");
		vehicle2.setName("caminhão caçamba");
		vehicle2.setCost(new BigDecimal("1.05"));
		vehicleRepository.save(vehicle2);

		Vehicle vehicle3 = new Vehicle();
		vehicle3.setKey("carreta");
		vehicle3.setName("carreta");
		vehicle3.setCost(new BigDecimal("1.12"));
		vehicleRepository.save(vehicle3);
	}

	private void loadDataRoadway() {
		Roadway roadway1 = new Roadway();
		roadway1.setKey("pavimentada");
		roadway1.setType("pavimentada");
		roadway1.setCost(new BigDecimal("0.54"));
		roadwayRepository.save(roadway1);

		Roadway roadway2 = new Roadway();
		roadway2.setKey("nao_pavimentada");
		roadway2.setType("não-pavimentada");
		roadway2.setCost(new BigDecimal("0.62"));
		roadwayRepository.save(roadway2);

	}

}
