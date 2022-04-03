package acme.features.any.user_accounts;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.entities.UserAccount;
import acme.framework.roles.Administrator;
import acme.framework.roles.Any;
import acme.framework.services.AbstractListService;

@Service
public class AnyUserAccountListService implements AbstractListService<Any, UserAccount>{
	
	// Internal state -------------------------------------------------------------------
	protected AnyUserAccountRepository anyUserRepo;
	
	@Autowired
	public AnyUserAccountListService(final AnyUserAccountRepository anyUserRepo) {
		this.anyUserRepo = anyUserRepo;
	}

	// AbstractListService<Any, UserAccount> interface ----------------------------
	
	@Override
	public boolean authorise(final Request<UserAccount> request) {
		assert request != null;
		return true;
	}

	@Override
	public Collection<UserAccount> findMany(final Request<UserAccount> request) {
		assert request != null;
		Collection<UserAccount> res;
		res = this.anyUserRepo.findAllUserAccounts();
		for(final UserAccount ua:res) {
			if(ua.isAnonymous() || ua.hasRole(Administrator.class)) {
				res.remove(ua);
			}
		}
		return res;
	}

	@Override
	public void unbind(final Request<UserAccount> request, final UserAccount entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "username");
		
	}

}
