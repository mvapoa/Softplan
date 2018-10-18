package br.com.softplan.sienge.transport.model;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RoadwayDistance {

	private Roadway roadway;
	private Integer distance;

	public RoadwayDistance(Roadway roadway, Integer distance) {
		super();
		this.roadway = roadway;
		this.distance = distance;
	}

	public BigDecimal getDistanceCost() {
		return roadway.getCost().multiply(new BigDecimal(distance));
	}

	@Override
	public String toString() {
		return "RoadwayDistance [roadway=" + roadway + ", distance=" + distance + "]";
	}

}
