package acme.features.any.artifactToolkit;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.Artifact;
import acme.entities.ArtifactToolkit;
import acme.entities.Toolkit;
import acme.framework.repositories.AbstractRepository;
import acme.roles.Inventor;

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
	
	@Query("select t from Toolkit t where t.id=:id")
	Toolkit findOneToolkitById(int id);
	
	@Query("select i from Inventor i where i.userAccount.id = :id")
	Inventor findInventorByUserAccountId(int id);
	
	@Query("select at from ArtifactToolkit at where at.toolkit = :toolkit and at.artifact = :artifact")
	Collection<ArtifactToolkit> findArtifactsToolkitsByToolkitAndArtifact(Toolkit toolkit, Artifact artifact);
}
