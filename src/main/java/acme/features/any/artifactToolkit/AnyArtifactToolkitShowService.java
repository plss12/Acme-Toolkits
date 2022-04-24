package acme.features.any.artifactToolkit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.ArtifactToolkit;
import acme.features.any.artifact.AnyArtifactRepository;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.roles.Any;
import acme.framework.services.AbstractShowService;

@Service
public class AnyArtifactToolkitShowService implements AbstractShowService<Any,ArtifactToolkit> {
	
	@Autowired
	protected AnyArtifactRepository repo;

	@Override
	public boolean authorise(final Request<ArtifactToolkit> request) {
		assert request != null;
		return true;
	}

	@Override
	public ArtifactToolkit findOne(final Request<ArtifactToolkit> request) {
		assert request != null;

		ArtifactToolkit result;
		int id;
		id=request.getModel().getInteger("id");
		result = this.repo.findArtifactToolkitById(id);
		return result;
	}

	@Override
	public void unbind(final Request<ArtifactToolkit> request, final ArtifactToolkit entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "artifact.name", "artifact.code", "artifact.technology", "artifact.description", "artifact.retailPrice", "artifact.artifactType", "artifact.link", "artifactAmount");
		
	}

}
