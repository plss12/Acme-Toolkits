package acme.features.inventor.chimpum;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.CHIMPUM;
import acme.features.authenticated.moneyExchange.AuthenticatedMoneyExchangePerformService;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractUpdateService;
import acme.roles.Inventor;

@Service
public class InventorChimpumUpdateService implements AbstractUpdateService<Inventor, CHIMPUM>{
	
	@Autowired
	protected InventorChimpumRepository repository;
	
	@Autowired 
	AuthenticatedMoneyExchangePerformService moneyExchange;
	
	@Override
	public boolean authorise(final Request<CHIMPUM> request) {
		boolean result;
		int id;
		CHIMPUM chimpum;
		Inventor inventor;
		
		id = request.getModel().getInteger("id");
		chimpum = this.repository.findChimpumById(id);
		inventor = chimpum.getArtefact().getInventor();
		result = request.isPrincipal(inventor);
		
		return result;
	}

	@Override
	public void bind(final Request<CHIMPUM> request, final CHIMPUM entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		request.bind(entity, errors,"code","creationMoment","title","description","startDate","finishDate","budget","link");
		
	}

	@Override
	public void unbind(final Request<CHIMPUM> request, final CHIMPUM entity, final Model model) {
		
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model,"code","creationMoment","title","description","startDate","finishDate","budget","link","artefact.code");
		
	}

	@Override
	public CHIMPUM findOne(final Request<CHIMPUM> request) {
		assert request != null;
		
		CHIMPUM result;
		int id;
		id = request.getModel().getInteger("id");
		result = this.repository.findChimpumById(id);
		return result;
	}

	@Override
	public void validate(final Request<CHIMPUM> request, final CHIMPUM entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		
		assert errors != null;
		if(!errors.hasErrors("budget")) {
			final String entityCurrency;
			final Double amount;
			final String[] acceptedCurrencies;
			final List<String> currencies;
			
			entityCurrency = entity.getBudget().getCurrency();
			amount = entity.getBudget().getAmount();
			errors.state(request, amount > 0, "budget", "inventor.artifact.form.error.negative");
			acceptedCurrencies=this.repository.findAllAcceptedCurrencies().split(",");
			
			currencies = Arrays.asList(acceptedCurrencies);
			errors.state(request, currencies.contains(entityCurrency) , "budget", "inventor.chimpum.form.error.noAcceptedCurrency");
		}
		if(!errors.hasErrors("startDate")) {
			Calendar calendar;
			final Date minimunDate;
			calendar = new GregorianCalendar();
			calendar.add(Calendar.MONTH, 1);
			minimunDate = calendar.getTime();
			errors.state(request, entity.getStartDate().after(minimunDate), "startDate", "inventor.chimpum.form.error.startDate");
		}
		
		if(!errors.hasErrors("finishDate")) {
			Calendar calendar;
			Date minimunDate;
			
			calendar = new GregorianCalendar();
			calendar.setTime(entity.getStartDate());
			
			calendar.add(Calendar.WEEK_OF_YEAR, 1);
			minimunDate = calendar.getTime();
			
			errors.state(request, entity.getFinishDate().equals(minimunDate), "finishDate", "inventor.chimpum.form.error.finishDate");
		}
		
		if (!errors.hasErrors("code")) {
			CHIMPUM chimpum;
			chimpum=this.repository.findChimpumByCode(entity.getCode());
			errors.state(request, chimpum == null || chimpum.getId()==entity.getId(), "code", "inventor.chimpum.form.error.duplicated_code");
			errors.state(request, entity.getCode().substring(0, 9)
				.equals(this.repository.findChimpumById(request.getModel().getInteger("id")).getArtefact().getCode()) , "code", "inventor.chimpum.form.error.sameCode");
			final LocalDate dateObj = LocalDate.now();
			final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yy/MM/dd");
			errors.state(request, entity.getCode().substring(10,18)
				.equals(dateObj.format(formatter)), "code", "inventor.chimpum.form.error.correctDate");
			
		}
		
		
	}

	@Override
	public void update(final Request<CHIMPUM> request, final CHIMPUM entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
		
	}

}
