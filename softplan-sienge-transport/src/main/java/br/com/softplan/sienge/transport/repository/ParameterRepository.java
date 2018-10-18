package br.com.softplan.sienge.transport.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.softplan.sienge.transport.model.Parameter;

@Repository
public interface ParameterRepository extends JpaRepository<Parameter, Long> {

	Parameter findByKey(String key);
	
}
