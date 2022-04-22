package acme.features.patron.patronage;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.Patronage;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface PatronPatronageRepository extends AbstractRepository{
	
	@Query("select patronage from Patronage patronage where patronage.patron.id =:id")
	List<Patronage> findManyPatronagesByPatronId(int id);
	
	@Query("select patronage from Patronage patronage where patronage.id =:id")
	Patronage findPatronageById(int id);

}
