package ca.sheridancollege.respository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import ca.sheridancollege.entities.Project;

// Project = class, Long = unique identifier (id)
public interface ProjectRepository extends CrudRepository<Project, Long> {

	//own method to return list. BC by default findAll is not iterable casted
	
	@Override 
	List<Project> findAll();
}
