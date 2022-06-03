package acme.features.inventor.misit;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.Misit;
import acme.framework.controllers.AbstractController;
import acme.roles.Inventor;

@Controller
public class InventorMisitController extends AbstractController<Inventor,Misit>{
	
	@Autowired
	protected InventorArtifactMisitListService listServiceArtifact;
	
	@Autowired
	protected InventorMisitShowService showService;
	
	@Autowired
	protected InventorMisitCreateService createService;
	
	@Autowired
	protected InventorMisitUpdateService updateService;
	
	@Autowired
	protected InventorMisitDeleteService deleteService;
	
	
	@PostConstruct
	protected void initialise() {
		super.addCommand("show", this.showService);
		super.addCommand("list", this.listServiceArtifact);
		super.addCommand("create", this.createService);
		super.addCommand("update", this.updateService);
		super.addCommand("delete", this.deleteService);
		
	}

}
