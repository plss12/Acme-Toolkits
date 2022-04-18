package acme.features.administrator.dashboard;

import java.util.Map;

import org.springframework.data.jpa.repository.Query;

import acme.entities.PatronageStatus;
import acme.framework.datatypes.Money;
import acme.framework.repositories.AbstractRepository;

public interface AdministratorDashboardRepository extends AbstractRepository {
	
	@Query("select count(a) from Artifact a where a.artifactType = acme.entities.ArtifactType.COMPONENT")
	int totalNumberOfComponents();
	
	@Query("select count(a) from Artifact a where a.artifactType = acme.entities.ArtifactType.TOOL")
	int totalNumberOfTools();
	
	@Query("select p.patronageStatus,count(p) from Patronage p group by p.patronageStatus")
	Map<PatronageStatus,Integer> totalNumberOfPatronages();
	@Query("select p.patronageStatus,p.budget from Patronage p group by p.patronageStatus")
	Map<PatronageStatus,Money> averageBudgetOfPatronages();

}
