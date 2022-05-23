package acme.features.inventor.artifact;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Artifact;
import acme.entities.Configuration;
import acme.features.SpamDetector.SpamDetector;
import acme.features.SpamDetector.SpamDetectorRepository;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractCreateService;
import acme.roles.Inventor;

@Service
public class InventorArtifactCreateService implements AbstractCreateService<Inventor, Artifact>{

	@Autowired
	protected InventorArtifactRepository repository;
	@Autowired
	protected SpamDetectorRepository spamRepo;
	
	@Override
	public boolean authorise(final Request<Artifact> request) {
		assert request != null;
		
		return true;
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
		if (!errors.hasErrors("code")) {
			Artifact otherArtifact;
			
			otherArtifact=this.repository.findArtifactByCode(entity.getCode());
			errors.state(request, otherArtifact == null || otherArtifact.getId()==entity.getId(), "code", "inventor.artifact.form.error.duplicated_code");
		}
		
		if(!errors.hasErrors("retailPrice")) {
			final String entityCurrency = entity.getRetailPrice().getCurrency();
			final Double amount = entity.getRetailPrice().getAmount();
			errors.state(request, amount > 0, "retailPrice", "inventor.artifact.form.error.negative");
			final String[] acceptedCurrencies=this.repository.findAllAcceptedCurrencies().split(",");
			
			final List<String> currencies = Arrays.asList(acceptedCurrencies);
			errors.state(request, currencies.contains(entityCurrency) , "retailPrice", "inventor.artifact.form.error.noAcceptedCurrency");
		}
		
		final SpamDetector spamdetector = new SpamDetector();
		final Configuration config = this.spamRepo.findOneConf();
		
		if(!errors.hasErrors("name")) {
			errors.state(request, !spamdetector.containsSpam(config.getWeakSpam(),config.getWeakSpamTrheshold(),entity.getName())
				&& !spamdetector.containsSpam(config.getStrongSpam(),config.getStrongSpamTrheshold(),entity.getName()),
				"name", "inventor.artifact.form.error.spam");
		}
		if(!errors.hasErrors("description")) {
			errors.state(request, !spamdetector.containsSpam(config.getWeakSpam(),config.getWeakSpamTrheshold(),entity.getDescription())
				&& !spamdetector.containsSpam(config.getStrongSpam(),config.getStrongSpamTrheshold(),entity.getDescription()),
				"description", "inventor.artifact.form.error.spam");
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
	public Artifact instantiate(final Request<Artifact> request) {
		assert request != null;
		
		Artifact result;
		int id;
		Inventor inventor;
		
		id = request.getPrincipal().getActiveRoleId();
		inventor = this.repository.findInventorById(id);
		result = new Artifact();
		result.setInventor(inventor);
		result.setPublic(false);
		
		return result;
	}

	@Override
	public void create(final Request<Artifact> request, final Artifact entity) {
		assert request != null;
		assert entity != null;
		
		this.repository.save(entity);		
	}
}
