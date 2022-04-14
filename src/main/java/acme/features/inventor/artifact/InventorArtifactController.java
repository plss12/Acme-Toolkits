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
		
		// Constructor
		
		@PostConstruct
		protected void initialise() {
			super.addCommand("show", this.showService);
			super.addCommand("list", this.listService);
		}
}
