package br.com.softplan.sienge.transport.constroller;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.softplan.sienge.transport.controller.TransportController;
import br.com.softplan.sienge.transport.model.Vehicle;
import br.com.softplan.sienge.transport.model.dto.CalculateTransportDTO;
import br.com.softplan.sienge.transport.model.dto.ResultDTO;
import junit.framework.TestCase;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TransportControllerTest extends TestCase {

	@Autowired
	public TransportController controller;

	@Test
	public void calculateTransportTest() {
		CalculateTransportDTO request = new CalculateTransportDTO();
		request.setDistancePaved(new Integer(50));
		request.setDistanceUnpaved(new Integer(30));
		request.setFreigth(new Integer(5));
		request.setVehicle("caminhao_cacamba");
		ResultDTO result = controller.calculateTransport(request);

		ResultDTO expected = new ResultDTO();
		expected.setBody(new BigDecimal("47.88"));
		expected.setStatus(HttpStatus.OK.value());

		assertEquals(expected.toString(), result.toString());
	}

	@Test
	public void calculateTransportExcessFreightTest() {
		CalculateTransportDTO request = new CalculateTransportDTO();
		request.setDistancePaved(new Integer(80));
		request.setDistanceUnpaved(new Integer(20));
		request.setFreigth(new Integer(6));
		request.setVehicle("caminhao_bau");
		ResultDTO result = controller.calculateTransport(request);

		ResultDTO expected = new ResultDTO();
		expected.setBody(new BigDecimal("57.60"));
		expected.setStatus(HttpStatus.OK.value());

		assertEquals(expected.toString(), result.toString());
	}

	@Test
	public void calculateTransportExceptionTest() {
		CalculateTransportDTO request = new CalculateTransportDTO();
		request.setDistancePaved(new Integer(80));
		request.setDistanceUnpaved(new Integer(20));
		request.setFreigth(new Integer(6));
		ResultDTO result = controller.calculateTransport(request);

		ResultDTO expected = new ResultDTO();
		expected.setStatus(HttpStatus.BAD_REQUEST.value());

		assertEquals(expected.toString(), result.toString());
	}

	@Test
	public void insertVehicleTest() {
		Vehicle request = new Vehicle();
		request.setKey("teste");
		request.setName("teste");
		request.setCost(new BigDecimal("3.5"));
		ResultDTO result = controller.insertVehicle(request);

		ResultDTO expected = new ResultDTO();
		request.setId(new Long(4));
		expected.setBody(request);
		expected.setStatus(HttpStatus.OK.value());

		assertEquals(expected.toString(), result.toString());
	}

	@Test
	public void insertVehicleExceptionTest() {
		Vehicle request = new Vehicle();
		request.setKey("teste");
		request.setCost(new BigDecimal("3.5"));
		ResultDTO result = controller.insertVehicle(request);

		ResultDTO expected = new ResultDTO();
		expected.setStatus(HttpStatus.BAD_REQUEST.value());

		assertEquals(expected.toString(), result.toString());
	}

	@Test
	@SuppressWarnings("unchecked")
	public void findAllVehicle() {
		ResultDTO result = controller.findAllVehicle();
		List<Vehicle> vehicles = ((List<Vehicle>) result.getBody());

		assertEquals(4, vehicles.size());
	}

}
