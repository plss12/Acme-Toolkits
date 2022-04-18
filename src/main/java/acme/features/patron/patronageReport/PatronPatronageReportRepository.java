package acme.features.patron.patronageReport;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.Patronage;
import acme.entities.PatronageReport;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface PatronPatronageReportRepository extends AbstractRepository{
	
	@Query("select p from Patronage p where p.id = :masterId")
	Patronage findOnePatronageById(int masterId);
	
	@Query("select r from PatronageReport r where r.patronage.id = :masterId")
	Collection<PatronageReport> findPatronageReportsByPatronageId(int masterId);
	
	@Query("select r from PatronageReport r where r.id = :id")
	PatronageReport findOnePatronageReportById(int id);
}
