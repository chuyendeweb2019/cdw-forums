package vn.cdw.cdwforums.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import vn.cdw.cdwforums.entity.Topic;
import vn.cdw.cdwforums.model.SearchAjaxResponse;
import vn.cdw.cdwforums.model.SearchModel;
import vn.cdw.cdwforums.reponsitory.ReplyRepository;
import vn.cdw.cdwforums.reponsitory.SectionRepository;
import vn.cdw.cdwforums.reponsitory.TopicRepository;
import vn.cdw.cdwforums.util.ForumConstants;

@Controller
public class SearchController {

	private TopicRepository topicRepository;
	private ReplyRepository replyRepository;
	private SectionRepository sectionRepository;

	@Autowired
	public SearchController(TopicRepository topicRepository, ReplyRepository replyRepository,
			SectionRepository sectionRepository) {
		this.topicRepository = topicRepository;
		this.replyRepository = replyRepository;
		this.sectionRepository = sectionRepository;
	}

	@GetMapping("/search")
	public String search(ModelMap model) {
		model.addAttribute("title", "Tìm kiếm");
		return "search/search";
	}

	@RequestMapping(value = "/api/timkiem", method ={ RequestMethod.GET, RequestMethod.POST })
	public ResponseEntity<?> searchResult(@RequestBody SearchModel searchModel, ModelMap model,
			 Pageable pageable) {
		System.out.println("---------------------------------------");
		model.addAttribute("title", "Tìm kiếm");
		
		SearchAjaxResponse result = new SearchAjaxResponse();
		List<?> topicsList = new ArrayList<>();
		Page<?> resultPage = null;

		
        switch (searchModel.getSearchIn()) {
            case ForumConstants.SEARCH_IN_SECTIONS:
            	resultPage =  sectionRepository.findByTitleContainingOrTextContaining(searchModel.getSearchWord(), searchModel.getSearchWord(), pageable);
                break;
            case ForumConstants.SEARCH_IN_TOPICS:
            	resultPage = topicRepository.findByTitleContainingOrTextContaining(searchModel.getSearchWord(), searchModel.getSearchWord(), pageable);
            	break;
            case ForumConstants.SEARCH_IN_REPLIES:
            	resultPage = replyRepository.findByTextContaining(searchModel.getSearchWord(), pageable);
                break;
        }
        topicsList = resultPage.getContent();
//        topicsList = resultPage.getContent();
    	
        System.out.println("########"+resultPage.toString());
    	
		return ResponseEntity.ok(topicsList);
	}
}
