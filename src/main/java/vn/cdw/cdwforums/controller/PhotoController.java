package vn.cdw.cdwforums.controller;


import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import vn.cdw.cdwforums.entity.Photo;
import vn.cdw.cdwforums.reponsitory.PhotoRepository;

@Controller
public class PhotoController {

    private PhotoRepository photoRepository;

    @Autowired
    public PhotoController(PhotoRepository photoRepository) {
        this.photoRepository = photoRepository;
    }

    @GetMapping("/photo")
    public void showImage(@RequestParam("id") Long imageId, HttpServletResponse response) throws IOException {

        Photo photo = photoRepository.findById(imageId).orElse(new Photo());
        response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
        response.getOutputStream().write(photo.getPhoto());
        response.getOutputStream().close();
    }
}
