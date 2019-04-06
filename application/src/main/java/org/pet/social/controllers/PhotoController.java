package org.pet.social.controllers;

import org.pet.social.BLL.implementation.PhotoService;
import org.pet.social.common.entity.Photo;
import org.pet.social.common.responses.ErrorResponse;
import org.pet.social.common.responses.Response;
import org.pet.social.common.responses.SuccessResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping(path="/photos")
public class PhotoController {

    @Autowired
    PhotoService photos;
    private final String[] photoAcceptableFormats = {"image/jpeg"};


    @PostMapping(path="/add")
    public @ResponseBody Response Add(@RequestParam("file") MultipartFile file, @RequestParam Integer userId, @RequestParam Integer problemId){
        String format = file.getContentType();
        boolean rightFormat = false;
        for(String f : photoAcceptableFormats){
            if(f.equals(format)) rightFormat = true;
        }

        if(!rightFormat) return new ErrorResponse();

        if(file.isEmpty() || userId == null || problemId == null) return new ErrorResponse();

        try {
            byte[] bytes = file.getBytes();
            Integer img = photos.Add(bytes, format, userId, problemId);
            if(img!=null) return new SuccessResponse(img);
            return new ErrorResponse();
        }
        catch (IOException ex){
            return new ErrorResponse();
        }
    }

    @GetMapping(path = "/get")
    public @ResponseBody Response Get(@RequestParam Integer imageId)
    {
        if(imageId != null){
            Photo photo = photos.Get(imageId);
            if(photo != null) return new SuccessResponse(photo);
        }
        return new ErrorResponse();
    }
}
