package acme.features.inventor.toolkit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.ArtifactToolkit;
import acme.entities.Toolkit;
import acme.features.any.artifactToolkit.AnyArtifactToolkitRepository;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractDeleteService;
import acme.roles.Inventor;

@Service
public class InventorToolkitDeleteService implements AbstractDeleteService<Inventor, Toolkit>{

	@Autowired
	protected InventorToolkitRepository repository;
	
	@Autowired
	protected AnyArtifactToolkitRepository ATrepository;
	
	@Override
	public boolean authorise(final Request<Toolkit> request) {
		assert request != null;
		
		boolean result;
		int id;
		Toolkit toolkit;
		Inventor inventor;
		
		id = request.getModel().getInteger("id");
		toolkit = this.repository.findToolkitById(id);
		inventor = toolkit.getInventor();
		result = !toolkit.isPublic() && request.isPrincipal(inventor);
		
		return result;
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
	public Toolkit findOne(final Request<Toolkit> request) {
		assert request != null;
		
		int id;
		Toolkit result;
		
		id = request.getModel().getInteger("id");
		result = this.repository.findToolkitById(id);
		
		return result;
	}

	@Override
	public void validate(final Request<Toolkit> request, final Toolkit entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
	}

	@Override
	public void delete(final Request<Toolkit> request, final Toolkit entity) {
		assert request != null;
		assert entity != null;
		
		 final int id = request.getModel().getInteger("id");
		 
		 for(final ArtifactToolkit at : this.ATrepository.findArtifactsToolkitsByToolkit(id)) {
			 this.ATrepository.delete(at);
		 }
		 
		this.repository.delete(entity);	
		
	}

}
