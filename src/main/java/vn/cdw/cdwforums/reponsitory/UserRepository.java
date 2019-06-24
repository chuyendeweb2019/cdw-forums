package vn.cdw.cdwforums.reponsitory;



import org.springframework.data.repository.CrudRepository;

import vn.cdw.cdwforums.entity.User;

public interface UserRepository extends CrudRepository<User, Long>{

	User findByUsername(String username);

    User findByEmail(String email);
    
    
}
