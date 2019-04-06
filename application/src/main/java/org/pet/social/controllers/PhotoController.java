package org.pet.social.controllers;

import org.pet.social.BLL.contracts.UserControlInterface;
import org.pet.social.BLL.implementation.PhotoService;
import org.pet.social.common.consts.AuthConstHolder;
import org.pet.social.common.entity.Photo;
import org.pet.social.common.entity.User;
import org.pet.social.common.responses.Response;
import org.pet.social.utils.AuthUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
    @Autowired
    private UserControlInterface userControl;
    AuthUtils authUtils;

    @PostMapping(path = "/add/{problemId}")
    public @ResponseBody
    Response Add(
                 HttpServletRequest request,
                 HttpServletResponse response,
                 @PathVariable String problemId,
                 @RequestParam(name="images", required=false) MultipartFile[] images) {

        if(authUtils == null) authUtils = new AuthUtils(userControl);

        Integer prob = null;
        try{
            prob = Integer.parseInt(problemId);
        }catch (NumberFormatException num){
            return this.error(response, 400, "Не верные данные.");
        }
        User user = authUtils.getCurrentUser(request);

        if(user == null){
            return this.error(response, 401, "Требуется авторизация!");
        }

        if (images == null || images.length == 0) {
            return this.error(response, 400, "Неверный запрос, изображения не найдены!");
        }

        if (photos.AddMany(images, user.getId(), prob)) {
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
