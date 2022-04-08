package acme.features.authenticated.inventor.patronage;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Patronage;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.roles.Authenticated;
import acme.framework.services.AbstractListService;

@Service
public class InventorPatronageListService implements AbstractListService<Authenticated, Patronage>{

	@Autowired
	protected InventorPatronageRepository repository;
	
	@Override
	public boolean authorise(final Request<Patronage> request) {
		assert request != null;
		return true;
	}

	@Override
	public Collection<Patronage> findMany(final Request<Patronage> request) {
		assert request != null;
		
		final Collection<Patronage> result;
		final int userAccountId = request.getPrincipal().getAccountId();
		final int inventorId = this.repository.findInventorByUserAccountId(userAccountId).getId();
		result = this.repository.findPatronagesByInventorId(inventorId);
		
		return result;
	}

	@Override
	public void unbind(final Request<Patronage> request, final Patronage entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "code", "budget.amount", "budget.currency", "legalStuff", "link", "startDate",
			"finishDate", "status", "patron_id");
		
	}

}
