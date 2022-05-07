package acme.features.any.artifact;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Artifact;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.roles.Any;
import acme.framework.services.AbstractShowService;

@Service
public class AnyArtifactShowService implements AbstractShowService<Any, Artifact>{
	// Internal state ---------------------------------------------------------

	@Autowired
	protected AnyArtifactRepository repo;

	@Override
	public boolean authorise(final Request<Artifact> request) {
		assert request != null;
		
		boolean result;
		final int id;
		id = request.getModel().getInteger("id");
		
		Artifact artifact;
		artifact = this.repo.findArtifactById(id);
		
		result = (artifact != null && artifact.isPublic());
		return result;
	}

	@Override
	public Artifact findOne(final Request<Artifact> request) {
		assert request != null;

		Artifact result;
		int id;
		id=request.getModel().getInteger("id");
		result = this.repo.findArtifactById(id);
		return result;
	}

	@Override
	public void unbind(final Request<Artifact> request, final Artifact entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "name", "code", "technology", "description", "retailPrice", "artifactType", "link");
	}


}
