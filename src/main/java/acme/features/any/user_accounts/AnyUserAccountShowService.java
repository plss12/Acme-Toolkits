package acme.features.any.user_accounts;

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
	protected AnyUserAccountRepository anyUserRepo;
	
	@Autowired
	public AnyUserAccountShowService(final AnyUserAccountRepository anyUserRepo) {
		this.anyUserRepo = anyUserRepo;
	}

	@Override
	public boolean authorise(final Request<UserAccount> request) {
		assert request != null;
		
		return true;
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
		request.unbind(entity, model, "identity");
		
	}

}
