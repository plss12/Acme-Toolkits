package acme.features.administrator.dashboard;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorDashboardRepository extends AbstractRepository {
	
	@Query("select count(a) from Artifact a where a.artifactType = acme.entities.ArtifactType.COMPONENT")
	int totalNumberOfComponents();
	
	@Query("select count(a) from Artifact a where a.artifactType = acme.entities.ArtifactType.TOOL")
	int totalNumberOfTools();
	
	@Query("select p.status,count(p) from Patronage p group by p.status")
	List<Object[]> totalNumberOfPatronages();
	
	//
	
	@Query("select a.technology,a.retailPrice.currency,avg(a.retailPrice.amount),min(a.retailPrice.amount),max(a.retailPrice.amount),stddev(a.retailPrice.amount) from Artifact a where a.artifactType = acme.entities.ArtifactType.COMPONENT group by a.retailPrice.currency, a.technology")
	List<Object[]> statsOfComponents();
	
	@Query("select a.retailPrice.currency,avg(a.retailPrice.amount),min(a.retailPrice.amount),max(a.retailPrice.amount),stddev(a.retailPrice.amount) from Artifact a where a.artifactType = acme.entities.ArtifactType.TOOL group by a.retailPrice.currency")
	List<Object[]> statsOfTools();
	
	@Query("select p.status,avg(p.budget.amount),min(p.budget.amount),max(p.budget.amount),stddev(p.budget.amount),p.budget.currency from Patronage p group by p.status")
	List<Object[]> statsOfPatronages();

}
