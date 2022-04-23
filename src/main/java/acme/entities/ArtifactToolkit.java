package acme.entities;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import acme.framework.entities.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class ArtifactToolkit extends AbstractEntity{

	// Serialisation identifier ----------------------

	protected static final long serialVersionUID = 1L;
	
	// Attributes  ------------------------------------
	
	@NotNull
	protected int artifactAmount;
	
	@NotNull
	@Valid
	@ManyToOne(optional=false)
	protected Toolkit toolkit;
	
	@NotNull
	@Valid
	@ManyToOne(optional=false)
	protected Artifact artifact;
}