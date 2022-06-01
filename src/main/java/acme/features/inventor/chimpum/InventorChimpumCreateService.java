package acme.features.inventor.chimpum;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Artifact;
import acme.entities.CHIMPUM;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractCreateService;
import acme.roles.Inventor;

@Service
public class InventorChimpumCreateService implements AbstractCreateService<Inventor, CHIMPUM>{

	@Autowired
	protected InventorChimpumRepository repository;
	
	@Override
	public boolean authorise(final Request<CHIMPUM> request) {
		assert request != null;
		
		boolean result;
		int id;
		id = request.getModel().getInteger("masterId");
		
		Artifact artifact;
		artifact = this.repository.findOneArtifactById(id);
		
		result = (artifact != null &&(request.isPrincipal(artifact.getInventor())));
		
		return result;
	}

	@Override
	public void bind(final Request<CHIMPUM> request, final CHIMPUM entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		request.bind(entity, errors, "creationMoment","title","description","startDate","finishDate","budget","link");
		
	}

	@Override
	public void unbind(final Request<CHIMPUM> request, final CHIMPUM entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "creationMoment","title","description","startDate","finishDate","budget","link");
		model.setAttribute("masterId", request.getModel().getAttribute("masterId"));
	}

	@Override
	public CHIMPUM instantiate(final Request<CHIMPUM> request) {
		final CHIMPUM result;
		int id;
		Artifact artifact;
		final Date actualDate;
		
		id = request.getModel().getInteger("masterId");
		artifact = this.repository.findOneArtifactById(id);
		actualDate = new Date();

		result = new CHIMPUM();
		result.setArtefact(artifact);
		result.setCreationMoment(actualDate);
		result.setCode("YY/MM/DD");
				
		return result;
	}

	@Override
	public void validate(final Request<CHIMPUM> request, final CHIMPUM entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
	}

	@Override
	public void create(final Request<CHIMPUM> request, final CHIMPUM entity) {
		assert request != null;
		assert entity != null;
		this.repository.save(entity);
	}

}
