package vn.cdw.cdwforums.reponsitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import vn.cdw.cdwforums.entity.User;


public interface UsReposity extends JpaRepository<User, Integer>, PagingAndSortingRepository<User, Integer> {
	
	

}
