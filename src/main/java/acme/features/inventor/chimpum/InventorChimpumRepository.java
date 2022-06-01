package acme.features.inventor.chimpum;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.Artifact;
import acme.entities.ArtifactType;
import acme.entities.Chimpum;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface InventorChimpumRepository extends AbstractRepository{

	@Query("select c from Chimpum c")
	Collection<Chimpum> findAllChimpums();
	
	@Query("select c from Chimpum c where c.id = :id")
	Chimpum findChimpumById(int id);
	
	@Query("select c from Chimpum c where c.code = :code")
	Chimpum findChimpumByCode(String code);
	
	@Query("select c from Chimpum c where c.artifact.id = :id")
	Collection<Chimpum> findChimpumByArtifactId(int id);
	
	@Query("select a from Artifact a where a.id = :id")
	Artifact findArtifactById(int id);
	
	@Query("select a from Artifact a where a.code = :code")
	Artifact findArtifactByCode(String code);
	
	@Query("SELECT a from Artifact a where a.isPublic = True and a.artifactType = :type")
	List<Artifact> findAllArtifactsPublicsByType(ArtifactType type);
	
	@Query("SELECT sc.acceptedCurrencies from Configuration sc")
	String findAllAcceptedCurrencies();
	
	@Query("SELECT sc.defaultCurrency from Configuration sc")
	String findDefaultCurrency();
}
