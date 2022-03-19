package acme.entities;

import javax.persistence.Entity;

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
	
	protected int artifactCode;
	
	protected int toolkitCode;

}
