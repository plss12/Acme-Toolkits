package acme.features.inventor.patronageReport;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Patronage;
import acme.entities.PatronageReport;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractCreateService;
import acme.roles.Inventor;

@Service
public class InventorPatronageReportCreateService implements AbstractCreateService<Inventor, PatronageReport>{

	@Autowired
	protected InventorPatronageReportRepository repository;
	
	@Override
	public boolean authorise(final Request<PatronageReport> request) {
		assert request != null;
		
		boolean result;
		int id;
		id = request.getModel().getInteger("masterId");
		
		Patronage patronage;
		patronage = this.repository.findOnePatronageById(id);
		
		result = (patronage != null &&(request.isPrincipal(patronage.getInventor())));
		
		return result;
	}

	@Override
	public void bind(final Request<PatronageReport> request, final PatronageReport entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		request.bind(entity, errors, "memorandum", "link");		
	}
	
	@Override
	public void validate(final Request<PatronageReport> request, final PatronageReport entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		final Boolean confirmed = request.getModel().getBoolean("confirm");
		errors.state(request, confirmed, "confirm", "inventor.patronageReport.form.error.confirm");
	
	}

	@Override
	public void unbind(final Request<PatronageReport> request, final PatronageReport entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "memorandum", "link");		
		model.setAttribute("masterId", request.getModel().getAttribute("masterId"));
		model.setAttribute("confirm", "false");
	}

	@Override
	public PatronageReport instantiate(final Request<PatronageReport> request) {
		assert request != null;
		
		final PatronageReport result;
		int id;
		Patronage patronage;
		final Date actualDate;
		int numReportsByPatronages;
		
		id = request.getModel().getInteger("masterId");
		patronage = this.repository.findOnePatronageById(id);
		actualDate = new Date();
		numReportsByPatronages = this.repository.findManyPatronageReportsByMasterId(id).size()+1;

		result = new PatronageReport();
		result.setPatronage(patronage);
		result.setCreationMoment(actualDate);
		result.setSequenceNumber(patronage.getCode()+":"+numReportsByPatronages);
				
		return result;
	}

	@Override
	public void create(final Request<PatronageReport> request, final PatronageReport entity) {
		assert request != null;
		assert entity != null;
		
		this.repository.save(entity);		
	}
}
