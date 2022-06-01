package acme.features.inventor.chimpum;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.CHIMPUM;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractListService;
import acme.roles.Inventor;

@Service
public class InventorChimpumListService implements AbstractListService<Inventor, CHIMPUM>{

	@Autowired
	protected InventorChimpumRepository chimpumRepo;
	
	@Override
	public boolean authorise(final Request<CHIMPUM> request) {
		assert request != null;
		return true;
	}

	@Override
	public Collection<CHIMPUM> findMany(final Request<CHIMPUM> request) {
		assert request != null;	
		return this.chimpumRepo.findAllChimpums();
	}

	@Override
	public void unbind(final Request<CHIMPUM> request, final CHIMPUM entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;	
		
		request.unbind(entity, model, "code","creationMoment","title","description", "startDate", "finishDate", "budget", "link", "artefact.code");
		
	}

}
