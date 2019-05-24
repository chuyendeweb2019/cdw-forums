package vn.cdw.cdwforums.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import vn.cdw.cdwforums.entity.Section;
import vn.cdw.cdwforums.reponsitory.SectionRepository;
import vn.cdw.cdwforums.reponsitory.TopicRepository;
import vn.cdw.cdwforums.util.ForumConstants;
import vn.cdw.cdwforums.util.ResourceNotFoundException;

@Controller
@RequestMapping("/section")
public class SectionController {

	private SectionRepository sectionRepository;
	private TopicRepository topicRepository;

	@Autowired
	public SectionController(SectionRepository sectionRepository, TopicRepository topicRepository) {
		this.sectionRepository = sectionRepository;
		this.topicRepository = topicRepository;
	}

	@GetMapping("/{id}")
	public String view(@PathVariable Long id, ModelMap model, @PageableDefault(sort = {
			"dateOfPublication" }, value = ForumConstants.PAGE_DEFAULT_SIZE, direction = Sort.Direction.DESC) Pageable pageable) {

		if (!sectionRepository.existsById(id)) {
			throw new ResourceNotFoundException();
		}

		Section section = sectionRepository.findById(id).get();
		
		model.addAttribute("title", section.getTitle());
		model.addAttribute("section", section);
		model.addAttribute("topics", topicRepository.findBySection(section, pageable));
		return "section/view";
	}

	@GetMapping("/")
	public String redirect() {
		return "redirect:/";
	}
}
