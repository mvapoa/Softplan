package br.com.softplan.sienge.transport.model.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ResultDTO {

	private Integer status;
	private Object body;

	@Override
	public String toString() {
		return "Result [status=" + status + ", body=" + body + "]";
	}

}
