package acme.features.administrator.systemConfiguration;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Configuration;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.roles.Administrator;
import acme.framework.services.AbstractUpdateService;

@Service
public class AdministratorSystemConfigurationUpdateService implements AbstractUpdateService<Administrator, Configuration>{

	
	// Internal state -------------------------------------------------------------------
	@Autowired
	protected AdministratorSystemConfigurationRepository adminConfRepo;

	@Override
	public boolean authorise(final Request<Configuration> request) {
		assert request != null;
		boolean result;
		Configuration configuration;
		configuration = this.adminConfRepo.findOneConf();
		result = configuration != null;
		return result;
	}

	@Override
	public void bind(final Request<Configuration> request, final Configuration entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		request.bind(entity, errors, "defaultCurrency", "acceptedCurrencies", "weakSpamTrheshold", 
			"strongSpamTrheshold", "strongSpam", "weakSpam");
		
	}

	@Override
	public void unbind(final Request<Configuration> request, final Configuration entity, final Model model) {
		request.unbind(entity, model, "defaultCurrency", "acceptedCurrencies", "weakSpamTrheshold", 
			"strongSpamTrheshold", "strongSpam", "weakSpam");
		
	}

	@Override
	public Configuration findOne(final Request<Configuration> request) {
		assert request != null;
		Configuration configuration;
		configuration = this.adminConfRepo.findOneConf();
		return configuration;
	}

	@Override
	public void validate(final Request<Configuration> request, final Configuration entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		final List<String> currencies= new ArrayList<String>();
		final String[] acceptedCurrencies= entity.getAcceptedCurrencies().split(",");
		for (final String currency : acceptedCurrencies){
		    currencies.add(currency.trim());
		    }
		
		if(!errors.hasErrors("defaultCurrency")) {
			errors.state(request, currencies.contains(entity.getDefaultCurrency()), "defaultCurrency", 
				"administrator.system_configuration.form.error.bad-default-currency");
		}
	}

	@Override
	public void update(final Request<Configuration> request, final Configuration entity) {
		assert request != null;
		assert entity != null;
		this.adminConfRepo.save(entity);
		
	}

}
