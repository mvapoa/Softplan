package br.com.softplan.sienge.transport.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.softplan.sienge.transport.model.Roadway;

@Repository
public interface RoadwayRepository extends JpaRepository<Roadway, Long> {

	Roadway findByKey(String key);
	
}
