package acme.features.inventor.patronage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Patronage;
import acme.entities.PatronageStatus;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractShowService;
import acme.roles.Inventor;

@Service
public class InventorPatronageShowService implements AbstractShowService<Inventor, Patronage>{

	@Autowired
	protected InventorPatronageRepository repository;
	
	@Override
	public boolean authorise(final Request<Patronage> request) {
		assert request != null;
		
		boolean result;
		int id;
		id = request.getModel().getInteger("id");
		
		Patronage patronage;
		patronage = this.repository.findPatronageById(id);
		
		result = (patronage != null &&(request.isPrincipal(patronage.getInventor())));
		
		return result;
	}

	@Override
	public Patronage findOne(final Request<Patronage> request) {
		assert request != null;
		
		int id;
		Patronage result;
		
		id = request.getModel().getInteger("id");
		result = this.repository.findPatronageById(id);
		
		return result;
	}

	@Override
	public void unbind(final Request<Patronage> request, final Patronage entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		model.setAttribute("isProposedAndPublic", entity.getStatus().equals(PatronageStatus.PROPOSED) && entity.isPublic());
		request.unbind(entity, model, "code", "budget", "legalStuff", "link", "startDate", "finishDate", "status", 
			"patron.company", "patron.link", "patron.statement", "patron.userAccount.username");
		model.setAttribute("readonly",true);
		model.setAttribute("confirmation", false);
	}

}
