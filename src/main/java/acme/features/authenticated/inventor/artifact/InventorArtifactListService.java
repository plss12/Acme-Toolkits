package acme.features.authenticated.inventor.artifact;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Artifact;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.roles.Authenticated;
import acme.framework.services.AbstractListService;

@Service
public class InventorArtifactListService implements AbstractListService<Authenticated, Artifact>{

	@Autowired
	protected InventorArtifactRepository repository;
	
	@Override
	public boolean authorise(final Request<Artifact> request) {
		assert request != null;
		return true;
	}

	@Override
	public Collection<Artifact> findMany(final Request<Artifact> request) {
		assert request != null;
		
		final Collection<Artifact> result;
		final int userAccountId = request.getPrincipal().getAccountId();
		final int inventorId = this.repository.findInventorByUserAccountId(userAccountId).getId();
		result = this.repository.findArtifactsByInventorId(inventorId);
		
		return result;
	}

	@Override
	public void unbind(final Request<Artifact> request, final Artifact entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "artifactType", "name", "code", "technology", "description", 
			"retailPrice.amount", "retailPrice.currency", "link");
		
	}
}
