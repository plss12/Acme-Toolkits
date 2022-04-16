package acme.features.patron.patronage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Patronage;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractShowService;
import acme.roles.Patron;

@Service
public class PatronPatronageShowService implements AbstractShowService<Patron, Patronage>{

	// Internal state ---------------------------------------------------------
	
	@Autowired
	protected PatronPatronageRepository repository;
	
	@Override
	public boolean authorise(final Request<Patronage> request) {
		assert request != null;

		boolean result;
		int masterId;
		Principal principal;
		Patronage patronage;
		Patron patron;
		
		masterId = request.getModel().getInteger("id");
		principal = request.getPrincipal();
		patronage = this.repository.findPatronageById(masterId);
		patron = patronage.getPatron();
		result = principal.getAccountId() == patron.getId();
		
		return result;
	}

	@Override
	public Patronage findOne(final Request<Patronage> request) {
		assert request != null;

		Patronage result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findPatronageById(id);

		return result;
	}

	@Override
	public void unbind(final Request<Patronage> request, final Patronage entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "code", "legalStuff", "budget", "startDate", "finishDate", "link", 
			"inventor.company", "inventor.statement", "inventor.link");
		
	}

}
