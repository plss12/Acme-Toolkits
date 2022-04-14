package acme.features.authenticated.inventor.artifact;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Artifact;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.roles.Authenticated;
import acme.framework.services.AbstractShowService;

@Service
public class InventorArtifactShowService implements AbstractShowService<Authenticated, Artifact>{

	@Autowired
	protected InventorArtifactRepository repository;
	
	@Override
	public boolean authorise(final Request<Artifact> request) {
		assert request != null;
		return true;
	}

	@Override
	public Artifact findOne(final Request<Artifact> request) {
		assert request != null;
		
		int id;
		Artifact result;
		
		id = request.getModel().getInteger("id");
		result = this.repository.findArtifactById(id);
		
		return result;
	}

	@Override
	public void unbind(final Request<Artifact> request, final Artifact entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "artifact_type", "name", "code", "technology", "description", 
			"retail_price.amount", "retail_price.currency", "link");
		model.setAttribute("readonly",true);
		model.setAttribute("confirmation", false);
	}
	
}
