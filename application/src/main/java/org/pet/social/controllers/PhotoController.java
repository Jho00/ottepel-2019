package org.pet.social.controllers;

import org.pet.social.BLL.contracts.UserControlInterface;
import org.pet.social.BLL.implementation.PhotoService;
import org.pet.social.common.consts.AuthConstHolder;
import org.pet.social.common.entity.Photo;
import org.pet.social.common.entity.User;
import org.pet.social.common.responses.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping(path = "/photos")
public class PhotoController extends BaseController {

    @Autowired
    PhotoService photos;
    @Autowired
    private UserControlInterface userControl;

    @PostMapping(path = "/add")
    public @ResponseBody
    Response Add(
                 HttpServletRequest request,
                 HttpServletResponse response,
                 @RequestParam Integer problemId,
                 @RequestParam(name="images", required=false) MultipartFile[] images) {

        String token = request.getHeader(AuthConstHolder.HTTP_AUTH_TOKEN_HEADER_NAME);
        if (token == null) {
            return this.error(response, 401, "Требуется авторизация!");
        }

        User user = userControl.getUserByToken(token);

        if(user == null){
            return this.error(response, 401, "Требуется авторизация!");
        }

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
