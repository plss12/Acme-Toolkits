package acme.features.any.toolkit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Toolkit;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.roles.Any;
import acme.framework.services.AbstractShowService;

@Service
public class AnyToolkitShowService implements AbstractShowService<Any, Toolkit>{
	
	// Internal state -------------------------------------------------------------------
	
	@Autowired
	protected AnyToolkitRepository repository;
	
	// AbstractShowService<Authenticated, Toolkit> interface ----------------------------

	@Override
	public boolean authorise(final Request<Toolkit> request) {
		assert request != null;
		
		Toolkit toolkit;
		boolean result;
		int id;
		id = request.getModel().getInteger("id");
		toolkit = this.repository.findOneToolkitById(id);
		result = (toolkit != null && (toolkit.getIsPublic()));
		
		return result;
	}

	@Override
	public Toolkit findOne(final Request<Toolkit> request) {
		assert request != null;
		
		Toolkit result;
		int id;
		
		id = request.getModel().getInteger("id");
		result = this.repository.findOneToolkitById(id);
		
		return result;
	}

	@Override
	public void unbind(final Request<Toolkit> request, final Toolkit entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		model.setAttribute("id", entity.getId());
		request.unbind(entity, model, "title", "description", "assemblyNotes", "link");	
	}
}