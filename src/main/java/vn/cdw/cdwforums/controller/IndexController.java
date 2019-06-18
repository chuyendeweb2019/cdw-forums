package vn.cdw.cdwforums.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import vn.cdw.cdwforums.reponsitory.SectionRepository;
import vn.cdw.cdwforums.reponsitory.TopicRepository;

@Controller
@RequestMapping("/")
public class IndexController {
	
	private TopicRepository topicRepository;
    private SectionRepository sectionRepository;

    @Autowired
    public IndexController(SectionRepository sectionRepository, TopicRepository topicRepository) {
        this.sectionRepository = sectionRepository;
        this.topicRepository  = topicRepository;
    }

    @GetMapping
    public String index(ModelMap model) {
        model.addAttribute("title", "CDW2019 - iTForum");

        model.addAttribute("sections", sectionRepository.findAllByParent(null));
        model.addAttribute("topic",topicRepository.getNewReply(new PageRequest(0,5)));
        model.addAttribute("topic2",topicRepository.getNewPost(new PageRequest(0,5)));
        model.addAttribute("topic3",topicRepository.getBestPost(new PageRequest(0,5)));

        return "index";
    }
}