package acme.features.administrator.dashboard;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import acme.entities.PatronageStatus;
import acme.forms.AdministratorDashboard;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.roles.Administrator;
import acme.framework.services.AbstractShowService;

@Service
public class AdministratorDashboardShowService implements AbstractShowService<Administrator,AdministratorDashboard> {

	//Internal state ----------------------------------------------------------------------
	
	@Autowired
	protected AdministratorDashboardRepository repository;
	
	//AbstractShowService<Administrator,AdministratorDashboard> interface -----------------
	
	@Override
	public boolean authorise(final Request<AdministratorDashboard> request) {
		assert request != null;
		return true;
	}

	@Override
	public AdministratorDashboard findOne(final Request<AdministratorDashboard> request) {
		assert request != null;
		
		final AdministratorDashboard result;
		
		// Components stats
		
		final int totalNumberOfComponents;
		totalNumberOfComponents = this.repository.totalNumberOfComponents();
		
		final Map<Pair<String,String>, Double> averageRetailPriceOfComponents = new HashMap<>();
		final Map<Pair<String,String>, Double> retailPriceDeviationOfComponents = new HashMap<>();
		final Map<Pair<String,String>, Double> minimumRetailPriceOfComponents = new HashMap<>();
		final Map<Pair<String,String>, Double> maximumRetailPriceOfComponents = new HashMap<>();
		
		final List<Object[]> statsOfComponents;
		statsOfComponents = this.repository.statsOfComponents();
		for(final Object[] stats:statsOfComponents) {
			final String technology = (String) stats[0];
			final String currency = (String) stats[1];
			final Double avg = (Double) stats[2];
			final Double min = (Double) stats[3];
			final Double max = (Double) stats[4];
			final Double stddev = (Double) stats[5];
			
			averageRetailPriceOfComponents.put(Pair.of(currency,technology), avg);
			retailPriceDeviationOfComponents.put(Pair.of(currency,technology), stddev);
			minimumRetailPriceOfComponents.put(Pair.of(currency,technology), min);
			maximumRetailPriceOfComponents.put(Pair.of(currency,technology), max);
		}
		
		//Tools stats
		
		final int totalNumberOfTools;
		totalNumberOfTools = this.repository.totalNumberOfTools();
		
		final Map<String, Double> averageRetailPriceOfTools = new HashMap<>();
		final Map<String, Double> retailPriceDeviationOfTools = new HashMap<>();
		final Map<String, Double> minimumRetailPriceOfTools = new HashMap<>();
		final Map<String, Double> maximumRetailPriceOfTools = new HashMap<>();
		
		final List<Object[]> statsOfTools;
		statsOfTools = this.repository.statsOfTools();
		for(final Object[] stats:statsOfTools) {
			final String currency = (String) stats[0];
			final Double avg = (Double) stats[1];
			final Double min = (Double) stats[2];
			final Double max = (Double) stats[3];
			final Double stddev = (Double) stats[4];
			
			averageRetailPriceOfTools.put(currency, avg);
			retailPriceDeviationOfTools.put(currency, stddev);
			minimumRetailPriceOfTools.put(currency, min);
			maximumRetailPriceOfTools.put(currency, max);
		}
		
		//Patronages stats
		
		final List<Object[]> totalNumberOfPatronagesGrouped;
		totalNumberOfPatronagesGrouped = this.repository.totalNumberOfPatronages();
		
		final EnumMap<PatronageStatus,Long> totalNumberOfPatronages = new EnumMap<>(PatronageStatus.class);
		final Map<Pair<PatronageStatus,String>,Double> averageBudgetOfPatronages = new HashMap<Pair<PatronageStatus,String>, Double>();
		final Map<Pair<PatronageStatus,String>,Double> deviationBudgetOfPatronages = new HashMap<Pair<PatronageStatus,String>, Double>();
		final Map<Pair<PatronageStatus,String>,Double> minimumBudgetOfPatronages = new HashMap<Pair<PatronageStatus,String>, Double>();
		final Map<Pair<PatronageStatus,String>,Double> maximumBudgetOfPatronages = new HashMap<Pair<PatronageStatus,String>, Double>();
		
		final List<Object[]> statsOfPatronages;
		statsOfPatronages = this.repository.statsOfPatronages();
		
		for(final Object[] group:totalNumberOfPatronagesGrouped) {
			final PatronageStatus status = (PatronageStatus) group[0];
			final Long i = (Long) group[1];
			
			totalNumberOfPatronages.put(status, i);
		}
		
		for(final Object[] stats:statsOfPatronages) {
			final PatronageStatus status = (PatronageStatus) stats[0];
			final Double avg = (Double) stats[1];
			final Double min = (Double) stats[2];
			final Double max = (Double) stats[3];
			final Double stddev = (Double) stats[4];
			final String currency = (String) stats[5];
			
			final Pair<PatronageStatus, String> pair = Pair.of(status, currency);
			averageBudgetOfPatronages.put(pair, avg);
			deviationBudgetOfPatronages.put(pair, stddev);
			minimumBudgetOfPatronages.put(pair, min);
			maximumBudgetOfPatronages.put(pair, max);
		}
		
		//Construct
		
		result = new AdministratorDashboard();
		
		result.setTotalNumberOfComponents(totalNumberOfComponents);
		result.setAverageRetailPriceOfComponents(averageRetailPriceOfComponents);
		result.setRetailPriceDeviationOfComponents(retailPriceDeviationOfComponents);
		result.setMinimumRetailPriceOfComponents(minimumRetailPriceOfComponents);
		result.setMaximumRetailPriceOfComponents(maximumRetailPriceOfComponents);
		
		result.setTotalNumberOfTools(totalNumberOfTools);
		result.setAverageRetailPriceOfTools(averageRetailPriceOfTools);
		result.setRetailPriceDeviationOfTools(retailPriceDeviationOfTools);
		result.setMinimumRetailPriceOfTools(minimumRetailPriceOfTools);
		result.setMaximumRetailPriceOfTools(maximumRetailPriceOfTools);
		
		result.setTotalNumberOfPatronages(totalNumberOfPatronages);
		result.setAverageBudgetOfPatronages(averageBudgetOfPatronages);
		result.setDeviationBudgetOfPatronages(deviationBudgetOfPatronages);
		result.setMinimumBudgetOfPatronages(minimumBudgetOfPatronages);
		result.setMaximumBudgetOfPatronages(maximumBudgetOfPatronages);
		
		return result;
	}

	@Override
	public void unbind(final Request<AdministratorDashboard> request, final AdministratorDashboard entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "totalNumberOfComponents","averageRetailPriceOfComponents","retailPriceDeviationOfComponents",
			"minimumRetailPriceOfComponents","maximumRetailPriceOfComponents",
			"totalNumberOfTools","averageRetailPriceOfTools","retailPriceDeviationOfTools","minimumRetailPriceOfTools","maximumRetailPriceOfTools",
			"totalNumberOfPatronages","averageBudgetOfPatronages","deviationBudgetOfPatronages","minimumBudgetOfPatronages","maximumBudgetOfPatronages");
		
		final Set<String> technologies = entity.getMinimumRetailPriceOfComponents().keySet().stream().map(Pair::getFirst).collect(Collectors.toSet());
		model.setAttribute("technology", technologies);
		
		final Set<String> currencies = entity.getRetailPriceDeviationOfTools().keySet();
		model.setAttribute("currency", currencies);
	}

}
