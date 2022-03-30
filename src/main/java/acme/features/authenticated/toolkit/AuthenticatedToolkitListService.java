package acme.features.authenticated.toolkit;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Toolkit;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.roles.Authenticated;
import acme.framework.services.AbstractListService;

@Service
public class AuthenticatedToolkitListService implements AbstractListService<Authenticated, Toolkit>{
	
	// Internal state -------------------------------------------------------------------
	
	@Autowired
	protected AuthenticatedToolkitRepository repository;
	
	// AbstractListService<Authenticated, Toolkit> interface ----------------------------
	
	@Override
	public boolean authorise(final Request<Toolkit> request) {
		assert request != null;
		
		return true;
	}
	
	@Override
	public void unbind(final Request<Toolkit> request, final Toolkit entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "title", "description", "assemblyNotes", "link", "artifactToolkits");
	}
	
	/*@Override 
	public Collection<Toolkit> findToolkitsByArtifact(final Request<Toolkit> request, final ArtifactToolkit artifactToolkit) {
		assert request != null;
		assert artifactToolkit != null;
		
		Collection<Toolkit> result;
		final ArtifactToolkit artifact = artifactToolkit;
		
		result = this.repository.findAllToolkitsWithComponentOrTool(artifact);
		
		return result;		
	}*/

	@Override
	public Collection<Toolkit> findMany(final Request<Toolkit> request) {
		assert request != null;
		
		Collection<Toolkit> result;
		
		result = this.repository.findAllToolkits();
		
		return result;
	}

}
