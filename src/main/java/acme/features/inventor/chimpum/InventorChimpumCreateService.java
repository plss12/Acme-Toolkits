package acme.features.inventor.chimpum;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Artifact;
import acme.entities.CHIMPUM;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractCreateService;
import acme.roles.Inventor;

@Service
public class InventorChimpumCreateService implements AbstractCreateService<Inventor, CHIMPUM>{

	@Autowired
	protected InventorChimpumRepository repository;
	
	@Override
	public boolean authorise(final Request<CHIMPUM> request) {
		assert request != null;
		
		boolean result;
		int id;
		id = request.getModel().getInteger("masterId");
		
		Artifact artifact;
		artifact = this.repository.findOneArtifactById(id);
		
		result = (artifact != null &&(request.isPrincipal(artifact.getInventor())));
		
		return result;
	}

	@Override
	public void bind(final Request<CHIMPUM> request, final CHIMPUM entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		final Integer id = request.getModel().getInteger("masterId");
		final Artifact artifact = this.repository.findOneArtifactById(id);
		
		final LocalDate dateObj = LocalDate.now();
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yy/MM/dd");
        final String date = dateObj.format(formatter);
        
		final String newcode = artifact.getCode() +"-"+ date +"-"+request.getModel().getAttribute("code");
		entity.setCode(newcode);
		request.getModel().setAttribute("code",newcode);
		
		
		request.bind(entity, errors,"code","creationMoment","title","description","startDate","finishDate","budget","link");
		
	}

	@Override
	public void unbind(final Request<CHIMPUM> request, final CHIMPUM entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		
		request.unbind(entity, model,"code","creationMoment","title","description","startDate","finishDate","budget","link");
		model.setAttribute("masterId", request.getModel().getAttribute("masterId"));
	}

	@Override
	public CHIMPUM instantiate(final Request<CHIMPUM> request) {
		final CHIMPUM result;
		int id;
		Artifact artifact;
		final Date actualDate;
		
		id = request.getModel().getInteger("masterId");
		artifact = this.repository.findOneArtifactById(id);
		
		actualDate = new Date();
		result = new CHIMPUM();
		result.setArtefact(artifact);
		result.setCreationMoment(actualDate);
				
		return result;
	}

	@Override
	public void validate(final Request<CHIMPUM> request, final CHIMPUM entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		
		assert errors != null;
		if(!errors.hasErrors("budget")) {
			final String entityCurrency;
			final Double amount;
			final String[] acceptedCurrencies;
			final List<String> currencies;
			
			entityCurrency = entity.getBudget().getCurrency();
			amount = entity.getBudget().getAmount();
			errors.state(request, amount > 0, "budget", "inventor.artifact.form.error.negative");
			acceptedCurrencies=this.repository.findAllAcceptedCurrencies().split(",");
			
			currencies = Arrays.asList(acceptedCurrencies);
			errors.state(request, currencies.contains(entityCurrency) , "budget", "inventor.chimpum.form.error.noAcceptedCurrency");
		}
		if(!errors.hasErrors("startDate")) {
			Calendar calendar;
			final Date minimunDate;
			calendar = new GregorianCalendar();
			calendar.add(Calendar.MONTH, 1);
			minimunDate = calendar.getTime();
			errors.state(request, entity.getStartDate().after(minimunDate), "startDate", "inventor.chimpum.form.error.startDate");
		}
		
		if(!errors.hasErrors("finishDate")) {
			Calendar calendar;
			Date minimunDate;
			
			calendar = new GregorianCalendar();
			calendar.setTime(entity.getStartDate());
			
			calendar.add(Calendar.WEEK_OF_YEAR, 1);
			minimunDate = calendar.getTime();
			
			errors.state(request, entity.getFinishDate().equals(minimunDate), "finishDate", "inventor.chimpum.form.error.finishDate");
		}
		
		if (!errors.hasErrors("code")) {
			CHIMPUM chimpum;

			chimpum=this.repository.findChimpumByCode(entity.getCode());
			errors.state(request, chimpum == null || chimpum.getId()==entity.getId(), "code", "inventor.chimpum.form.error.duplicated_code");
		}
		
		if(errors.hasErrors("code")) {
			final Model model = request.getModel();
			model.setAttribute("code",model.getAttribute("code").toString().charAt(model.getAttribute("code").toString().length()-1));
		}
		
		
	}

	@Override
	public void create(final Request<CHIMPUM> request, final CHIMPUM entity) {
		assert request != null;
		assert entity != null;
		this.repository.save(entity);
	}

}
