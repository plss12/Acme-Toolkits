package acme.features.any.toolkit;


import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.ArtifactToolkit;
import acme.entities.Toolkit;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AnyToolkitRepository extends AbstractRepository {
	
	@Query("select * from Toolkit")
	Collection<Toolkit> findAllToolkits();
	
	@Query("select t.* from Toolkit t where t.artifactToolkit = ?1")
	Collection<Toolkit> findAllToolkitsWithComponentOrTool(ArtifactToolkit artifactToolkit);
	
	@Query("select t from Toolkit t where t.id = :id")
	Toolkit findOneToolkitById(int id);

}

