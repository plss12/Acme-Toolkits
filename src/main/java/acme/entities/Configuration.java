package acme.entities;

import javax.persistence.Entity;
import javax.validation.constraints.Digits;
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
	
	@Range(min=0, max=1)
	@Digits(integer=1, fraction=2)
	protected double weakSpamTrheshold;
	
	@Range(min=0, max=1)
	@Digits(integer=1, fraction=2)
	protected double strongSpamTrheshold;
	
}
