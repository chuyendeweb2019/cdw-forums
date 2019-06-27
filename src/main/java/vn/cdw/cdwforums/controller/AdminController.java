package vn.cdw.cdwforums.controller;


import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import vn.cdw.cdwforums.entity.Role;
import vn.cdw.cdwforums.entity.Section;
import vn.cdw.cdwforums.entity.User;
import vn.cdw.cdwforums.reponsitory.RoleRepository;
import vn.cdw.cdwforums.reponsitory.SectionRepository;
import vn.cdw.cdwforums.reponsitory.UserRepository;
import vn.cdw.cdwforums.service.UserService;
import vn.cdw.cdwforums.util.ResourceNotFoundException;

@Controller
@PreAuthorize("hasRole('ADMIN')")
@RequestMapping("/admin")
public class AdminController {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private SectionRepository sectionRepository;
    @Autowired
    private UserService userService;

    @Autowired
    public AdminController(UserRepository userRepository, RoleRepository roleRepository, SectionRepository sectionRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.sectionRepository = sectionRepository;
    }
 // Hang
    @GetMapping("/users")
    public String users(ModelMap model) {
        model.addAttribute("title", "Thành viên");


        return "admin/users/list";
    }
  @PostMapping("/users1")
  @ResponseBody
    public  List<User> userPage(ModelMap model) {

    	model.addAttribute("title","Users");
    		System.out.println("bikhung");

    	return userService.findAll();
    }
 

    
    @GetMapping("/users/{id}/edit")
    public String editUser(@PathVariable Long id, ModelMap model) {

        if (!userRepository.existsById(id)) {
            throw new ResourceNotFoundException();
        }

        model.addAttribute("title", "Cập nhật tài khoản");
        model.addAttribute("user", userRepository.findById(id).orElse(new User()));
        model.addAttribute("roles", roleRepository.findAll());

        return "admin/users/edit";
    }

    @PostMapping("/users/{id}/edit")
    public String updateUser(@PathVariable Long id, @RequestParam(value = "roles", required = false) String roles) {

        if (!userRepository.existsById(id)) {
            throw new ResourceNotFoundException();
        }

        Set<Role> newRoles = new HashSet<>();

        if ((roles != null) && (!roles.isEmpty())) {

            for (String roleAuthority : roles.split(",")) {
                newRoles.add(roleRepository.findByAuthority(roleAuthority));
            }
        }

        User user = userRepository.findById(id).orElse(new User());
        user.setRoles(newRoles);
        userRepository.save(user);

        return "redirect:/admin/users";
    }

    @GetMapping("/sections")
    public String sections(ModelMap model) {
        model.addAttribute("title", "Danh mục");
        model.addAttribute("sections", sectionRepository.findAllByParent(null));

        return "admin/sections/list";
    }


    @GetMapping("/sections/add")
    public String addSection(ModelMap model) {
        model.addAttribute("title", "Thêm danh mục");
        model.addAttribute("sections", sectionRepository.findAll());
        model.addAttribute("section", new Section());
        return "admin/sections/add";
    }

    @PostMapping("/sections/add")
    public String updateSection(@Valid Section section, BindingResult result, @RequestParam(value = "parent_id", required = false) Long parentId, ModelMap model) {
        model.addAttribute("title", "Cập nhật danh mục");

        if (result.hasErrors()) {
            model.addAttribute("sections", sectionRepository.findAll());
            return "admin/sections/add";
        }

        if (Objects.nonNull(parentId) && sectionRepository.existsById(parentId)) {
            section.setParent(sectionRepository.findById(parentId).orElse(new Section()));
        }

        if (Objects.isNull(section.getId())) {
            section.setDateOfPublication(new Date());

        }

        sectionRepository.save(section);

        return "redirect:/admin/sections";
    }

    @GetMapping("/sections/{id}/edit")
    public String edit(@PathVariable Long id, ModelMap model) {
        model.addAttribute("title", "Chỉnh sửa danh mục");

        Section section = sectionRepository.findById(id).orElse(new Section());

        if (Objects.isNull(section)) {
            throw new ResourceNotFoundException();
        }

        if (Objects.nonNull(section.getParent())) {
            model.addAttribute("parent_id", section.getParent());
        }

        model.addAttribute("sections", sectionRepository.findAll());
        model.addAttribute("section", section);

        return "admin/sections/add";
    }

    @GetMapping("/sections/{id}/delete")
    public String confirmRemoval(@PathVariable Long id, ModelMap model) {
        model.addAttribute("title", "Xóa danh mục");

        if (!sectionRepository.existsById(id)) {
            throw new ResourceNotFoundException();
        }

        model.addAttribute("section", sectionRepository.findById(id).orElse(new Section()));

        return "admin/sections/delete";
    }

    @PostMapping("/sections/{id}/delete")
    public String remove(@PathVariable Long id) {

        if (!sectionRepository.existsById(id)) {
            throw new ResourceNotFoundException();
        }

        sectionRepository.deleteById(id);

        return "redirect:/admin/sections";
    }

    @GetMapping("/")
    public String index() {
        return "redirect:/";
    }
}
