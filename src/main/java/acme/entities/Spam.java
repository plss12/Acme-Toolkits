package acme.entities;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import acme.framework.entities.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Spam extends AbstractEntity{

	// Serialisation identifier ----------------------

	protected static final long serialVersionUID = 1L;
	
	// Attributes  ------------------------------------
	
	@NotBlank
	protected String word;
	
	@NotNull
	protected TypeSpam type;
}
