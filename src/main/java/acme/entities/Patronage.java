package acme.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.framework.entities.AbstractEntity;
import acme.roles.Inventor;
import acme.roles.Patron;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Patronage extends AbstractEntity{
	// Serialisation identifier -----------------------------------------------

		protected static final long	serialVersionUID	= 1L;

		// Attributes -------------------------------------------------------------

		@NotBlank
		protected PatronageStatus patronageStatus;

		@NotBlank
		@Pattern(regexp="^[A-Z]{3}-[0-9]{3}(-[A-Z])?$")
		@Column(unique = true)
		protected String code;
		
		@NotBlank
		@Length(max=256)
		protected String legalStuff;
		
		@NotBlank
		@Size(min = 0)
		protected Integer budget;
		
		//protected Date moment;
		
		@URL
		protected String furtherInformation;

		// Derived attributes -----------------------------------------------------

		// Relationships ----------------------------------------------------------
		@Column(name = "Patron")
		@ManyToOne
		protected Patron patron;
		
		@Column(name = "Inventor")
		@ManyToOne
		protected Inventor inventor;
}
