package acme.features.patron.dashboard;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.framework.repositories.AbstractRepository;

@Repository
public interface PatronDashboardRepository extends AbstractRepository{
	@Query("Select p.status, count(p) from Patronage p Where p.patron.id = :patronId Group by p.status")
	List<Object[]> totalNumberOfPatronages(int patronId);
	
	@Query("Select p.status, avg(p.budget.amount), p.budget.currency from Patronage p Where p.patron.id = :patronId Group by p.status")
	List<Object[]> averageBudgetOfPatronages(int patronId);

	@Query("Select p.status, stddev(p.budget.amount), p.budget.currency from Patronage p Where p.patron.id = :patronId Group by p.status")
	List<Object[]> deviationBudgetOfPatronages(int patronId);
	
	@Query("Select p.status, min(p.budget.amount), p.budget.currency from Patronage p Where p.patron.id = :patronId Group by p.status")
	List<Object[]> minimumBudgetOfPatronages(int patronId);
	
	@Query("Select p.status, max(p.budget.amount), p.budget.currency from Patronage p Where p.patron.id = :patronId Group by p.status")
	List<Object[]> maximumBudgetOfPatronages(int patronId);
}
