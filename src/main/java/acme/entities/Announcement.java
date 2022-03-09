package acme.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.framework.entities.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Announcement extends AbstractEntity{
	
	// Serialisation identifier -------------------
	
	private static final long serialVersionUID = 1L;
	
	// Attributes ---------------------------------

	@Temporal(TemporalType.TIMESTAMP)
	protected Date creationDate;
	
	@NotBlank
	@Length(max=101)
	protected String title;
	
	@NotBlank
	@Length(max=101)
	protected String author;
	
	@NotBlank
	@Length(max=256)
	protected String body;
	
	@Column(nullable=false)
	protected Boolean flag;
	
	@URL
	protected String link;
	
	
	
}
