package acme.features.inventor.patronageReport;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.Patronage;
import acme.entities.PatronageReport;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface InventorPatronageReportRepository extends AbstractRepository{

	@Query("select p from Patronage p where p.id = :masterId")
	Patronage findOnePatronageById(int masterId);
	
	@Query("select pr from PatronageReport pr where pr.patronage.id = :masterId")
	Collection<PatronageReport> findManyPatronageReportsByMasterId(int masterId);
	
	@Query("select pr from PatronageReport pr where pr.id = :id")
	PatronageReport findOnePatronageReportById(int id);
}
