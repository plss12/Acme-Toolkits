package acme.features.authenticated.chirp;

import java.sql.Date;
import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.Chirp;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedChirpRepository extends AbstractRepository{

	@Query("select c from Chirp j where c.creationMoment > :deadLine")
	Collection<Chirp> findRecentChirps(Date deadLine);

}
