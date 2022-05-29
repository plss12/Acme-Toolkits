package acme.features.inventor.chimpum;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Artifact;
import acme.entities.Chimpum;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
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
		
		Artifact artifact;
		
		id = request.getModel().getInteger("masterId");
		artifact = this.repository.findArtifactById(id);
		
		result = (artifact != null && (request.isPrincipal(artifact.getInventor())));
		
		return result;
	}

	@Override
	public Collection<Chimpum> findMany(final Request<Chimpum> request) {
		assert request != null;
	
		final Collection<Chimpum> result;
		final int artifactId= request.getModel().getInteger("masterId");
		result = this.repository.findChimpumByArtifactId(artifactId);
		
		return result;
	}

	@Override
	public void unbind(final Request<Chimpum> request, final Chimpum entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "title", "creationMoment", "budget");
	}
}