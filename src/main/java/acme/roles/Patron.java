package acme.roles;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.URL;

import acme.framework.roles.UserRole;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Patron extends UserRole {
	// Serialisation identifier -----------------------------------------------

		protected static final long	serialVersionUID	= 1L;

		// Attributes -------------------------------------------------------------

		@NotBlank
		@Size(max = 100)
		protected String			company;

		@NotBlank
		@Size(max = 255)
		protected String			statement;
		
		@URL
		protected String			link;

		// Derived attributes -----------------------------------------------------

		// Relationships ----------------------------------------------------------
}
