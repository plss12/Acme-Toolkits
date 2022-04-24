package acme.features.any.artifactToolkit;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.Artifact;
import acme.entities.ArtifactToolkit;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AnyArtifactToolkitRepository extends AbstractRepository{
	
	@Query("select a from Artifact a")
	Collection<Artifact> findAllArtifacts();
	
	@Query("select a from Artifact a where a.id=:id")
	Artifact findArtifactById(int id);
	
	@Query("select at from ArtifactToolkit at where at.id=:id")
	ArtifactToolkit findArtifactToolkitById(int id);
	
	@Query("select a from Artifact a where a.isPublic=:p")
	Collection<Artifact> findAllPublishedArtifacts(Boolean p);
	
	@Query("select at from ArtifactToolkit at where at.toolkit.id = :id")
	Collection<ArtifactToolkit> findArtifactsToolkitsByToolkit(int id);
}
