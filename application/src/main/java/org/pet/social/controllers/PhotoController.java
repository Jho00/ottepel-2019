package org.pet.social.controllers;

import org.pet.social.BLL.implementation.PhotoService;
import org.pet.social.common.entity.Photo;
import org.pet.social.common.entity.User;
import org.pet.social.common.responses.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;

import javax.servlet.MultipartConfigElement;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping(path = "/photos")
public class PhotoController extends BaseController {

    @Autowired
    PhotoService photos;

    @PostMapping(path = "/add")
    public @ResponseBody
    Response Add(
                 HttpServletRequest request,
                 HttpServletResponse response,
                 @RequestParam Integer problemId,
                 @RequestParam(name="images", required = false) MultipartFile[] images) {

        if (images != null) System.out.println("not null" + images.length);
        else System.out.println("NUUUL");

        User user = authUtils.getCurrentUser(request);
        if (images == null || images.length == 0 || problemId == null) {
            return this.error(response, 400, "Неверный запрос!");
        }

        if (photos.AddMany(images, user.getId(), problemId)) {
            return this.success(response, "", 201);
        }

        return this.error(response, 500);
    }

    @GetMapping(path = "/get")
    public @ResponseBody
    Response Get(HttpServletResponse response, @RequestParam Integer imageId) {
        if (imageId != null) {
            Photo photo = photos.Get(imageId);
            if (photo != null) {
                return this.success(response, photo);
            }
            return this.error(response, 500);
        }
        return this.error(response, 400);
    }
}
