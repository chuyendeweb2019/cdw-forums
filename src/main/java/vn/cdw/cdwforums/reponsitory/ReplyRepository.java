package vn.cdw.cdwforums.reponsitory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import vn.cdw.cdwforums.entity.Reply;
import vn.cdw.cdwforums.entity.Topic;

public interface ReplyRepository extends PagingAndSortingRepository<Reply, Long> {

	Page<Reply> findByTopic(Topic topic, Pageable pageable);

    Page<Reply> findByTextContaining(String searchWord, Pageable pageable);
    
}
