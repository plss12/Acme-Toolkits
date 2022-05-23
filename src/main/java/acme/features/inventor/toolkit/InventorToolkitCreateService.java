package acme.features.inventor.toolkit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Configuration;
import acme.entities.Toolkit;
import acme.features.SpamDetector.SpamDetector;
import acme.features.SpamDetector.SpamDetectorRepository;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractCreateService;
import acme.roles.Inventor;

@Service
public class InventorToolkitCreateService implements AbstractCreateService<Inventor, Toolkit>{

	@Autowired
	protected InventorToolkitRepository repository;
	@Autowired
	protected SpamDetectorRepository spamRepo;
	
	@Override
	public boolean authorise(final Request<Toolkit> request) {
		assert request != null;
		
		return true;
	}

	@Override
	public void bind(final Request<Toolkit> request, final Toolkit entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		request.bind(entity, errors, "code", "title", "description", "assemblyNotes", "link");
		
	}

	@Override
	public void unbind(final Request<Toolkit> request, final Toolkit entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "code", "title", "description", "assemblyNotes", "link");
		
	}

	@Override
	public Toolkit instantiate(final Request<Toolkit> request) {
		assert request != null;
		final Toolkit result;
		
		Inventor inventor;
		inventor = this.repository.findInventorByUserAccountId(request.getPrincipal().getAccountId());
		
		result = new Toolkit();
		result.setPublic(false);
		result.setInventor(inventor);
		
		return result;
	}

	@Override
	public void validate(final Request<Toolkit> request, final Toolkit entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		if (!errors.hasErrors("code")) {
			Toolkit otherToolkit;
			
			otherToolkit=this.repository.findToolkitByCode(entity.getCode());
			errors.state(request, otherToolkit == null || otherToolkit.getId()==entity.getId(), "code", "inventor.toolkit.form.error.duplicated_code");
		}
		
		final SpamDetector spamdetector = new SpamDetector();
		final Configuration config = this.spamRepo.findOneConf();
		
		if(!errors.hasErrors("title")) {
			errors.state(request, !spamdetector.containsSpam(config.getWeakSpam(),config.getWeakSpamTrheshold(),entity.getTitle())
				&& !spamdetector.containsSpam(config.getStrongSpam(),config.getStrongSpamTrheshold(),entity.getTitle()),
				"title", "administrator.toolkit.form.error.spam");
		}
		if(!errors.hasErrors("description")) {
			errors.state(request, !spamdetector.containsSpam(config.getWeakSpam(),config.getWeakSpamTrheshold(),entity.getDescription())
				&& !spamdetector.containsSpam(config.getStrongSpam(),config.getStrongSpamTrheshold(),entity.getDescription()),
				"description", "administrator.toolkit.form.error.spam");
		}
		if(!errors.hasErrors("assemblyNotes")) {
			errors.state(request, !spamdetector.containsSpam(config.getWeakSpam(),config.getWeakSpamTrheshold(),entity.getAssemblyNotes())
				&& !spamdetector.containsSpam(config.getStrongSpam(),config.getStrongSpamTrheshold(),entity.getAssemblyNotes()),
				"assemblyNotes", "administrator.toolkit.form.error.spam");
		}
		
	}

	@Override
	public void create(final Request<Toolkit> request, final Toolkit entity) {
		assert request != null;
		assert entity != null;
		
		
		this.repository.save(entity);
		
	}

	

}
