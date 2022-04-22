package acme.features.any.toolkit;


import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.Toolkit;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AnyToolkitRepository extends AbstractRepository {
	
	@Query("select t from Toolkit t where t.isPublic =:b")
	Collection<Toolkit> findAllToolkits(boolean b);
	
	@Query("select t from Toolkit t where t.id = :id")
	Toolkit findOneToolkitById(int id);

}

