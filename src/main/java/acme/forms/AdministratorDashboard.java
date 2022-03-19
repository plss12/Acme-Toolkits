package acme.forms;

import java.io.Serializable;
import java.util.Map;

import org.springframework.data.util.Pair;

import acme.entities.PatronageStatus;
import acme.framework.datatypes.Money;

public class AdministratorDashboard implements Serializable{
	
	// Serialisation identifier -----------------------------------------------

	protected static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------
	
	Integer								totalNumberOfComponents;
	Map<Pair<String, String>, Money>	averageRetailPriceOfComponents;
	Map<Pair<String, String>, Money>	retailPriceDeviationOfComponents;
	Map<Pair<String, String>, Money>	minimumRetailPriceOfComponents;
	Map<Pair<String, String>, Money>	maximumRetailPriceOfComponents;
	
	Integer								totalNumberOftools;
	Map<Pair<String, String>, Money>	averageRetailPriceOfTools;
	Map<Pair<String, String>, Money>	retailPriceDeviationOfTools;
	Map<Pair<String, String>, Money>	minimumRetailPriceOfTools;
	Map<Pair<String, String>, Money>	maximumRetailPriceOfTools;
	
	
	Map<PatronageStatus, Integer>		totalNumberOfPatronages;
	Map<PatronageStatus, Money>			averageBudgetOfPatronages;
    Map<PatronageStatus, Money>			deviationBudgetOfPatronages;
	Map<PatronageStatus, Money>			minimumBudgetOfPatronages;
	Map<PatronageStatus, Money>			maximumBudgetOfPatronages;
	
	// Derived attributes -----------------------------------------------------

	// Relationships ----------------------------------------------------------
}
