package acme.features.authenticated.patron;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.framework.entities.UserAccount;
import acme.framework.repositories.AbstractRepository;
import acme.roles.Patron;

@Repository
public interface AuthenticatedPatronRepository extends AbstractRepository {
	
	@Query("select p from Patron p where p.userAccount.id = :id")
	Patron findPatronByUserAccountId(int id);
	
	@Query("select u from UserAccount u where u.id = :id")
	UserAccount findUserAccountById(int id);
}
