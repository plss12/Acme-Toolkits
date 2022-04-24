package acme.features.any.artifact;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Artifact;
import acme.entities.Toolkit;
import acme.features.any.toolkit.AnyToolkitRepository;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.roles.Any;
import acme.framework.services.AbstractListService;

@Service
public class AnyArtifactListToolkitService implements AbstractListService<Any, Artifact> {
	// Internal state ---------------------------------------------------------

		@Autowired
		protected AnyArtifactRepository repo;
		
		@Autowired
		protected AnyToolkitRepository toolkitRepo;
		
		
	// AbstractListService<Any, Artifact> interface --------------

	@Override
	public boolean authorise(final Request<Artifact> request) {
		assert request != null;
		
		boolean result;
		int masterId;
		Toolkit toolkit;
		
		masterId = request.getModel().getInteger("masterId");
		toolkit = this.toolkitRepo.findOneToolkitById(masterId);
		
		result = (toolkit != null);
		
		return result;
	}
	

	@Override
	public Collection<Artifact> findMany(final Request<Artifact> request) {
		assert request != null;
		Collection<Artifact> result;
		final int id= request.getModel().getInteger("masterId");
		result = this.repo.findArtifactsByToolkit(id);
		return result;
		
	}

	@Override
	public void unbind(final Request<Artifact> request, final Artifact entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "name", "retailPrice", "artifactType");
	}
	

}
