package acme.features.any.chirp;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Chirp;
import acme.features.inventor.toolkit.InventorToolkitRepository;
import acme.features.patron.patronage.PatronPatronageRepository;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.roles.Any;
import acme.framework.services.AbstractCreateService;

@Service
public class AnyChirpCreateService implements AbstractCreateService<Any, Chirp>{
	
	// Internal State ----------------------------------------------------------
	
	@Autowired
	protected AnyChirpRepository chirpRepository;
	
	@Autowired
	protected InventorToolkitRepository inventorRepository;
	
	@Autowired
	protected PatronPatronageRepository patronRepository;
	
	
	//AbstractCreateService interface

	@Override
	public boolean authorise(final Request<Chirp> request) {
		assert request != null;
		
		return true;
	}

	@Override
	public void bind(final Request<Chirp> request, final Chirp entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		final Date actualDate = new Date();
		entity.setCreationMoment(actualDate);
		
		request.bind(entity, errors, "title", "body", "email");
		
	}

	@Override
	public void unbind(final Request<Chirp> request, final Chirp entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model,"title", "body", "email");
		model.setAttribute("confirm", "false");
		
	}

	@Override
	public Chirp instantiate(final Request<Chirp> request) {
		assert request != null;
		final Chirp result = new Chirp();
		
		final Date actualDate = new Date();
		result.setCreationMoment(actualDate);
		
		final String username = request.getPrincipal().getUsername();
		result.setAuthor(username);
		
		return result;
	}

	@Override
	public void validate(final Request<Chirp> request, final Chirp entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		final Boolean confirmed = request.getModel().getBoolean("confirm");
		errors.state(request, confirmed, "confirm", "any.chirp.form.error.confirm");
		
	}

	@Override
	public void create(final Request<Chirp> request, final Chirp entity) {
		assert request != null;
		assert entity != null;

		this.chirpRepository.save(entity);
		
	}
	
}