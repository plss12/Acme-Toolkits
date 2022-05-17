package acme.features.inventor.artifact;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Artifact;
import acme.features.authenticated.moneyExchange.AuthenticatedMoneyExchangePerformService;
import acme.forms.MoneyExchange;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractShowService;
import acme.roles.Inventor;

@Service
public class InventorArtifactShowService implements AbstractShowService<Inventor, Artifact>{

	@Autowired
	protected AuthenticatedMoneyExchangePerformService moneyExchange;
	
	@Autowired
	protected InventorArtifactRepository repository;
	
	@Override
	public boolean authorise(final Request<Artifact> request) {
		assert request != null;
		
		boolean result;
		int id;
		
		Artifact artifact;
		
		id = request.getModel().getInteger("id");
		artifact = this.repository.findArtifactById(id);
		
		result = (artifact != null && (request.isPrincipal(artifact.getInventor())));
		
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
	public void unbind(final Request<Artifact> request, final Artifact entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		MoneyExchange exchange;
		final String defaultCurrency = this.repository.findDefaultCurrency();
		exchange = this.moneyExchange.computeMoneyExchange(entity.getRetailPrice(), defaultCurrency);
		
		request.unbind(entity, model, "artifactType", "name", "code", "technology", "description", 
			"retailPrice", "link", "inventor.userAccount.username", "isPublic");
		
		model.setAttribute("budgetExchange", exchange.getTarget());
		model.setAttribute("budgetExchangeDate", exchange.getDate());
	}
	
}
