package acme.features.any.artifactToolkit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.ArtifactToolkit;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.roles.Any;
import acme.framework.services.AbstractShowService;

@Service
public class AnyArtifactToolkitShowService implements AbstractShowService<Any,ArtifactToolkit> {
	
	@Autowired
	protected AnyArtifactToolkitRepository repo;

	@Override
	public boolean authorise(final Request<ArtifactToolkit> request) {
		assert request != null;
		
		int id;
		final ArtifactToolkit atifactToolkit;
		
		id = request.getModel().getInteger("id");
		atifactToolkit = this.repo.findArtifactToolkitById(id);
		
		/* Somos conscientes de que sería posible acceder a los artefactos de un toolkit público que no es del inventor
		 * desde la vista de toolkits de inventor, pero esto tiene sentido porque desde la vista de any (authenticated) toolkit 
		 * es posible mostrarlos también, no es ninguna brecha de seguridad sino un ahorro de código razonable.  */
		
		if(atifactToolkit == null) {
			return false;
		}else if(Boolean.TRUE.equals(atifactToolkit.getToolkit().isPublic())) {
			return true;
		}else if(request.isPrincipal(atifactToolkit.getToolkit().getInventor())){
			return true;
		}
		return false;
	}

	@Override
	public ArtifactToolkit findOne(final Request<ArtifactToolkit> request) {
		assert request != null;

		ArtifactToolkit result;
		int id;
		id=request.getModel().getInteger("id");
		result = this.repo.findArtifactToolkitById(id);
		return result;
	}

	@Override
	public void unbind(final Request<ArtifactToolkit> request, final ArtifactToolkit entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "artifact.name", "artifact.code", "artifact.technology", "artifact.description", "artifact.retailPrice", "artifact.artifactType", "artifact.link", "artifactAmount");
		
	}

}
