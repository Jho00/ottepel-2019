package org.pet.social.controllers;

import com.sun.org.apache.xpath.internal.operations.Mult;
import org.pet.social.BLL.implementation.PhotoService;
import org.pet.social.common.entity.Photo;
import org.pet.social.common.responses.ErrorResponse;
import org.pet.social.common.responses.Response;
import org.pet.social.common.responses.SuccessResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping(path="/photos")
public class PhotoController extends BaseController{

    @Autowired
    PhotoService photos;

    @PostMapping(path="/add")
    public @ResponseBody Response Add(HttpServletResponse response,
                                      @RequestParam("images") MultipartFile[] images,
                                      @RequestParam Integer userId,
                                      @RequestParam Integer problemId){
        if(images == null || images.length == 0 || userId == null || problemId == null) return this.error(response, 400, "Неверный запрос!");
        if(photos.AddMany(images, userId, problemId)) return this.success(response,"", 201);
        return this.error(response, 501);
    }

    @GetMapping(path = "/get")
    public @ResponseBody Response Get(HttpServletResponse response, @RequestParam Integer imageId)
    {
        if(imageId != null){
            Photo photo = photos.Get(imageId);
            if(photo != null) return this.success(response,photo);
            return this.error(response, 501);
        }
        return this.error(response, 400);
    }
}
