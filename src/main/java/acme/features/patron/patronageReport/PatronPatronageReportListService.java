package acme.features.patron.patronageReport;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Patronage;
import acme.entities.PatronageReport;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractListService;
import acme.roles.Patron;

@Service
public class PatronPatronageReportListService implements AbstractListService<Patron, PatronageReport> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected PatronPatronageReportRepository repository;

	// AbstractListService<Patron, PatronageReport> interface ---------------------------


	@Override
	public boolean authorise(final Request<PatronageReport> request) {
		assert request != null;

		boolean result;
		int masterId;
		
		Patronage patronage;
		
		masterId = request.getModel().getInteger("masterId");
		patronage = this.repository.findOnePatronageById(masterId);
		result = (patronage != null && (request.isPrincipal(patronage.getPatron())));
		
		return result;
	}

	@Override
	public Collection<PatronageReport> findMany(final Request<PatronageReport> request) {
		assert request != null;

		Collection<PatronageReport> result;
		int masterId;
		
		masterId=request.getModel().getInteger("masterId");
		
		result=this.repository.findPatronageReportsByPatronageId(masterId);

		return result;
	}
	
	@Override
	public void unbind(final Request<PatronageReport> request, final PatronageReport entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "sequenceNumber", "creationMoment", "patronage.code");
	}
}