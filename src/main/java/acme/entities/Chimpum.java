package acme.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
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
public class Chimpum extends AbstractEntity{

	protected static final long serialVersionUID = 1L;
	
	@NotBlank
	@Length(max=100)
	protected String title;

	@Column(unique = true)
	@Pattern(regexp = "[A-Z]{3}-[12]\\d{3}/(0[1-9]|1[0-2])/(0[1-9]|[12]\\d|3[01])")	
	@NotBlank
	protected String code;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Past
	@NotNull
	protected Date creationMoment;
	
	@NotBlank
	@Length(max=255)
	protected String description;
	
	@Valid
	@NotNull
	protected Money budget;
	
	@URL
	protected String link;
	
	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	protected Date startDate;
	
	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	protected Date finishDate;
    
    @Valid
    @NotNull
    @ManyToOne
    protected Artifact artifact;
}
