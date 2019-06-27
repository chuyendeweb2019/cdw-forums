package vn.cdw.cdwforums.reponsitory;

import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import vn.cdw.cdwforums.entity.Section;

public interface SectionRepository extends PagingAndSortingRepository<Section, Long>{

	Set<Section> findAllByParent(Section parent);

    Page<Section> findByTitleContainingOrTextContaining(String searchWordInTitle, String searchWordInText, Pageable pageable);
    List<Section> findByTitleContaining(String searchWordInTitle);

}
