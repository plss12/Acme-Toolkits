package acme.features.SpamDetector;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.Configuration;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface SpamDetectorRepository extends AbstractRepository{
	
	@Query("select conf from Configuration conf")
	Configuration findOneConf();

}
