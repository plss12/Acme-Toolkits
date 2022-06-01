package acme.features.patron.patronage;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Patronage;
import acme.features.authenticated.moneyExchange.AuthenticatedMoneyExchangePerformService;
import acme.forms.MoneyExchange;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractUpdateService;
import acme.roles.Inventor;
import acme.roles.Patron;

@Service	
public class PatronPatronageUpdateService implements AbstractUpdateService<Patron, Patronage>{

	@Autowired
	protected AuthenticatedMoneyExchangePerformService moneyExchange;
	
	@Autowired
	protected PatronPatronageRepository repository;
	
	@Override
	public boolean authorise(final Request<Patronage> request) {
		boolean result;
		int id;
		Patronage patronage;
		Patron patron;
		
		id = request.getModel().getInteger("id");
		patronage = this.repository.findPatronageById(id);
		patron = patronage.getPatron();
		result = request.isPrincipal(patron)&&(!patronage.isPublic());
		
		return result;
	}

	@Override
	public void bind(final Request<Patronage> request, final Patronage entity, final Errors errors) {
		
		final String inventorUsername = String.valueOf(request.getModel().getAttribute("inventorUsername"));
		final Inventor inventor = this.repository.findInventorByUsername(inventorUsername).get(0);
		errors.state(request, inventor!=null, "*", "patron.patronage.form.error.invalidInventor");
		entity.setInventor(inventor);
		
		request.bind(entity, errors, "code", "legalStuff", "budget", "startDate", "finishDate", "link");
		
	}

	@Override
	public void unbind(final Request<Patronage> request, final Patronage entity, final Model model) {
		
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
	public void validate(final Request<Patronage> request, final Patronage entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		if(!errors.hasErrors("code")) {
			Patronage existing;
			
			existing = this.repository.findOnePatronageByCode(entity.getCode());
			errors.state(request, existing==null||entity.getCode().equals(existing.getCode()), "code", "patron.patronage.form.error.duplicated");
		}
		
		if(!errors.hasErrors("startDate")) {
			Calendar calendar;
			final Date minimunDate;
			
			calendar = new GregorianCalendar();
			calendar.add(Calendar.MONTH, 1);
			minimunDate = calendar.getTime();
			
			final Date previousDate = this.repository.findPatronageById(entity.getId()).getStartDate();
			errors.state(request, entity.getStartDate().after(minimunDate)||(entity.getStartDate().equals(previousDate)), "startDate", "patron.patronage.form.error.too-close-start");
		}
		
		if(!errors.hasErrors("budget")) {
			errors.state(request, entity.getBudget().getAmount() > 0, "budget", "patron.patronage.form.error.negative");
			
			final String entityCurrency = entity.getBudget().getCurrency();			
			final String[] acceptedCurrencies=this.repository.findAllAcceptedCurrencies().split(",");
			final List<String> currencies= Arrays.asList(acceptedCurrencies);			
			errors.state(request, currencies.contains(entityCurrency) , "budget", "patron.patronage.form.error.noAcceptedCurrency");
		}
		
		if(!errors.hasErrors("finishDate")) {
			Calendar calendar;
			Date minimunDate;
			
			calendar = new GregorianCalendar();
			calendar.setTime(entity.getStartDate());
			
			calendar.add(Calendar.MONTH, 1);
			minimunDate = calendar.getTime();
			
			errors.state(request, entity.getFinishDate().after(minimunDate), "finishDate", "patron.patronage.form.error.too-close");
		}
		
	}

	@Override
	public void update(final Request<Patronage> request, final Patronage entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
		
	}

}
