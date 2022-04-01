package acme.features.anonymous.chirp;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.Chirp;
import acme.framework.controllers.AbstractController;
import acme.framework.roles.Anonymous;

@Controller
public class AnonymousChirpController extends AbstractController<Anonymous, Chirp>{
	// Internal state ---------------------------------------------------------

	@Autowired
	protected AnonymousChirpListRecentService	listService;

	// Constructors -----------------------------------------------------------

	@PostConstruct
	protected void initialise() {
		super.addCommand("list-recent", this.listService);
	}
}
