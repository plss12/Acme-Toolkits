package acme.features.inventor.misit;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Misit;
import acme.features.authenticated.moneyExchange.AuthenticatedMoneyExchangePerformService;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractUpdateService;
import acme.roles.Inventor;

@Service
public class InventorMisitUpdateService implements AbstractUpdateService<Inventor, Misit>{
	
	@Autowired
	protected InventorMisitRepository repository;
	
	@Autowired 
	AuthenticatedMoneyExchangePerformService moneyExchange;
	
	@Override
	public boolean authorise(final Request<Misit> request) {
		boolean result;
		int id;
		Misit chimpum;
		Inventor inventor;
		
		id = request.getModel().getInteger("id");
		chimpum = this.repository.findMisitById(id);
		inventor = chimpum.getArtefact().getInventor();
		result = request.isPrincipal(inventor);
		
		return result;
	}

	@Override
	public void bind(final Request<Misit> request, final Misit entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		request.bind(entity, errors,"code","creationMoment","subject","explanation","startDate","finishDate","quantity","additionalInfo");
		
	}

	@Override
	public void unbind(final Request<Misit> request, final Misit entity, final Model model) {
		
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model,"code","creationMoment","subject","explanation","startDate","finishDate","quantity","additionalInfo","artefact.code");
		
	}

	@Override
	public Misit findOne(final Request<Misit> request) {
		assert request != null;
		
		Misit result;
		int id;
		id = request.getModel().getInteger("id");
		result = this.repository.findMisitById(id);
		return result;
	}

	@Override
	public void validate(final Request<Misit> request, final Misit entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		
		assert errors != null;
		if(!errors.hasErrors("quantity")) {
			final String entityCurrency;
			final Double amount;
			final String[] acceptedCurrencies;
			final List<String> currencies;
			
			entityCurrency = entity.getQuantity().getCurrency();
			amount = entity.getQuantity().getAmount();
			errors.state(request, amount > 0, "budget", "inventor.artifact.form.error.negative");
			acceptedCurrencies=this.repository.findAllAcceptedCurrencies().split(",");
			
			currencies = Arrays.asList(acceptedCurrencies);
			errors.state(request, currencies.contains(entityCurrency) , "quantity", "inventor.misit.form.error.noAcceptedCurrency");
		}
		if(!errors.hasErrors("startDate")) {
			Calendar calendar;
			final Date minimunDate;
			calendar = new GregorianCalendar();
			calendar.add(Calendar.MONTH, 1);
			minimunDate = calendar.getTime();
			errors.state(request, entity.getStartDate().after(minimunDate), "startDate", "inventor.misit.form.error.startDate");
		}
		
		if(!errors.hasErrors("finishDate")) {
			Calendar calendar;
			Date minimunDate;
			
			calendar = new GregorianCalendar();
			calendar.setTime(entity.getStartDate());
			
			calendar.add(Calendar.WEEK_OF_YEAR, 1);
			minimunDate = calendar.getTime();
			
			errors.state(request, entity.getFinishDate().equals(minimunDate), "finishDate", "inventor.misit.form.error.finishDate");
		}
		
		if (!errors.hasErrors("code")) {
			Misit misit;
			misit=this.repository.findMisitByCode(entity.getCode());
			errors.state(request, misit == null || misit.getId()==entity.getId(), "code", "inventor.misit.form.error.duplicated_code");
		}
		
		
	}

	@Override
	public void update(final Request<Misit> request, final Misit entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
		
	}

}
