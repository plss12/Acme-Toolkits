package acme.features.any.chirp;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.Chirp;
import acme.framework.controllers.AbstractController;
import acme.framework.roles.Any;

@Controller
@RequestMapping("/any/chirps/")
public class AnyChirpController extends AbstractController<Any, Chirp>{
	// Internal state ---------------------------------------------------------

	@Autowired
	protected AnyChirpListRecentService	listService;

	@Autowired
	protected AnyChirpShowService	showService;
	
	// Constructors -----------------------------------------------------------

	@PostConstruct
	protected void initialise() {
		super.addCommand("list", this.listService);
		super.addCommand("show", this.showService);
	}
}
