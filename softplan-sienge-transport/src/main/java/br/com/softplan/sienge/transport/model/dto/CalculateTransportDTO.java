package br.com.softplan.sienge.transport.model.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CalculateTransportDTO {

	private Integer distancePaved;
	private Integer distanceUnpaved;
	private String vehicle;
	private Integer freigth;

}
