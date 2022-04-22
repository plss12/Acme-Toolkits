package acme.features.any.userAccount;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.entities.UserAccount;
import acme.framework.roles.Any;
import acme.framework.services.AbstractShowService;

@Service
public class AnyUserAccountShowService implements AbstractShowService<Any, UserAccount>{
	
	// Internal state -------------------------------------------------------------------
	@Autowired
	protected AnyUserAccountRepository anyUserRepo;

	@Override
	public boolean authorise(final Request<UserAccount> request) {
		assert request != null;
		
		boolean result;
		final int id;
		id = request.getModel().getInteger("id");
		
		UserAccount userAccount;
		userAccount = this.anyUserRepo.findUserAccountById(id);
		
		result = (userAccount != null && !(userAccount.getUsername().equals("administrator") || userAccount.isAnonymous() || !userAccount.isEnabled()));
		return result;
	}

	@Override
	public UserAccount findOne(final Request<UserAccount> request) {
		assert request != null;
		int id;
		UserAccount ua;
		id = request.getModel().getInteger("id");
		ua = this.anyUserRepo.findUserAccountById(id);
		return ua;
	}

	@Override
	public void unbind(final Request<UserAccount> request, final UserAccount entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "username", "identity.name", "identity.surname", "identity.email");
		
	}

}
