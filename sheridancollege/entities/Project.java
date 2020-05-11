package ca.sheridancollege.entities;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor; 


@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
public class Project {
	
	@Id
	
	// loading db from data.sql we change the geenration 
	//type to identity instead of auto. Relies on 
	// db to generate 
	@GeneratedValue(strategy= GenerationType.SEQUENCE, generator="project_id")
	
	//rows
	private long projectId; 
	private String name; 
	private String stage; //tells if inprogress, completed, incompleted 
	private String description;
	
	//project can be assigned to many employees 
	// one project can have many employees assigned toit 
	// in the thymleaf employees will be 
	// referred to as "*{employees} 
	// or you could "${project.employees}"
	//@OneToMany(mappedBy="theProject")
	
	//many projects can be associated with one employee
	@ManyToMany(cascade= {CascadeType.DETACH, CascadeType.REFRESH, CascadeType.PERSIST},
			fetch=FetchType.LAZY)
	//here are joining the foreign keys and primary keys into a table 
	// called project_employee
	@JoinTable(name="project_employee", 
	joinColumns = @JoinColumn(name ="project_id"), 
	inverseJoinColumns=@JoinColumn(name="employee_id")
	)
	
	@JsonIgnore // ignores the field employees for serialization
	private List <Employee> employees;

}
