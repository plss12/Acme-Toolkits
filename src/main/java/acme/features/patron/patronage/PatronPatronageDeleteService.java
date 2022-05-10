package acme.features.patron.patronage;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Patronage;
import acme.entities.PatronageReport;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractDeleteService;
import acme.roles.Inventor;
import acme.roles.Patron;

@Service
public class PatronPatronageDeleteService implements AbstractDeleteService<Patron, Patronage>{

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
		result = request.isPrincipal(patron);
		
		return result;
	}

	@Override
	public void bind(final Request<Patronage> request, final Patronage entity, final Errors errors) {

		final String inventorUsername = String.valueOf(request.getModel().getAttribute("inventor.userAccount.username"));
		final Inventor inventor = this.repository.findInventorByUsername(inventorUsername);
		errors.state(request, inventor!=null, "*", "patron.patronage.form.error.invalidInventor");
		entity.setInventor(inventor);
		
		request.bind(entity, errors, "code", "status", "legalStuff", "budget", "startDate", "finishDate", "link","inventor.userAccount.username");
		
		
	}

	@Override
	public void unbind(final Request<Patronage> request, final Patronage entity, final Model model) {
		request.unbind(entity, model, "code", "status", "legalStuff", "budget", "startDate", "finishDate", "link","inventor.userAccount.username");
		
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
		
	}

	@Override
	public void delete(final Request<Patronage> request, final Patronage entity) {
		assert request != null;
		assert entity != null;
		
		Collection<PatronageReport> patronageReports;
		
		patronageReports = this.repository.findManyPatronageReportsByPatronageId(request.getModel().getInteger("id"));
		for (final PatronageReport patronageReport : patronageReports) {
			this.repository.delete(patronageReport);
		}		
		this.repository.delete(entity);
		
	}

}
