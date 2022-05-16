package acme.features.inventor.patronage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Patronage;
import acme.entities.PatronageStatus;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractUpdateService;
import acme.roles.Inventor;

@Service
public class InventorPatronageUpdateService implements AbstractUpdateService<Inventor, Patronage> {

	// Internal state -------------------------------------------------------------------
	@Autowired
	protected InventorPatronageRepository invPatroRepo;
	
	@Override
	public boolean authorise(final Request<Patronage> request) {
		assert request != null;
		boolean result;
		int id;
		Patronage patronage;
		id = request.getModel().getInteger("id");
		patronage = this.invPatroRepo.findPatronageById(id);
		result = (patronage != null && patronage.isPublic() && patronage.getStatus().equals(PatronageStatus.PROPOSED) && 
			(request.isPrincipal(patronage.getInventor())));
		return result;
	}

	@Override
	public void bind(final Request<Patronage> request, final Patronage entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		request.bind(entity, errors, "code", "budget", "legalStuff", "link", "startDate", "finishDate", "status", 
			"patron.company", "patron.link", "patron.statement");
		
	}

	@Override
	public void unbind(final Request<Patronage> request, final Patronage entity, final Model model) {
		model.setAttribute("accepted", PatronageStatus.ACCEPTED);
		model.setAttribute("denied", PatronageStatus.DENIED);
		request.unbind(entity, model, "code", "budget", "legalStuff", "link", "startDate", "finishDate", "status", 
			"patron.company", "patron.link", "patron.statement");
		
	}

	@Override
	public Patronage findOne(final Request<Patronage> request) {
		
		assert request != null;
		
		int id;
		Patronage result;
		
		id = request.getModel().getInteger("id");
		result = this.invPatroRepo.findPatronageById(id);
		
		return result;
	}

	@Override
	public void validate(final Request<Patronage> request, final Patronage entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
	}

	@Override
	public void update(final Request<Patronage> request, final Patronage entity) {
		assert request != null;
		assert entity != null;
		
		this.invPatroRepo.save(entity);
		
	}



}
