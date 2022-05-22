package acme.features.any.artifactToolkit;

import java.util.List;

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
		
		final String artifactc = String.valueOf(request.getModel().getAttribute("artifact.code"));
		final String toolkitc = String.valueOf(request.getModel().getAttribute("toolkit.code"));
		
		final Artifact artifact = this.artifactRepository.findArtifactByCode(artifactc);
		final Toolkit toolkit = this.toolkitRepository.findToolkitByCode(toolkitc);
		
		entity.setArtifact(artifact);
		entity.setToolkit(toolkit);
		
		request.bind(entity, errors, "artifactAmount", "artifact.code", "toolkit.code");		
	}

	@Override
	public void unbind(final Request<ArtifactToolkit> request, final ArtifactToolkit entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		final List<Artifact> artifactSelected = this.artifactRepository.findAllPublics(true);
		final List<Toolkit> toolkitSelected = this.toolkitRepository.findPublicToolkitsByInventorUsername(request.getPrincipal().getUsername(), false);
		
		model.setAttribute("artifactSelected", artifactSelected);
		model.setAttribute("toolkitSelected", toolkitSelected);
		
		request.unbind(entity, model, "artifactAmount", "artifact.code", "toolkit.code");
		
	}

	@Override
	public ArtifactToolkit instantiate(final Request<ArtifactToolkit> request) {
		assert request != null;
		
		return new ArtifactToolkit();
		
	}

	@Override
	public void validate(final Request<ArtifactToolkit> request, final ArtifactToolkit entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		if(!errors.hasErrors("artifactAmount")) {
			errors.state(request, entity.getArtifactAmount() > 0, "artifactAmount", "any.artifact-toolkit.form.error.negative-amount");
		}
		
	}

	@Override
	public void create(final Request<ArtifactToolkit> request, final ArtifactToolkit entity) {
		assert request != null;
		assert entity != null;
		
		this.repository.save(entity);
		
	}

}
