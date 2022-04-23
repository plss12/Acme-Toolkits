package acme.features.any.userAccount;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.framework.entities.UserAccount;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AnyUserAccountRepository extends AbstractRepository{
	
	@Query("select ua from UserAccount ua where ua.username !=:anonymous and ua.username !=:administrator and ua.enabled=:enabled")
	List<UserAccount> findAllUserAccountsAllowed(String anonymous, String administrator, boolean enabled);
	
	@Query("select ua from UserAccount ua where ua.id =:id")
	UserAccount findUserAccountById(int id);

}
