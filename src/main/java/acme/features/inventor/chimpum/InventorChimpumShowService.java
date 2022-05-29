package acme.features.inventor.chimpum;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Chimpum;
import acme.features.authenticated.moneyExchange.AuthenticatedMoneyExchangePerformService;
import acme.forms.MoneyExchange;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractShowService;
import acme.roles.Inventor;

@Service
public class InventorChimpumShowService implements AbstractShowService<Inventor, Chimpum>{

	@Autowired
	protected AuthenticatedMoneyExchangePerformService moneyExchange;
	
	@Autowired
	protected InventorChimpumRepository repository;
	
	@Override
	public boolean authorise(final Request<Chimpum> request) {
		assert request != null;
		
		boolean result;
		int id;
		
		Chimpum chimpum;
		
		id = request.getModel().getInteger("id");
		chimpum = this.repository.findChimpumById(id);
		
		result = (chimpum != null && (request.isPrincipal(chimpum.getArtifact().getInventor())));
		
		return result;
	}

	@Override
	public Chimpum findOne(final Request<Chimpum> request) {
		assert request != null;
		
		int id;
		Chimpum result;
		
		id = request.getModel().getInteger("id");
		result = this.repository.findChimpumById(id);
		
		return result;
	}

	@Override
	public void unbind(final Request<Chimpum> request, final Chimpum entity, final Model model) {
		MoneyExchange exchange;
		final String defaultCurrency = this.repository.findDefaultCurrency();
		exchange = this.moneyExchange.computeMoneyExchange(entity.getBudget(), defaultCurrency);
		
		request.unbind(entity, model, "title", "code", "creationMoment", "description", "budget", 
			"link", "startDate", "finishDate");
		
		model.setAttribute("budgetExchange", exchange.getTarget());
		model.setAttribute("budgetExchangeDate", exchange.getDate());
	}
}