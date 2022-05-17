package acme.features.patron.patronage;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Patronage;
import acme.entities.PatronageStatus;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractCreateService;
import acme.roles.Inventor;
import acme.roles.Patron;

@Service
public class PatronPatronageCreateService implements AbstractCreateService<Patron, Patronage>{

	@Autowired
	protected PatronPatronageRepository repository;
	
	@Override
	public boolean authorise(final Request<Patronage> request) {
		assert request != null;
				
		return true;
	}
	
	@Override
	public Patronage instantiate(final Request<Patronage> request) {
		assert request != null;
		
		final Patronage result;
		Patron patron;
		
		patron = this.repository.findOnePatronById(request.getPrincipal().getActiveRoleId());
		result = new Patronage();
		result.setPublic(false);
		result.setPatron(patron);
		
		final Date actualDate = new Date();		
		result.setStartDate(actualDate);
		result.setStatus(PatronageStatus.PROPOSED);
		
		return result;
	}

	@Override
	public void bind(final Request<Patronage> request, final Patronage entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		final String inventorUsername = String.valueOf(request.getModel().getAttribute("inventor.userAccount.username"));
		final Inventor inventor = this.repository.findInventorByUsername(inventorUsername);
		errors.state(request, inventor!=null, "*", "patron.patronage.form.error.invalidInventor");
		entity.setInventor(inventor);
		
		request.bind(entity, errors, "code", "status", "legalStuff", "budget", "startDate", "finishDate", "link","inventor.userAccount.username");
		
	}
	
	@Override
	public void validate(final Request<Patronage> request, final Patronage entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		if(!errors.hasErrors("code")) {
			Patronage existing;
			
			existing = this.repository.findOnePatronageByCode(entity.getCode());
			errors.state(request, existing==null, "code", "patron.patronage.form.error.duplicated");
		}
		
		if(!errors.hasErrors("startDate")) {
			Calendar calendar;
			final Date minimunDate;
			
			calendar = new GregorianCalendar();
			calendar.add(Calendar.MONTH, 1);
			minimunDate = calendar.getTime();
			errors.state(request, entity.getStartDate().after(minimunDate), "startDate", "patron.patronage.form.error.too-close-start");
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
		
		
			errors.state(request, entity.getStatus().equals(PatronageStatus.PROPOSED), "status", "patron.patronage.form.error.status");
		
		
	}

	@Override
	public void unbind(final Request<Patronage> request, final Patronage entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		final List<Inventor> inventorSelect = this.repository.findAllInventors();
		model.setAttribute("inventorSelect", inventorSelect);
		
		request.unbind(entity, model, "code", "status", "legalStuff", "budget", "startDate", "finishDate", "link", "inventor.userAccount.username");
		
	}		

	@Override
	public void create(final Request<Patronage> request, final Patronage entity) {
		assert request != null;
		assert entity != null;
		final String inventorUsername = String.valueOf(request.getModel().getAttribute("inventor.userAccount.username"));
		final Inventor inventor = this.repository.findInventorByUsername(inventorUsername);
		entity.setInventor(inventor);

		this.repository.save(entity);		
	}
	
}
