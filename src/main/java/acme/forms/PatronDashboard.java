package acme.forms;

import java.io.Serializable;
import java.util.Map;

import org.springframework.data.util.Pair;

import acme.entities.PatronageStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PatronDashboard implements Serializable{

	// Serialisation identifier ---------------------------------------
	
	private static final long serialVersionUID = 1L;	
	
	//Attributes
	
	Map<PatronageStatus,Long> 				totalNumberOfPatronages;
	Map<Pair<PatronageStatus,String>,Double> 	averageBudgetOfPatronages;
	Map<Pair<PatronageStatus,String>,Double> 	deviationBudgetOfPatronages;
	Map<Pair<PatronageStatus,String>,Double> 	minimumBudgetOfPatronages;
	Map<Pair<PatronageStatus,String>,Double> 	maximumBudgetOfPatronages;
	
	
}
