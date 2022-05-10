package acme.features.inventor.artifact;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.Artifact;
import acme.framework.controllers.AbstractController;
import acme.roles.Inventor;

@Controller
public class InventorArtifactController extends AbstractController<Inventor, Artifact>{

	// Internal state -----------------------------------------------------------
	
		@Autowired
		protected InventorArtifactListService listService;
		
		@Autowired
		protected InventorArtifactShowService showService;
		
		@Autowired
		protected InventorArtifactCreateService createService;
		
		@Autowired
		protected InventorArtifactUpdateService updateService;
		
		@Autowired
		protected InventorArtifactDeleteService deleteService;
		
		@Autowired
		protected InventorArtifactPublishService publishService;
		
		// Constructor
		
		@PostConstruct
		protected void initialise() {
			super.addCommand("show", this.showService);
			super.addCommand("list", this.listService);
			super.addCommand("create", this.createService);
			super.addCommand("update", this.updateService);
			super.addCommand("delete", this.deleteService);
			super.addCommand("publish", "update", this.publishService);
		}
}
