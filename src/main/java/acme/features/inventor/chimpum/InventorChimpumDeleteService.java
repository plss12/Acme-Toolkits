package acme.features.inventor.chimpum;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.CHIMPUM;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractDeleteService;
import acme.roles.Inventor;

@Service
public class InventorChimpumDeleteService implements AbstractDeleteService<Inventor, CHIMPUM>{

	@Autowired
    protected InventorChimpumRepository repository;

    @Override
    public boolean authorise(final Request<CHIMPUM> request) {
        boolean result;
        int id;
        CHIMPUM chimpum;
        Inventor inventor;

        id = request.getModel().getInteger("id");
        chimpum = this.repository.findChimpumById(id);
        inventor = chimpum.getArtefact().getInventor();
        result = request.isPrincipal(inventor) && chimpum!=null;

        return result;
    }

    @Override
    public void bind(final Request<CHIMPUM> request, final CHIMPUM entity, final Errors errors) {
    	assert request != null;
		assert entity != null;
		assert errors != null;        
       

        request.bind(entity, errors, "code", "creationMoment", "title", "description", "startDate", "finishDate", "budget","link","artefact.code");


    }

    @Override
    public void unbind(final Request<CHIMPUM> request, final CHIMPUM entity, final Model model) {
    	assert request != null;
		assert entity != null;
		assert model != null;
    	
    	request.unbind(entity, model, "code", "creationMoment", "title", "description", "startDate", "finishDate", "budget","link","artefact.code");

    }

    @Override
    public CHIMPUM findOne(final Request<CHIMPUM> request) {
        assert request != null;

        CHIMPUM result;
        int id;

        id = request.getModel().getInteger("id");
        result = this.repository.findChimpumById(id);

        return result;
    }

    @Override
    public void validate(final Request<CHIMPUM> request, final CHIMPUM entity, final Errors errors) {
        assert request != null;
        assert entity != null;
        assert errors != null;

    }
    
    @Override
    public void delete(final Request<CHIMPUM> request, final CHIMPUM entity) {
        assert request != null;
        assert entity != null;

        Collection<CHIMPUM> chimpums;

        chimpums = this.repository.findManyCHIMPUMsByArtifactId(request.getModel().getInteger("id"));
        for (final CHIMPUM chimpum : chimpums) {
            this.repository.delete(chimpum);
        }
        this.repository.delete(entity);

    }

}
