package vn.cdw.cdwforums.reponsitory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import vn.cdw.cdwforums.entity.Section;
import vn.cdw.cdwforums.entity.Topic;

public interface TopicRepository extends PagingAndSortingRepository<Topic, Long> {

	Page<Topic> findBySection(Section section, Pageable pageable);

    Page<Topic> findByTitleContainingOrTextContaining(String searchWordInTitle, String searchWordInText, Pageable pageable);
    
}
