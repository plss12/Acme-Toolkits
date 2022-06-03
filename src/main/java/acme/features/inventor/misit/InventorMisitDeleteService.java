package acme.features.inventor.misit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Misit;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractDeleteService;
import acme.roles.Inventor;

@Service
public class InventorMisitDeleteService implements AbstractDeleteService<Inventor, Misit>{

	@Autowired
    protected InventorMisitRepository repository;

    @Override
    public boolean authorise(final Request<Misit> request) {
        boolean result;
        int id;
        Misit chimpum;
        Inventor inventor;

        id = request.getModel().getInteger("id");
        chimpum = this.repository.findMisitById(id);
        inventor = chimpum.getArtefact().getInventor();
        result = request.isPrincipal(inventor) && chimpum!=null;

        return result;
    }

    @Override
    public void bind(final Request<Misit> request, final Misit entity, final Errors errors) {
    	assert request != null;
		assert entity != null;
		assert errors != null;        
       

        request.bind(entity, errors, "code","creationMoment","subject","explanation","startDate","finishDate","quantity","additionalInfo","artefact.code");

    }

    @Override
    public void unbind(final Request<Misit> request, final Misit entity, final Model model) {
    	assert request != null;
		assert entity != null;
		assert model != null;
    	
    	request.unbind(entity, model, "code","creationMoment","subject","explanation","startDate","finishDate","quantity","additionalInfo","artefact.code");

    }

    @Override
    public Misit findOne(final Request<Misit> request) {
        assert request != null;

        Misit result;
        int id;

        id = request.getModel().getInteger("id");
        result = this.repository.findMisitById(id);

        return result;
    }

    @Override
    public void validate(final Request<Misit> request, final Misit entity, final Errors errors) {
        assert request != null;
        assert entity != null;
        assert errors != null;

    }
    
    @Override
    public void delete(final Request<Misit> request, final Misit entity) {
        assert request != null;
        assert entity != null;

        this.repository.delete(entity);
    }

}
