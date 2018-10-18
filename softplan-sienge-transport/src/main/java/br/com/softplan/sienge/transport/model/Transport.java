package br.com.softplan.sienge.transport.model;

import java.math.BigDecimal;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Transport {

	private List<RoadwayDistance> roadways;
	private Vehicle vehicle;
	private Integer freight;
	private Integer freightTolerante;
	private BigDecimal freightToleranteCost;

	public Transport(List<RoadwayDistance> roadways, Vehicle vehicle, Integer freight, Integer freightTolerante,
			BigDecimal freightToleranteCost) {
		super();
		this.roadways = roadways;
		this.vehicle = vehicle;
		this.freight = freight;
		this.freightTolerante = freightTolerante;
		this.freightToleranteCost = freightToleranteCost;
	}

	public Integer getTotalDistanceAllRoadways() {
		Integer totalDistance = new Integer(0);
		for (RoadwayDistance roadway : roadways) {
			totalDistance = totalDistance + roadway.getDistance();
		}
		return totalDistance;
	}

	public BigDecimal getExcessFreightCost() {
		if (freight > freightTolerante) {
			Integer freightRemaining = freight - freightTolerante;
			return freightToleranteCost.multiply(new BigDecimal(freightRemaining * getTotalDistanceAllRoadways()));
		}
		return BigDecimal.ZERO;
	}

	public BigDecimal getFreightCost() {
		BigDecimal costFreight = BigDecimal.ZERO;
		for (RoadwayDistance roadway : roadways) {
			costFreight = costFreight.add(vehicle.getCost().multiply(roadway.getDistanceCost()));
		}
		return costFreight;
	}

	public BigDecimal getTotalCost() {
		return getFreightCost().add(getExcessFreightCost());
	}

	@Override
	public String toString() {
		return "Transport [roadways=" + roadways + ", vehicle=" + vehicle + ", freight=" + freight
				+ ", freightTolerante=" + freightTolerante + ", freightToleranteCost=" + freightToleranteCost + "]";
	}

}
