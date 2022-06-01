package acme.features.inventor.chimpum;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Artifact;
import acme.entities.ArtifactType;
import acme.entities.Chimpum;
import acme.entities.Configuration;
import acme.features.SpamDetector.SpamDetector;
import acme.features.SpamDetector.SpamDetectorRepository;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractCreateService;
import acme.roles.Inventor;

@Service
public class InventorChimpumCreateService implements AbstractCreateService<Inventor, Chimpum>{

	@Autowired
	protected InventorChimpumRepository repository;
	
	@Autowired
	protected SpamDetectorRepository spamRepo;
	
	@Override
	public boolean authorise(final Request<Chimpum> request) {
		assert request != null;
		
		boolean result;
		int id;
		id = request.getModel().getInteger("masterId");
		
		Artifact artifact;
		artifact = this.repository.findArtifactById(id);
		
		result = (artifact != null &&(request.isPrincipal(artifact.getInventor())) && artifact.getArtifactType()==ArtifactType.TOOL);
		
		return result;
	}

	@Override
	public void bind(final Request<Chimpum> request, final Chimpum entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		request.bind(entity, errors, "title", "code", "description", "budget", "link", "startDate", "finishDate");
	}

	@Override
	public void unbind(final Request<Chimpum> request, final Chimpum entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		model.setAttribute("masterId", request.getModel().getInteger("masterId"));
		request.unbind(entity, model, "title", "code", "description", "budget", "link", "startDate", "finishDate");
		
	}

	@Override
	public Chimpum instantiate(final Request<Chimpum> request) {
		assert request != null;
		
		Chimpum result;
		int id;
		Date actualDate;
		Artifact artifact;
		
		result = new Chimpum();
		
		actualDate = new Date();		
		result.setCreationMoment(actualDate);
		
		id = request.getModel().getInteger("masterId");
		artifact = this.repository.findArtifactById(id);
		result.setArtifact(artifact);
		
		return result;
	}

	@Override
	public void validate(final Request<Chimpum> request, final Chimpum entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		if (!errors.hasErrors("code")) {
			Chimpum chimpum;

			chimpum=this.repository.findChimpumByCode(entity.getCode());
			errors.state(request, chimpum == null || chimpum.getId()==entity.getId(), "code", "inventor.chimpum.form.error.duplicated_code");
			
			String code;
			Date date;
			SimpleDateFormat formatter;
			String format;
			int finalIndexCode;
			
			date = entity.getCreationMoment();
			formatter = new SimpleDateFormat("yyyy/MM/dd");
			format = formatter.format(date);
			
			code=entity.getCode();
			finalIndexCode=code.length();
			code=code.substring(finalIndexCode-10, finalIndexCode);
			errors.state(request, code.equals(format), "code", "inventor.chimpum.form.error.incorrect_code");

			
		}
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
			errors.state(request, entity.getStartDate().after(minimunDate), "startDate", "inventor.chimpum.form.error.too-close-start");
		}
		if(!errors.hasErrors("finishDate")) {
			Calendar calendar;
			Date minimunDate;
			
			calendar = new GregorianCalendar();
			calendar.setTime(entity.getStartDate());
			
			calendar.add(Calendar.WEEK_OF_YEAR, 1);
			minimunDate = calendar.getTime();
			
			errors.state(request, entity.getFinishDate().equals(minimunDate), "finishDate", "inventor.chimpum.form.error.too-close");
		}
		
		final SpamDetector spamdetector = new SpamDetector();
		final Configuration config = this.spamRepo.findOneConf();
		
		if(!errors.hasErrors("title")) {
			errors.state(request, !spamdetector.containsSpam(config.getWeakSpam(),config.getWeakSpamTrheshold(),entity.getTitle())
				&& !spamdetector.containsSpam(config.getStrongSpam(),config.getStrongSpamTrheshold(),entity.getTitle()),
				"title", "inventor.chimpum.form.error.spam");
		}
		if(!errors.hasErrors("description")) {
			errors.state(request, !spamdetector.containsSpam(config.getWeakSpam(),config.getWeakSpamTrheshold(),entity.getDescription())
				&& !spamdetector.containsSpam(config.getStrongSpam(),config.getStrongSpamTrheshold(),entity.getDescription()),
				"description", "inventor.chimpum.form.error.spam");
		}
		
	}

	@Override
	public void create(final Request<Chimpum> request, final Chimpum entity) {
		assert request != null;
		assert entity != null;
	
		this.repository.save(entity);
	}
}