package vn.cdw.cdwforums.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import vn.cdw.cdwforums.reponsitory.SectionRepository;

@Controller
@RequestMapping("/")
public class IndexController {

    private SectionRepository sectionRepository;

    @Autowired
    public IndexController(SectionRepository sectionRepository) {
        this.sectionRepository = sectionRepository;
    }

    @GetMapping
    public String index(ModelMap model) {
        model.addAttribute("title", "CDW2019 - iTForum");

        model.addAttribute("sections", sectionRepository.findAllByParent(null));
        return "index";
    }
}