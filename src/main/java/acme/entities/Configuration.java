package acme.entities;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;

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
	
	@NotBlank
	protected String defaultCurrency;
	
	@NotBlank
	protected String acceptedCurrencies;
	
	@Range(min=1, max=100)
	protected int weakSpamTrheshold;
	
	@Range(min=1, max=100)
	protected int strongSpamTrheshold;
	
	protected String strongSpam;
	
	protected String weakSpam;
	
}
