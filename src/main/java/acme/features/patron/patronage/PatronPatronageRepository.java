package acme.features.patron.patronage;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.Patronage;
import acme.entities.PatronageReport;
import acme.framework.repositories.AbstractRepository;
import acme.roles.Inventor;
import acme.roles.Patron;

@Repository
public interface PatronPatronageRepository extends AbstractRepository{
	
	@Query("select patronage from Patronage patronage where patronage.patron.id =:id")
	List<Patronage> findManyPatronagesByPatronId(int id);
	
	@Query("select patronage from Patronage patronage where patronage.id =:id")
	Patronage findPatronageById(int id);
	
	@Query("select patron from Patron patron where patron.id =:id")
	Patron findOnePatronById(int id);

	@Query("select patronage from Patronage patronage where patronage.code =:code")
	Patronage findOnePatronageByCode(String code);

	@Query("select inventor from Inventor inventor where inventor.userAccount.username =:inventorUsername")
	List<Inventor> findInventorByUsername(String inventorUsername);

	@Query("select patronageReports from PatronageReport patronageReports where patronageReports.patronage.id =:id")
	Collection<PatronageReport> findManyPatronageReportsByPatronageId(int id);
	
	@Query("SELECT sc.acceptedCurrencies from Configuration sc")
	String findAllAcceptedCurrencies();
	
	@Query("SELECT sc.defaultCurrency from Configuration sc")
	String findDefaultCurrency();
	
	@Query("SELECT inventors from Inventor inventors")
	List<Inventor> findAllInventors();

}
