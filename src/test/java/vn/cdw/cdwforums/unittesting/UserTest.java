package vn.cdw.cdwforums.unittesting;

import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;



import vn.cdw.cdwforums.entity.User;
import vn.cdw.cdwforums.reponsitory.UserRepository;
import vn.cdw.cdwforums.service.UserService;

@RunWith(MockitoJUnitRunner.class)
public class UserTest {
	
	@Mock 
	UserRepository userRes;
	
	@InjectMocks
	UserService userSer;
	
	@Test
	public void loadUserByUsername() {
		 when(userRes.findByUsername("admin")).thenReturn(new User(4,"tthien","thien@gmail.com","tthien","2019-06-19 01:53:55.141000"));
		
		 
	}
	

}
