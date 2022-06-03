package acme.features.inventor.misit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Misit;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractShowService;
import acme.roles.Inventor;

@Service
public class InventorMisitShowService implements AbstractShowService<Inventor, Misit>{
	
	@Autowired
	protected InventorMisitRepository chimpumRepo;

	@Override
	public boolean authorise(final Request<Misit> request) {
		
		boolean result;
		int id;
		Misit chimpum;
		
		id = request.getModel().getInteger("id");
		chimpum = this.chimpumRepo.findMisitById(id);
		result = (chimpum != null && (request.isPrincipal(chimpum.getArtefact().getInventor())));
		
		return result;
	}

	@Override
	public Misit findOne(final Request<Misit> request) {
		final Integer id = request.getModel().getInteger("id");
		final Misit result = this.chimpumRepo.findMisitById(id);
		return result;
	}

	@Override
	public void unbind(final Request<Misit> request, final Misit entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;	
		
		
		
		request.unbind(entity, model, "code","creationMoment","subject","explanation","startDate","finishDate","quantity","additionalInfo", "artefact.code");
		
		
	}
	

}
