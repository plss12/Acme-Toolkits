package acme.features.any.artifactToolkit;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Artifact;
import acme.entities.ArtifactToolkit;
import acme.entities.Toolkit;
import acme.features.authenticated.inventor.AuthenticatedInventorRepository;
import acme.features.inventor.artifact.InventorArtifactRepository;
import acme.features.inventor.toolkit.InventorToolkitRepository;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.roles.Any;
import acme.framework.services.AbstractCreateService;

@Service
public class AnyArtifactToolkitCreateService implements AbstractCreateService<Any, ArtifactToolkit>{

	@Autowired
	protected AnyArtifactToolkitRepository repository;
	
	@Autowired
	protected AuthenticatedInventorRepository inventorRepository;
	
	@Autowired
	protected InventorToolkitRepository toolkitRepository;
	
	@Autowired
	protected InventorArtifactRepository artifactRepository;
	
	@Override
	public boolean authorise(final Request<ArtifactToolkit> request) {
		assert request != null;
	
		return this.inventorRepository.findInventorByUserAccountId(request.getPrincipal().getAccountId()) != null;
	}
	

	@Override
	public void bind(final Request<ArtifactToolkit> request, final ArtifactToolkit entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		request.bind(entity, errors, "artifactAmount", "artifact", "toolkit");
		
	}

	@Override
	public void unbind(final Request<ArtifactToolkit> request, final ArtifactToolkit entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		final Collection<Artifact> artifactSelected = this.artifactRepository.findAllPublics(true);
		final Collection<Toolkit> toolkitSelected = this.toolkitRepository.findToolkitsByInventorUsername(request.getPrincipal().getUsername());
		
		model.setAttribute("artifactSelected", artifactSelected);
		model.setAttribute("toolkitSelected", toolkitSelected);
		
		request.unbind(entity, model, "artifactAmount", "artifact", "toolkit");
		
	}

	@Override
	public ArtifactToolkit instantiate(final Request<ArtifactToolkit> request) {
		assert request != null;
		
		final ArtifactToolkit result = new ArtifactToolkit();
		
		return result;
		
	}

	@Override
	public void validate(final Request<ArtifactToolkit> request, final ArtifactToolkit entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		if(!errors.hasErrors("artifactAmount")) {
			errors.state(request, entity.getArtifactAmount() <= 0, "artifactAmount", "any.artifact-toolkit.form.error.negative-amount");
		}
		
	}

	@Override
	public void create(final Request<ArtifactToolkit> request, final ArtifactToolkit entity) {
		assert request != null;
		assert entity != null;
		
		this.repository.save(entity);
		
	}

}
