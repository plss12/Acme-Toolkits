package acme.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;

import acme.framework.entities.AbstractEntity;
import lombok.Getter;
import lombok.Setter;
 
@Entity
@Getter
@Setter
public class Configuration extends AbstractEntity{
	
	// Serialisation identifier ----------------------

	protected static final long serialVersionUID = 1L;
	
	// Attributes  ------------------------------------
	
	@NotNull
	protected String defaultCurrency;
	
	protected List<String> acceptedCurrencies;
	
	@Range(min=0, max=100)
	protected Integer weakSpamTrheshold;
	
	@Range(min=0, max=100)
	protected Integer strongSpamTrheshold;
	
	protected List<String> strongSpam;
	
	protected List<String> weakSpam;
	
}
