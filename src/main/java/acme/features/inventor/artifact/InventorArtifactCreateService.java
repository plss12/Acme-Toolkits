package acme.features.inventor.artifact;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Artifact;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractCreateService;
import acme.roles.Inventor;

@Service
public class InventorArtifactCreateService implements AbstractCreateService<Inventor, Artifact>{

	@Autowired
	protected InventorArtifactRepository repository;
	
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
		
		request.bind(entity, errors, "artifactType", "name", "code", "technology", "description", "retailPrice", "link", "inventor.userAccount.username");
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
			
			final List<String> currencies= new ArrayList<String>();
			final String[] acceptedCurrencies=this.repository.findAllAcceptedCurrencies().split(",");
			for (final String currency : acceptedCurrencies){
			    currencies.add(currency);
			    }
			errors.state(request, currencies.contains(entityCurrency) , "retailPrice", "inventor.artifact.form.error.noAcceptedCurrency");
		}
	}

	@Override
	public void unbind(final Request<Artifact> request, final Artifact entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "artifactType", "name", "code", "technology", "description", "retailPrice", "link", "inventor.userAccount.username");		
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
