package acme.features.authenticated.inventor.artifact;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.Artifact;
import acme.framework.controllers.AbstractController;
import acme.framework.roles.Authenticated;

@Controller
public class InventorArtifactController extends AbstractController<Authenticated, Artifact>{

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
