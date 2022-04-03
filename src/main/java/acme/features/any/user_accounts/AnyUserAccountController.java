package acme.features.any.user_accounts;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.framework.controllers.AbstractController;
import acme.framework.entities.UserAccount;
import acme.framework.roles.Any;

@Controller
@RequestMapping("/any/user_accounts/")
public class AnyUserAccountController extends AbstractController<Any, UserAccount>{
	
	// Internal state --------------------------------------------------------------
	
	protected AnyUserAccountListService listService;
	
	protected AnyUserAccountShowService showService;
	
	@Autowired
	public AnyUserAccountController(final AnyUserAccountListService listService, final AnyUserAccountShowService showService) {
		this.listService = listService;
		this.showService = showService;
	}
	
	// Constructors ----------------------------------------------------------------
	
	@PostConstruct
	protected void initialise() {
		super.addCommand("list", this.listService);
		super.addCommand("show", this.showService);
	}

}
