package br.com.softplan.sienge.transport.model;

import java.math.BigDecimal;
import java.text.Normalizer;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Setter
@Getter
@ToString
public class Vehicle {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@Column(unique = true)
	private String key;

	@NotNull
	private String name;

	@NotNull
	private BigDecimal cost;

	public void setNameLowerCase() {
		name = name.toLowerCase();
	}

	public void generateKey() {
		key = Normalizer.normalize(name.replaceAll(" ", "_"), Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "")
				.toLowerCase();
	}

}
