package acme.features.authenticated.inventor.artifact;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.Artifact;
import acme.framework.repositories.AbstractRepository;
import acme.roles.Inventor;

@Repository
public interface InventorArtifactRepository extends AbstractRepository{

	@Query("select a from Artifact a where a.id = :id")
	Artifact findArtifactById(int id);
	
	@Query("select i from Inventor i where i.userAccount.id = :id")
	Inventor findInventorByUserAccountId(int id);
	
	@Query("select a from Artifact a where a.inventor.id = :id")
	Collection<Artifact> findArtifactsByInventorId(int id);
}
