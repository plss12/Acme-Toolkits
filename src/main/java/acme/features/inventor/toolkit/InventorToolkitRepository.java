package acme.features.inventor.toolkit;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.Toolkit;
import acme.framework.repositories.AbstractRepository;
import acme.roles.Inventor;

@Repository
public interface InventorToolkitRepository extends AbstractRepository{
	
	@Query("select t from Toolkit t where t.id = :id")
	Toolkit findToolkitById(int id);
	
	@Query("select t from Toolkit t where t.code = :code")
	Toolkit findToolkitByCode(String code);
	
	@Query("select i from Inventor i where i.userAccount.id = :id")
	Inventor findInventorByUserAccountId(int id);
	
	@Query("select t from Toolkit t where t.inventor.id = :id")
	Collection<Toolkit> findToolkitByInventorId(int id);
	
	@Query("select at.artifact,at.artifactAmount from ArtifactToolkit at where at.toolkit.id = :id")
	List<Object[]> findPricesAndNumberOfArtifactsByToolkit(int id);
	
	@Query("select c.defaultCurrency from Configuration c")
	String defaultCurrency();
	
	@Query("SELECT t from Toolkit t where t.inventor.userAccount.username = :username and t.isPublic = :visibility")
	List<Toolkit> findPublicToolkitsByInventorUsername(String username, boolean visibility);

}
