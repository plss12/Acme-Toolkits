package acme.features.any.artifact;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.Artifact;
import acme.framework.controllers.AbstractController;
import acme.framework.roles.Any;

@Controller
public class AnyArtifactController extends AbstractController<Any, Artifact> {
	
	// Internal state ---------------------------------------------------------

	@Autowired
	protected AnyArtifactListService		listService;

	@Autowired
	protected AnyArtifactShowService		showService;
	 
	// Constructors -----------------------------------------------------------


	@PostConstruct
	protected void initialise() {
		super.addCommand("list", this.listService);
		super.addCommand("show", this.showService);
	} 
}
