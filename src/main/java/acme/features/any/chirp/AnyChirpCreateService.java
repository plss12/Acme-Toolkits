package acme.features.any.chirp;

import org.springframework.beans.factory.annotation.Autowired;

import acme.entities.Chirp;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.roles.Any;
import acme.framework.services.AbstractCreateService;

public class AnyChirpCreateService implements AbstractCreateService<Any, Chirp>{
	
	// Internal State ----------------------------------------------------------
	
	@Autowired
	protected AnyChirpRepository repository;
	
	
	//AbstractCreateService interface

	@Override
	public boolean authorise(final Request<Chirp> request) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void bind(final Request<Chirp> request, final Chirp entity, final Errors errors) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void unbind(final Request<Chirp> request, final Chirp entity, final Model model) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Chirp instantiate(final Request<Chirp> request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void validate(final Request<Chirp> request, final Chirp entity, final Errors errors) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void create(final Request<Chirp> request, final Chirp entity) {
		// TODO Auto-generated method stub
		
	}
	
	//

}
