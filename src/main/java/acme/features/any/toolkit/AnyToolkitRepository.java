package acme.features.any.toolkit;


import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.Artifact;
import acme.entities.Toolkit;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AnyToolkitRepository extends AbstractRepository {
	
	@Query("select t from Toolkit t where t.isPublic =:b")
	Collection<Toolkit> findAllToolkits(boolean b);
	
	@Query("select t from Toolkit t where t.id = :id")
	Toolkit findOneToolkitById(int id);
	
	@Query("select at.artifact from ArtifactToolkit at where at.toolkit.id = :id")
	Collection<Artifact> findArtifactsByToolkit(int id);
	
	@Query("select at.artifact,at.artifactAmount from ArtifactToolkit at where at.toolkit.id = :id")
	List<Object[]> findPricesAndNumberOfArtifactsByToolkit(int id);
	
	@Query("select c.defaultCurrency from Configuration c")
	String defaultCurrency();

}

