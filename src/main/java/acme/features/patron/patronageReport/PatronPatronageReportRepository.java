package acme.features.patron.patronageReport;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.PatronageReport;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface PatronPatronageReportRepository extends AbstractRepository{
	
	@Query("select r from PatronageReport r")
	Collection<PatronageReport> findAllPatronageReports();

	@Query("select r from PatronageReport r where r.patronage.id = :id")
	Collection<PatronageReport> findPatronageReportsByPatronageId(int id);
	
	@Query("select r from PatronageReport r where r.id = :id")
	PatronageReport findOnePatronageReportById(int id);
}
