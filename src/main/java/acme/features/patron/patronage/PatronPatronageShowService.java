package acme.features.patron.patronage;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Patronage;
import acme.features.authenticated.moneyExchange.AuthenticatedMoneyExchangePerformService;
import acme.forms.MoneyExchange;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractShowService;
import acme.roles.Inventor;
import acme.roles.Patron;

@Service
public class PatronPatronageShowService implements AbstractShowService<Patron, Patronage>{

	// Internal state ---------------------------------------------------------
	
	@Autowired
	protected AuthenticatedMoneyExchangePerformService moneyExchange;
	
	@Autowired
	protected PatronPatronageRepository repository;
	
	@Override
	public boolean authorise(final Request<Patronage> request) {
		assert request != null;
		
		boolean result;
		int id;
		id = request.getModel().getInteger("id");
		
		Patronage patronage;
		patronage = this.repository.findPatronageById(id);
		
		result = (patronage != null &&(request.isPrincipal(patronage.getPatron())));
		
		return result;
	}

	@Override
	public Patronage findOne(final Request<Patronage> request) {
		assert request != null;

		Patronage result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findPatronageById(id);

		return result;
	}

	@Override
	public void unbind(final Request<Patronage> request, final Patronage entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		MoneyExchange exchange;
		final String defaultCurrency = this.repository.findDefaultCurrency();
		exchange = this.moneyExchange.computeMoneyExchange(entity.getBudget(), defaultCurrency);	
		
		final List<Inventor> inventorSelect = this.repository.findAllInventors();
		

		request.unbind(entity, model, "code", "status", "legalStuff", "budget", "startDate", "finishDate", "link",
			"inventor.userAccount.username", "inventor.company", "inventor.statement", "inventor.link", "isPublic");
		
		model.setAttribute("budgetExchange", exchange.getTarget());
		model.setAttribute("budgetExchangeDate", exchange.getDate());
		model.setAttribute("inventorSelect", inventorSelect);
	}

}
