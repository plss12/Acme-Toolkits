package acme.features.any.artifactToolkit;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.ArtifactToolkit;
import acme.framework.controllers.AbstractController;
import acme.framework.roles.Any;

@Controller
public class AnyArtifactToolkitController extends AbstractController<Any, ArtifactToolkit> {
	
	// Internal state ---------------------------------------------------------
	 
	@Autowired
	protected AnyArtifactListToolkitService		listToolkitService;
	@Autowired
	protected AnyArtifactToolkitShowService		showToolkitService;
	@Autowired
	protected AnyArtifactToolkitDeleteService		deleteToolkitService;
	@Autowired
	protected AnyArtifactToolkitCreateService		createToolkitService;
	// Constructors -----------------------------------------------------------


	@PostConstruct
	protected void initialise() {
		super.addCommand("list", this.listToolkitService);
		super.addCommand("show", this.showToolkitService);
		super.addCommand("delete", this.deleteToolkitService);
		super.addCommand("create", this.createToolkitService);
	} 
}
