package acme.features.any.artifactToolkit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.ArtifactToolkit;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.roles.Any;
import acme.framework.services.AbstractDeleteService;

@Service
public class AnyArtifactToolkitDeleteService implements AbstractDeleteService<Any, ArtifactToolkit>{

	@Autowired
	protected AnyArtifactToolkitRepository repository;
	
	@Override
	public boolean authorise(final Request<ArtifactToolkit> request) {
		assert request != null;
		
		final int id = request.getModel().getInteger("id");
		final ArtifactToolkit at= this.repository.findArtifactToolkitById(id);
		
		return at.getToolkit().getInventor().getUserAccount().getId() == request.getPrincipal().getAccountId();
	}

	@Override
	public void bind(final Request<ArtifactToolkit> request, final ArtifactToolkit entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "artifact.name", "artifact.code", "artifact.technology", "artifact.description", "artifact.retailPrice", "artifact.artifactType", "artifact.link", "artifactAmount");
		
	}

	@Override
	public void unbind(final Request<ArtifactToolkit> request, final ArtifactToolkit entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "artifact.name", "artifact.code", "artifact.technology", "artifact.description", "artifact.retailPrice", "artifact.artifactType", "artifact.link", "artifactAmount");
		
	}

	@Override
	public ArtifactToolkit findOne(final Request<ArtifactToolkit> request) {
		assert request != null;

		ArtifactToolkit result;
		int id;
		id=request.getModel().getInteger("id");
		result = this.repository.findArtifactToolkitById(id);
		return result;
	}

	@Override
	public void validate(final Request<ArtifactToolkit> request, final ArtifactToolkit entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		if(!errors.hasErrors("toolkit.isPublic")) {
			ArtifactToolkit existing;
			
			existing = this.repository.findArtifactToolkitById(request.getModel().getInteger("id"));
			errors.state(request, existing.getToolkit().isPublic() == false, "*", "any.artifact-toolkit.form.error.published");
		}
	}

	@Override
	public void delete(final Request<ArtifactToolkit> request, final ArtifactToolkit entity) {
		assert request != null;
		assert entity != null;
		 
		this.repository.delete(entity);	
	}

}
