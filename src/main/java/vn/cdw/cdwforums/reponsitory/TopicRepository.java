package vn.cdw.cdwforums.reponsitory;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import vn.cdw.cdwforums.entity.Section;
import vn.cdw.cdwforums.entity.Topic;
import vn.cdw.cdwforums.entity.Reply;;


public interface TopicRepository extends PagingAndSortingRepository<Topic, Long> {

	Page<Topic> findBySection(Section section, Pageable pageable);

    Page<Topic> findByTitleContainingOrTextContaining(String searchWordInTitle, String searchWordInText, Pageable pageable);
    List<Topic> findByTitleContainingOrTextContaining(String searchWordInTitle, String searchWordInText);

    @Query("SELECT t,r FROM Topic t,Reply r GROUP BY t.title ORDER BY r.dateOfPublication DESC")
    List<Topic> getNewReply(Pageable pageable);

    
    @Query("SELECT t FROM Topic t GROUP BY t.title ORDER BY t.dateOfPublication DESC")
    List<Topic> getNewPost(Pageable pageable);
   
    //    @Query("SELECT t,r FROM Topic t,Reply r ORDER BY COUNT(r.topic) DESC")
    @Query("SELECT t FROM Topic t GROUP BY t.title ORDER BY t.dateOfPublication DESC")
    List<Topic> getBestPost(Pageable pageable);
}
