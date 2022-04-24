package acme.features.authenticated.systemConfiguration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Configuration;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.roles.Authenticated;
import acme.framework.services.AbstractShowService;

@Service
public class AuthenticatedSystemConfigurationShowService implements AbstractShowService<Authenticated, Configuration>{

	// Internal state -------------------------------------------------------------------
	@Autowired
	protected AuthenticatedSystemConfigurationRepository authConfRepo;
	
	@Override
	public boolean authorise(final Request<Configuration> request) {
		assert request != null;
		boolean result;
		Configuration configuration;
		configuration = this.authConfRepo.findOneConf();
		result = configuration != null;
		return result;
	}

	@Override
	public Configuration findOne(final Request<Configuration> request) {
		assert request != null;
		Configuration configuration;
		configuration = this.authConfRepo.findOneConf();
		return configuration;
	}

	@Override
	public void unbind(final Request<Configuration> request, final Configuration entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "defaultCurrency", "acceptedCurrencies");
		
	}

}
