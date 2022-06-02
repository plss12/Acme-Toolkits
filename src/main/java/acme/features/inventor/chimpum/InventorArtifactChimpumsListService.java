package acme.features.inventor.chimpum;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Artifact;
import acme.entities.ArtifactType;
import acme.entities.CHIMPUM;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.helpers.CollectionHelper;
import acme.framework.services.AbstractListService;
import acme.roles.Inventor;

@Service
public class InventorArtifactChimpumsListService implements AbstractListService<Inventor, CHIMPUM>{
	
	@Autowired
	protected InventorChimpumRepository chimpumRepo;
	
	@Override
	public boolean authorise(final Request<CHIMPUM> request) {
		boolean result;
		int masterId;
		
		Artifact artifact;
		
		masterId = request.getModel().getInteger("masterId");
		artifact = this.chimpumRepo.findOneArtifactById(masterId);
		result = (artifact != null && (request.isPrincipal(artifact.getInventor())) && artifact.getArtifactType()==ArtifactType.TOOL);
		return result;
	}

	@Override
	public Collection<CHIMPUM> findMany(final Request<CHIMPUM> request) {
		assert request != null;
		final Integer masterId = request.getModel().getInteger("masterId");
		final Collection<CHIMPUM> result = this.chimpumRepo.findAllChimpumsFromArtefact(masterId);
		return result;
	}

	@Override
	public void unbind(final Request<CHIMPUM> request, final CHIMPUM entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;	
		request.unbind(entity, model, "code","creationMoment","title","description", "startDate", "finishDate", "budget", "link", "artefact.code");
	}
	
	@Override
	public void unbind(final Request<CHIMPUM> request, final Collection<CHIMPUM> entities, final Model model) {
		assert request != null;
		assert !CollectionHelper.someNull(entities);
		assert model != null;
		
		model.setAttribute("masterId", request.getModel().getInteger("masterId"));
	}

}
