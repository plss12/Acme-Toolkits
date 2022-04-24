package acme.forms;

import java.io.Serializable;
import java.util.Map;

import org.springframework.data.util.Pair;

import acme.entities.PatronageStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdministratorDashboard implements Serializable{
	
	// Serialisation identifier -----------------------------------------------

	protected static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------
	
	int											totalNumberOfComponents;
	Map<Pair<String, String>, Double>			averageRetailPriceOfComponents;
	Map<Pair<String, String>, Double>			retailPriceDeviationOfComponents;
	Map<Pair<String, String>, Double>			minimumRetailPriceOfComponents;
	Map<Pair<String, String>, Double>			maximumRetailPriceOfComponents;
	
	int											totalNumberOfTools;
	Map<String, Double>							averageRetailPriceOfTools;
	Map<String, Double>							retailPriceDeviationOfTools;
	Map<String, Double>							minimumRetailPriceOfTools;
	Map<String, Double>							maximumRetailPriceOfTools;
	
	
	Map<PatronageStatus, Long>					totalNumberOfPatronages;
	Map<Pair<PatronageStatus,String>,Double>	averageBudgetOfPatronages;
	Map<Pair<PatronageStatus,String>,Double>	deviationBudgetOfPatronages;
	Map<Pair<PatronageStatus,String>,Double>	minimumBudgetOfPatronages;
	Map<Pair<PatronageStatus,String>,Double>	maximumBudgetOfPatronages;
	
	// Derived attributes -----------------------------------------------------

	// Relationships ----------------------------------------------------------
}
