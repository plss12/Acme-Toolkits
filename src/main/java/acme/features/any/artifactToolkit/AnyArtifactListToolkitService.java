package acme.features.any.artifactToolkit;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.ArtifactToolkit;
import acme.entities.Toolkit;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.roles.Any;
import acme.framework.services.AbstractListService;

@Service
public class AnyArtifactListToolkitService implements AbstractListService<Any, ArtifactToolkit> {
	// Internal state ---------------------------------------------------------

		@Autowired
		protected AnyArtifactToolkitRepository repo;
		
		
		
	// AbstractListService<Any, Artifact> interface --------------

	@Override
	public boolean authorise(final Request<ArtifactToolkit> request) {
		assert request != null;
		
		int masterId;
		Toolkit toolkit;
		
		masterId = request.getModel().getInteger("masterId");
		toolkit = this.repo.findOneToolkitById(masterId);
		
		/* Somos conscientes de que sería posible acceder a los artefactos de un toolkit público que no es del inventor
		 * desde la vista de toolkits de inventor, pero esto tiene sentido porque desde la vista de any (authenticated) toolkit 
		 * es posible mostrarlos también, no es ninguna brecha de seguridad sino un ahorro de código razonable.  */
		
		if(toolkit == null) {
			return false;
		}else if(Boolean.TRUE.equals(toolkit.isPublic())) {
			return true;
		}else if(request.isPrincipal(toolkit.getInventor())){
			return true;
		}
		return false;
	}
	

	@Override
	public Collection<ArtifactToolkit> findMany(final Request<ArtifactToolkit> request) {
		assert request != null;
		Collection<ArtifactToolkit> result;
		final int id= request.getModel().getInteger("masterId");
		result = this.repo.findArtifactsToolkitsByToolkit(id);
		return result;
		
	}

	@Override
	public void unbind(final Request<ArtifactToolkit> request, final ArtifactToolkit entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "artifact.name", "artifact.retailPrice", "artifact.artifactType", "artifactAmount");
	}
	

}
