package acme.forms;

import java.io.Serializable;

import acme.framework.datatypes.Money;

public class AdministratorDashboard implements Serializable{
	
	// Serialisation identifier -----------------------------------------------

	protected static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------
	
	Integer						totalNumberOfComponents;
	Money						averageRetailPriceOfComponents;
	Money						retailPriceDeviationOfComponents;
	Money						minimumRetailPriceOfComponents;
	Money						maximumRetailPriceOfComponents;
	Integer						numberOfComponentsGroupedByTechnology;
	Integer						numberOfComponentsGroupedByCurrency;
	
	Integer						totalNumberOftools;
	Money						averageRetailPriceOfTools;
	Money						retailPriceDeviationOfTools;
	Money						minimumRetailPriceOfTools;
	Money						maximumRetailPriceOfTools;
	Integer						numberOftoolsGroupedByCurrency;
	
	Integer						totalNumberOfProposedPatronages;
	Double						averageOfProposedPatronages;
	Double						deviationOfProposedPatronages;
	Integer						minimumOfProposedPatronages;
	Integer						maximumOfProposedPatronages;
	
	Integer						totalNumberOfAcceptedPatronages;
	Double						averageOfAcceptedPatronages;
	Double						deviationOfAcceptedPatronages;
	Integer						minimumOfAcceptedPatronages;
	Integer						maximumOfAcceptedPatronages;
	
	Integer						totalNumberOfDeniedPatronages;
	Double						averageOfDeniedPatronages;	
	Double						deviationOfDeniedPatronages;	
	Integer						minimumOfDeniedPatronages;	
	Integer						maximumOfDeniedPatronages;
	
	// Derived attributes -----------------------------------------------------

	// Relationships ----------------------------------------------------------
}
