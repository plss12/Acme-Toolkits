package acme.forms;

import java.io.Serializable;

import acme.framework.datatypes.Money;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PatronDashboard implements Serializable{

	// Serialisation identifier ---------------------------------------
	
	private static final long serialVersionUID = 1L;	
	
	//Attributes
	
	Integer numberOfProposedPatronages;
	Integer numberOfAcceptedPatronages;
	Integer numberOfDeniedPatronages;
	
	Money averageBudgetPropossedPatronages;
	Money deviationBudgetPropossedPatronages;
	Money minimunBudgetPropossedPatronages;
	Money maximunBudgetPropossedPatronages;
	
	Money averageBudgetAcceptedPatronages;
	Money deviationBudgetAcceptedPatronages;
	Money minimunBudgetAcceptedPatronages;
	Money maximunBudgetAcceptedPatronages;
	
	Money averageBudgetDeniedPatronages;
	Money deviationBudgetDeniedPatronages;
	Money minimunBudgetDeniedPatronages;
	Money maximunBudgetDeniedPatronages;
	
	
}
