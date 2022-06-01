package acme.features.inventor.chimpum;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.CHIMPUM;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractShowService;
import acme.roles.Inventor;

@Service
public class InventorChimpumShowService implements AbstractShowService<Inventor, CHIMPUM>{
	
	@Autowired
	protected InventorChimpumRepository chimpumRepo;

	@Override
	public boolean authorise(final Request<CHIMPUM> request) {
		
		boolean result;
		int id;
		CHIMPUM chimpum;
		
		id = request.getModel().getInteger("id");
		chimpum = this.chimpumRepo.findChimpumById(id);
		result = (chimpum != null && (request.isPrincipal(chimpum.getArtefact().getInventor())));
		
		return result;
	}

	@Override
	public CHIMPUM findOne(final Request<CHIMPUM> request) {
		final Integer id = request.getModel().getInteger("id");
		final CHIMPUM result = this.chimpumRepo.findChimpumById(id);
		return result;
	}

	@Override
	public void unbind(final Request<CHIMPUM> request, final CHIMPUM entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;	
		
		
		
		request.unbind(entity, model, "code","creationMoment","title","description", "startDate", "finishDate", "budget", "link", "artefact.code");
		
		
	}
	

}
