package ca.sheridancollege.respository;

import org.springframework.data.repository.CrudRepository;

import ca.sheridancollege.entities.UserAccount;


public interface UserAccountRepository extends CrudRepository<UserAccount, Long> {

}
