package acme.features.inventor.misit;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.Artifact;
import acme.entities.Misit;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface InventorMisitRepository extends AbstractRepository{
	
	@Query("select m from Misit m")
	Collection<Misit> findAllMisit();
	
	@Query("select m from Misit m WHERE m.id = :id")
	Misit findMisitById(int id);
	
	@Query("select m from Misit m WHERE m.artefact.id = :id")
	Collection<Misit> findAllMisitFromArtefact(int id);
	
	@Query("select a from Artifact a where a.id = :masterId")
	Artifact findOneArtifactById(int masterId);
	
	@Query("select m from Misit m where m.artefact.id =:id")
	Collection<Misit> findManyMisitByArtifactId(Integer id);
	
	@Query("select sc.acceptedCurrencies from Configuration sc")
	String findAllAcceptedCurrencies();
	
	@Query("select m from Misit m where m.code = :code")
	Misit findMisitByCode(String code);
	

}
