package acme.features.authenticated.toolkit;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.Toolkit;
import acme.framework.controllers.AbstractController;
import acme.framework.roles.Authenticated;

@Controller
@RequestMapping("/authenticated/toolkits/")
public class AuthenticatedToolkitController extends AbstractController<Authenticated,Toolkit>{
	
	// Internal state --------------------------------------------------------------
	
	@Autowired
	protected AuthenticatedToolkitListService listService;
	
	@Autowired
	protected AuthenticatedToolkitShowService showService;
	
	// Constructors ----------------------------------------------------------------
	
	@PostConstruct
	protected void initialise() {
		super.addCommand("list", this.listService);
		super.addCommand("show", this.showService);
	}

}
