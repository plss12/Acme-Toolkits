package acme.features.any.userAccount;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.entities.UserAccount;
import acme.framework.roles.Any;
import acme.framework.services.AbstractListService;

@Service
public class AnyUserAccountListService implements AbstractListService<Any, UserAccount>{
	
	// Internal state -------------------------------------------------------------------
	@Autowired
	protected AnyUserAccountRepository anyUserRepo;

	// AbstractListService<Any, UserAccount> interface ----------------------------
	
	@Override
	public boolean authorise(final Request<UserAccount> request) {
		assert request != null;
		return true;
	}

	@Override
	public List<UserAccount> findMany(final Request<UserAccount> request) {
		assert request != null;
		List<UserAccount> allAccounts;
		final Boolean enabled = true;
		allAccounts = this.anyUserRepo.findAllUserAccountsAllowed("anonymous", "administrator",enabled);
		return allAccounts;
	}

	@Override
	public void unbind(final Request<UserAccount> request, final UserAccount entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "username");
		
	}

}
