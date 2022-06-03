package acme.features.inventor.artifact;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Artifact;
import acme.entities.Misit;
import acme.features.inventor.misit.InventorMisitRepository;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractDeleteService;
import acme.roles.Inventor;

@Service
public class InventorArtifactDeleteService implements AbstractDeleteService<Inventor, Artifact>{

	@Autowired
	protected InventorArtifactRepository repository;
	
	@Autowired
	protected InventorMisitRepository chimpumRepository;
	
	@Override
	public boolean authorise(final Request<Artifact> request) {
		assert request != null;
		
		boolean result;
		int id;
		Artifact artifact;
		Inventor inventor;
		
		
		id=request.getModel().getInteger("id");
		artifact=this.repository.findArtifactById(id);
		inventor=artifact.getInventor();
		result=!artifact.isPublic()&&request.isPrincipal(inventor);
		
		return result;
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
	public void bind(final Request<Artifact> request, final Artifact entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		request.bind(entity, errors, "artifactType", "name", "code", "technology", "description", "retailPrice", "link");
	}
	
	@Override
	public void validate(final Request<Artifact> request, final Artifact entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		if(!errors.hasErrors("publish")) {
			boolean publish;
			publish=this.repository.findById(entity.getId()).isPresent();
			errors.state(request, publish, "publish", "inventor.artifact.form.error.publish");
		}
	}

	@Override
	public void unbind(final Request<Artifact> request, final Artifact entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "artifactType", "name", "code", "technology", "description", "retailPrice", "link");
		
	}

	@Override
	public void delete(final Request<Artifact> request, final Artifact entity) {
		assert request != null;
		assert entity != null;
		
		final Collection<Misit> chimpums;
		int id;

		id = request.getModel().getInteger("id");
		chimpums = this.chimpumRepository.findAllMisitFromArtefact(id);

		for(final Misit chimpum : chimpums) {
			this.chimpumRepository.delete(chimpum);
		}
		
		this.repository.delete(entity);		
	}
}
