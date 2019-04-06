package org.pet.social.controllers;

import org.pet.social.DAL.contracts.CommentInterface;
import org.pet.social.DAL.contracts.PhotoInterface;
import org.pet.social.DAL.contracts.ProblemInterface;
import org.pet.social.DAL.contracts.UserInterface;
import org.pet.social.common.entity.Comment;
import org.pet.social.common.entity.Photo;
import org.pet.social.common.entity.Problem;
import org.pet.social.common.entity.User;
import org.pet.social.common.responses.ErrorResponse;
import org.pet.social.common.responses.Response;
import org.pet.social.common.responses.SuccessResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Iterator;
import java.util.function.Consumer;

@RestController
@RequestMapping(path="/tests")
public class TestController {
    @Autowired
    UserInterface users;
    @Autowired
    ProblemInterface problems;
    @Autowired
    PhotoInterface photos;
    @Autowired
    CommentInterface comments;

    @GetMapping(path="/user")
    public @ResponseBody
    Response GetUser(){
        Iterator<User> it = users.findAll().iterator();
        if(it.hasNext()) {
            return new SuccessResponse(it.next());
        }
        else return new ErrorResponse();
    }

    @GetMapping(path="/problem")
    public @ResponseBody
    Response GetProblem(){
        Iterator<Problem> it = problems.findAll().iterator();
        if(it.hasNext()) {
            return new SuccessResponse(it.next());
        }
        else return new ErrorResponse();
    }

    @GetMapping(path="/comment")
    public @ResponseBody
    Response GetComment(){
        Iterator<Comment> it = comments.findAll().iterator();
        if(it.hasNext()) {
            return new SuccessResponse(it.next());
        }
        else return new ErrorResponse();
    }

    @GetMapping(path="/photo")
    public @ResponseBody
    Response GetPhoto(){
        Iterator<Photo> it = photos.findAll().iterator();
        if(it.hasNext()) {
            return new SuccessResponse(it.next());
        }
        else return new ErrorResponse();
    }

}
