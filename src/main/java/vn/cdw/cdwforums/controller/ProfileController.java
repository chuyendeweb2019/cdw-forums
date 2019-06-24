package vn.cdw.cdwforums.controller;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Objects;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import net.coobird.thumbnailator.Thumbnails;
import vn.cdw.cdwforums.controller.form.ChangePasswordForm;
import vn.cdw.cdwforums.entity.Photo;
import vn.cdw.cdwforums.entity.User;
import vn.cdw.cdwforums.reponsitory.PhotoRepository;
import vn.cdw.cdwforums.reponsitory.UserRepository;
import vn.cdw.cdwforums.service.UserService;
import vn.cdw.cdwforums.util.ForumConstants;


@Controller
@PreAuthorize("isAuthenticated()")
public class ProfileController {

    private UserService userService;
    private PasswordEncoder passwordEncoder;
    private UserRepository userRepository;
    private PhotoRepository photoRepository;

    @Autowired
    public ProfileController(UserService userService, PasswordEncoder passwordEncoder, UserRepository userRepository, PhotoRepository photoRepository) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.photoRepository = photoRepository;
    }

    @GetMapping("/profile")
    public String profile(Model model) {
        model.addAttribute("title", "Trang cá nhân");
        model.addAttribute("user", userRepository.findById(userService.getCurrentUser().getId()).orElse(new User()));
        return "profile/view";
    }

    @GetMapping("/profile/edit/photo")
    public String editPhoto(Model model) {
        model.addAttribute("title", "Sửa ảnh đại diện");
        model.addAttribute("user", userRepository.findById(userService.getCurrentUser().getId()).orElse(new User()));
        return "profile/edit/photo";
    }

    @PostMapping("/profile/edit/photo")
    public String updatePhoto(@RequestParam("file") MultipartFile file) {

        if (Objects.nonNull(file) && (!file.isEmpty())) {
            try {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                Thumbnails.of(new ByteArrayInputStream(file.getBytes()))
                        .forceSize(ForumConstants.PHOTO_WIDTH, ForumConstants.PHOTO_HEIGHT)
                        .toOutputStream(byteArrayOutputStream);

                User user = userRepository.findById(userService.getCurrentUser().getId()).orElse(new User());
                Photo oldUserPhoto = photoRepository.findByUserId(user.getId());

                if (Objects.nonNull(oldUserPhoto)) {
                    photoRepository.save(new Photo(oldUserPhoto.getId(), byteArrayOutputStream.toByteArray(), user));
                } else {
                    photoRepository.save(new Photo(byteArrayOutputStream.toByteArray(), user));
                }

                return "redirect:/profile/";

            } catch (Exception e) {
                return "redirect:/profile/edit/photo";
            }

        } else {
            return "redirect:/profile/edit/photo";
        }
    }

    @GetMapping("/profile/edit/password")
    public String editPassword(Model model) {
        model.addAttribute("title", "Thay đổi mật khẩu");
        model.addAttribute("changePasswordForm", new ChangePasswordForm());
        model.addAttribute("minLengthPassword", ForumConstants.PASSWORD_LENGTH_MIN);
        model.addAttribute("maxLengthPassword", ForumConstants.PASSWORD_LENGTH_MAX);
        return "profile/edit/password";
    }

    @PostMapping("/profile/edit/password")
    public String updatePassword(@Valid ChangePasswordForm changePasswordForm, BindingResult result) {

        if (result.hasErrors()) {
            return "profile/edit/password";
        }

        User user = userRepository.findById(userService.getCurrentUser().getId()).orElse(new User());
        user.setPassword(passwordEncoder.encode(changePasswordForm.getNewPassword()));
        userRepository.save(user);

        return "redirect:/profile";
    }

    @GetMapping("/profile/edit/")
    public String edit() {
        return "redirect:/profile";
    }
}
