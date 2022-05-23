package acme.features.inventor.toolkit;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Artifact;
import acme.entities.Toolkit;
import acme.features.authenticated.moneyExchange.AuthenticatedMoneyExchangePerformService;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractShowService;
import acme.roles.Inventor;

@Service
public class InventorToolkitShowService implements AbstractShowService<Inventor,Toolkit>{
	
	@Autowired
	protected InventorToolkitRepository repository;
	@Autowired
	protected AuthenticatedMoneyExchangePerformService moneyService;

	@Override
	public boolean authorise(final Request<Toolkit> request) {
		assert request != null;
		
		int id;
		final boolean result;
		final Toolkit toolkit;
		id = request.getModel().getInteger("id");
		toolkit = this.repository.findToolkitById(id);
		result = (toolkit != null && (request.isPrincipal(toolkit.getInventor())));
		
		return result;
	}

	@Override
	public Toolkit findOne(final Request<Toolkit> request) {
		assert request != null;
		
		Toolkit result;
		int id;
		id = request.getModel().getInteger("id");
		result = this.repository.findToolkitById(id);
		return result;
	}

	@Override
	public void unbind(final Request<Toolkit> request, final Toolkit entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		model.setAttribute("id", entity.getId());
		model.setAttribute("price", this.calculatePriceOfToolkit(entity.getId()));
		request.unbind(entity, model, "code", "title", "description", "assemblyNotes", "link", "isPublic");	
	}
	
	public Double calculatePriceOfToolkit(final int id) {
		final String defaultCurrency = this.repository.defaultCurrency();
		final List<Object[]> prices = this.repository.findPricesAndNumberOfArtifactsByToolkit(id);
		Double totalAmount = 0.0;
		for (final Object[] price:prices) {
			totalAmount = totalAmount + (this.moneyService.computeMoneyExchange(((Artifact) price[0]).getRetailPrice(), defaultCurrency).getTarget().getAmount()) * (Integer) price[1];
		}
		return totalAmount;
		
	}
	

}
