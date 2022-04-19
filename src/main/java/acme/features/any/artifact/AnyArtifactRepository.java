package acme.features.any.artifact;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.Artifact;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AnyArtifactRepository extends AbstractRepository{
	
	@Query("select a from Artifact a")
	Collection<Artifact> findAllArtifacts();
	
	@Query("select a from Artifact a where a.id=:id")
	Artifact findArtifactById(int id);
}
