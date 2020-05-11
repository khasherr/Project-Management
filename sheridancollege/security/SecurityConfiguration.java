package ca.sheridancollege.security;



import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

//this is config class 

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	//datasource tells what to use either in memory or jdbc
	// depends on what is outlined in the application.properties file as datasource
	@Autowired
	DataSource dataSource;
  
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	@Override 
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	
		// if you want in memory authemtical than use inMemory 
		//auth.inMemoryAuthentication()
		// here using JDBC authentication will use jdbc authentication
		auth.jdbcAuthentication().dataSource(dataSource)
		//use bCryptPasswordEncoder 
		.passwordEncoder(bCryptPasswordEncoder)
		//get username and password the authentication part. username password enabled are columns defined in postgres
		.usersByUsernameQuery("select username, password, enabled " + "from user_accounts where username= ?")
		//get the roles associated with username - username and roles are columns . user_accounts is table name in postgres we defined
		.authoritiesByUsernameQuery("select username, role " + "from user_accounts where username=?");
	}

	//authorization 
	protected void configure(HttpSecurity http) throws Exception{ 
		http.authorizeRequests()
		//only admin will create new projects 
		// only admin will create new employees 
		.antMatchers("/projects/new").hasRole("ADMIN")
		.antMatchers("/employees/new").hasRole("ADMIN")
		//permit h2 console only if we are using h2-console
		.antMatchers("/h2-console/**").permitAll()
		.antMatchers("/", "/**").permitAll()
		.and()
		.formLogin();
		

	http.csrf().disable();
	http.headers().frameOptions().disable();
	
	
	}

}
