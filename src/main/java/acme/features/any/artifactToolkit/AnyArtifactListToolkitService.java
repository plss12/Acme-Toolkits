package acme.features.any.artifactToolkit;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.ArtifactToolkit;
import acme.entities.Toolkit;
import acme.features.any.artifact.AnyArtifactRepository;
import acme.features.any.toolkit.AnyToolkitRepository;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.roles.Any;
import acme.framework.services.AbstractListService;

@Service
public class AnyArtifactListToolkitService implements AbstractListService<Any, ArtifactToolkit> {
	// Internal state ---------------------------------------------------------

		@Autowired
		protected AnyArtifactRepository repo;
		
		@Autowired
		protected AnyToolkitRepository toolkitRepo;
		
		
	// AbstractListService<Any, Artifact> interface --------------

	@Override
	public boolean authorise(final Request<ArtifactToolkit> request) {
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
	public Collection<ArtifactToolkit> findMany(final Request<ArtifactToolkit> request) {
		assert request != null;
		Collection<ArtifactToolkit> result;
		final int id= request.getModel().getInteger("masterId");
		result = this.repo.findArtifactsToolkitsByToolkit(id);
		return result;
		
	}

	@Override
	public void unbind(final Request<ArtifactToolkit> request, final ArtifactToolkit entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "artifact.name", "artifact.retailPrice", "artifact.artifactType", "artifactAmount");
	}
	

}
