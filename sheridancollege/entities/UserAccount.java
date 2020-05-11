package ca.sheridancollege.entities;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//associating this UserAccount
// UserAccount = user_account in the Postgres

@Data
@AllArgsConstructor
@NoArgsConstructor


@Entity

@Table(name="user_accounts")
public class UserAccount {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generator_name")
	@SequenceGenerator(name="generator_name", sequenceName = "user_accounts_seq", allocationSize=1)

	//user_account_seq is identidied in the sql code
	//@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="user_accounts_seq")
	//@GeneratedValue(strategy=GenerationType.AUTO)
	 
	


	@Column(name="user_id")

	private long userId; 
	
	
	//column annotation we used bc  if we have camelcase by default spring looks for 
	// understores in postgres table properties but in postgres there aren't any underscore
	// so here I am associating the property username == in postgres with userName
	@Column (name="username")
	private String userName;
	
	private String email;
	private String password; 
	private boolean enabled = true;

}
