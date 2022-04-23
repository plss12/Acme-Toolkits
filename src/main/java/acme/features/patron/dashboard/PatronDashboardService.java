package acme.features.patron.dashboard;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import acme.entities.PatronageStatus;
import acme.forms.PatronDashboard;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractShowService;
import acme.roles.Patron;

@Service
public class PatronDashboardService implements AbstractShowService<Patron, PatronDashboard>{


	// Internal state ---------------------------------------------------------

	@Autowired
	protected PatronDashboardRepository repository;
	
	@Override
	public boolean authorise(final Request<PatronDashboard> request) {
		assert request != null;

		return true;
	}
	
	@Override
	public PatronDashboard findOne(final Request<PatronDashboard> request) {
		assert request != null;

		PatronDashboard result;
		final EnumMap<PatronageStatus,Long> totalNumberOfPatronages = new EnumMap<>(PatronageStatus.class);
		final Map<Pair<PatronageStatus,String>,Double> averageBudgetOfPatronages = new HashMap<Pair<PatronageStatus,String>, Double>();
		final Map<Pair<PatronageStatus,String>,Double> deviationBudgetOfPatronages = new HashMap<Pair<PatronageStatus,String>, Double>();
		final Map<Pair<PatronageStatus,String>,Double> minimumBudgetOfPatronages = new HashMap<Pair<PatronageStatus,String>, Double>();
		final Map<Pair<PatronageStatus,String>,Double> maximumBudgetOfPatronages = new HashMap<Pair<PatronageStatus,String>, Double>();
		

		final List<Object[]> totalNumberList = this.repository.totalNumberOfPatronages(request.getPrincipal().getActiveRoleId());
		for(final Object[] elemento:totalNumberList) {
			final PatronageStatus status = (PatronageStatus) elemento[0];
			final Long number = (Long) elemento[1];
			totalNumberOfPatronages.put(status, number);	
		}
		
		final List<Object[]> averageList = this.repository.averageBudgetOfPatronages(request.getPrincipal().getActiveRoleId());
		for(final Object[] elemento:averageList) {
			final PatronageStatus status = (PatronageStatus) elemento[0];
			final Double average = (Double) elemento[1];
			final String currency = (String) elemento[2];
			final Pair<PatronageStatus, String> par = Pair.of(status, currency);
			averageBudgetOfPatronages.put(par, average);	
		}
		
		final List<Object[]> deviationList = this.repository.deviationBudgetOfPatronages(request.getPrincipal().getActiveRoleId());
		for(final Object[] elemento:deviationList) {
			final PatronageStatus status = (PatronageStatus) elemento[0];
			final Double deviation = (Double) elemento[1];
			final String currency = (String) elemento[2];
			final Pair<PatronageStatus, String> par = Pair.of(status, currency);
			deviationBudgetOfPatronages.put(par, deviation);	
		}
		
		final List<Object[]> minList = this.repository.minimumBudgetOfPatronages(request.getPrincipal().getActiveRoleId());
		for(final Object[] elemento:minList) {
			final PatronageStatus status = (PatronageStatus) elemento[0];
			final Double min = (Double) elemento[1];
			final String currency = (String) elemento[2];
			final Pair<PatronageStatus, String> par = Pair.of(status, currency);
			minimumBudgetOfPatronages.put(par, min);	
		}
		
		final List<Object[]> maxList = this.repository.maximumBudgetOfPatronages(request.getPrincipal().getActiveRoleId());
		for(final Object[] elemento:maxList) {
			final PatronageStatus status = (PatronageStatus) elemento[0];
			final Double max = (Double) elemento[1];
			final String currency = (String) elemento[2];
			final Pair<PatronageStatus, String> par = Pair.of(status, currency);
			maximumBudgetOfPatronages.put(par, max);	
		}
		
		result = new PatronDashboard();
		result.setTotalNumberOfPatronages(totalNumberOfPatronages);
		result.setAverageBudgetOfPatronages(averageBudgetOfPatronages);
		result.setDeviationBudgetOfPatronages(deviationBudgetOfPatronages);
		result.setMinimumBudgetOfPatronages(minimumBudgetOfPatronages);
		result.setMaximumBudgetOfPatronages(maximumBudgetOfPatronages);
		System.out.println(totalNumberOfPatronages);
		return result;
	}
	
	@Override
	public void unbind(final Request<PatronDashboard> request, final PatronDashboard entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "totalNumberOfPatronages", "averageBudgetOfPatronages",
			"deviationBudgetOfPatronages", "minimumBudgetOfPatronages",
			"maximumBudgetOfPatronages");
	}
}
