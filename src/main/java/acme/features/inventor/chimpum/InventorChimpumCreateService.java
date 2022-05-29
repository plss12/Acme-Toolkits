package acme.features.inventor.chimpum;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Artifact;
import acme.entities.Chimpum;
import acme.entities.Configuration;
import acme.features.SpamDetector.SpamDetector;
import acme.features.SpamDetector.SpamDetectorRepository;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractCreateService;
import acme.roles.Inventor;

@Service
public class InventorChimpumCreateService implements AbstractCreateService<Inventor, Chimpum>{

	@Autowired
	protected InventorChimpumRepository repository;
	
	@Autowired
	protected SpamDetectorRepository spamRepo;
	
	@Override
	public boolean authorise(final Request<Chimpum> request) {
		assert request != null;
		
		return true;
	}

	@Override
	public void bind(final Request<Chimpum> request, final Chimpum entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		request.bind(entity, errors, "title", "code", "creationMoment", "description", "budget", "link", "startDate", "finishDate");
	}

	@Override
	public void unbind(final Request<Chimpum> request, final Chimpum entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "title", "code", "creationMoment", "description", "budget", "link", "startDate", "finishDate");
		
	}

	@Override
	public Chimpum instantiate(final Request<Chimpum> request) {
		assert request != null;
		
		Chimpum result;
		int id;
		Artifact artifact;
		
		id = request.getModel().getInteger("id");
		artifact = this.repository.findArtifactById(id);
		result = new Chimpum();
		result.setArtifact(artifact);;
		
		return result;
	}

	@Override
	public void validate(final Request<Chimpum> request, final Chimpum entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		if(!errors.hasErrors("budget")) {
			final String entityCurrency = entity.getBudget().getCurrency();
			final Double amount = entity.getBudget().getAmount();
			errors.state(request, amount > 0, "retailPrice", "inventor.artifact.form.error.negative");
			final String[] acceptedCurrencies=this.repository.findAllAcceptedCurrencies().split(",");
			
			final List<String> currencies = Arrays.asList(acceptedCurrencies);
			errors.state(request, currencies.contains(entityCurrency) , "retailPrice", "inventor.chimpum.form.error.noAcceptedCurrency");
		}
		
		final SpamDetector spamdetector = new SpamDetector();
		final Configuration config = this.spamRepo.findOneConf();
		
		if(!errors.hasErrors("title")) {
			errors.state(request, !spamdetector.containsSpam(config.getWeakSpam(),config.getWeakSpamTrheshold(),entity.getTitle())
				&& !spamdetector.containsSpam(config.getStrongSpam(),config.getStrongSpamTrheshold(),entity.getTitle()),
				"name", "inventor.chimpum.form.error.spam");
		}
		if(!errors.hasErrors("description")) {
			errors.state(request, !spamdetector.containsSpam(config.getWeakSpam(),config.getWeakSpamTrheshold(),entity.getDescription())
				&& !spamdetector.containsSpam(config.getStrongSpam(),config.getStrongSpamTrheshold(),entity.getDescription()),
				"description", "inventor.chimpum.form.error.spam");
		}
		
	}

	@Override
	public void create(final Request<Chimpum> request, final Chimpum entity) {
		assert request != null;
		assert entity != null;
		
		this.repository.save(entity);
	}
}