package acme.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.framework.datatypes.Money;
import acme.framework.entities.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class CHIMPUM extends AbstractEntity{
	
	// Serialisation identifier ----------------------

	protected static final long serialVersionUID = 1L;
	
	// Attributes  ------------------------------------
		
	@Pattern(regexp = "YY/MM/DD")
	@NotBlank
	protected String code;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Past
	protected Date creationMoment;
	
	@Length(max=100)
	@NotBlank
	protected String title;
	
	@Length(max=255)
	@NotBlank
	protected String description;
	
	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	protected Date startDate;
	
	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	protected Date finishDate;
	
	@NotNull
	@Valid
	protected Money budget;
	
	@URL
	protected String link;
	
	// Relationships ----------------------------------------------------------	
	
	@Valid
	@OneToOne(optional = true)
	protected Artifact artefact;
}
