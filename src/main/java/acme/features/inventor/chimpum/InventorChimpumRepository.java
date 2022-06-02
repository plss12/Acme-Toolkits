package acme.features.inventor.chimpum;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.Artifact;
import acme.entities.CHIMPUM;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface InventorChimpumRepository extends AbstractRepository{
	
	@Query("select c from CHIMPUM c")
	Collection<CHIMPUM> findAllChimpums();
	
	@Query("select c from CHIMPUM c WHERE c.id = :id")
	CHIMPUM findChimpumById(int id);
	
	@Query("select c from CHIMPUM c WHERE c.artefact.id = :id")
	Collection<CHIMPUM> findAllChimpumsFromArtefact(int id);
	
	@Query("select a from Artifact a where a.id = :masterId")
	Artifact findOneArtifactById(int masterId);
	
	@Query("select c from CHIMPUM c where c.artefact.id =:id")
	Collection<CHIMPUM> findManyCHIMPUMsByArtifactId(Integer id);
	
	@Query("select sc.acceptedCurrencies from Configuration sc")
	String findAllAcceptedCurrencies();
	
	@Query("select c from CHIMPUM c where c.code = :code")
	CHIMPUM findChimpumByCode(String code);
	

}
