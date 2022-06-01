package acme.features.inventor.chimpum;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Artifact;
import acme.entities.ArtifactType;
import acme.entities.Chimpum;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.helpers.CollectionHelper;
import acme.framework.services.AbstractListService;
import acme.roles.Inventor;

@Service
public class InventorChimpumListService implements AbstractListService<Inventor, Chimpum>{

	@Autowired
	protected InventorChimpumRepository repository;
	
	@Override
	public boolean authorise(final Request<Chimpum> request) {
		assert request != null;

		boolean result;
		int id;
		id = request.getModel().getInteger("masterId");
		
		Artifact artifact;
		artifact = this.repository.findArtifactById(id);
		
		result = (artifact != null &&(request.isPrincipal(artifact.getInventor())) && artifact.getArtifactType()==ArtifactType.TOOL);
		
		return result;
	}

	@Override
	public Collection<Chimpum> findMany(final Request<Chimpum> request) {
		assert request != null;
	
		Collection<Chimpum> result;
		int id;
		
		id = request.getModel().getInteger("masterId");
		result = this.repository.findChimpumByArtifactId(id);
		
		return result;
	}

	@Override
	public void unbind(final Request<Chimpum> request, final Chimpum entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "title", "creationMoment", "budget");
	}
	
	@Override
	public void unbind(final Request<Chimpum> request, final Collection<Chimpum> entities, final Model model) {
		assert request != null;
		assert !CollectionHelper.someNull(entities);
		assert model != null;
		
		model.setAttribute("masterId", request.getModel().getInteger("masterId"));
	}
}