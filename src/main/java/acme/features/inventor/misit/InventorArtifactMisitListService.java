package acme.features.inventor.misit;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Artifact;
import acme.entities.ArtifactType;
import acme.entities.Misit;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.helpers.CollectionHelper;
import acme.framework.services.AbstractListService;
import acme.roles.Inventor;

@Service
public class InventorArtifactMisitListService implements AbstractListService<Inventor, Misit>{
	
	@Autowired
	protected InventorMisitRepository misitRepo;
	
	@Override
	public boolean authorise(final Request<Misit> request) {
		boolean result;
		int masterId;
		
		Artifact artifact;
		
		masterId = request.getModel().getInteger("masterId");
		artifact = this.misitRepo.findOneArtifactById(masterId);
		result = (artifact != null && (request.isPrincipal(artifact.getInventor())) && artifact.getArtifactType()==ArtifactType.COMPONENT);
		return result;
	}

	@Override
	public Collection<Misit> findMany(final Request<Misit> request) {
		assert request != null;
		final Integer masterId = request.getModel().getInteger("masterId");
		final Collection<Misit> result = this.misitRepo.findAllMisitFromArtefact(masterId);
		return result;
	}

	@Override
	public void unbind(final Request<Misit> request, final Misit entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;	
		request.unbind(entity, model, "code","creationMoment","subject","explanation", "startDate", "finishDate", "quantity", "additionalInfo", "artefact.code");
	}
	
	@Override
	public void unbind(final Request<Misit> request, final Collection<Misit> entities, final Model model) {
		assert request != null;
		assert !CollectionHelper.someNull(entities);
		assert model != null;
		
		model.setAttribute("masterId", request.getModel().getInteger("masterId"));
	}

}
