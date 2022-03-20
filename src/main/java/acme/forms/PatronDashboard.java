package acme.forms;

import java.io.Serializable;
import java.util.Map;

import org.springframework.data.util.Pair;

import acme.entities.PatronageStatus;
import acme.framework.datatypes.Money;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PatronDashboard implements Serializable{

	// Serialisation identifier ---------------------------------------
	
	private static final long serialVersionUID = 1L;	
	
	//Attributes
	
	Map<PatronageStatus,Integer> 				totalNumberOfPatronages;
	Map<Pair<PatronageStatus,String>,Money> 	averageBudgetOfPatronages;
	Map<Pair<PatronageStatus,String>,Money> 	deviationBudgetOfPatronages;
	Map<Pair<PatronageStatus,String>,Money> 	minimumBudgetOfPatronages;
	Map<Pair<PatronageStatus,String>,Money> 	maximumBudgetOfPatronages;
	
	
}
