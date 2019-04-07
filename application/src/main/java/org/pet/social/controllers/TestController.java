package org.pet.social.controllers;

import org.pet.social.BLL.implementation.entity.ProblemService;
import org.pet.social.DAL.contracts.CommentInterface;
import org.pet.social.DAL.contracts.PhotoInterface;
import org.pet.social.DAL.contracts.ProblemInterface;
import org.pet.social.DAL.contracts.UserInterface;
import org.pet.social.common.entity.Comment;
import org.pet.social.common.entity.Photo;
import org.pet.social.common.entity.Problem;
import org.pet.social.common.entity.User;
import org.pet.social.common.enums.ProblemStatus;
import org.pet.social.common.responses.ErrorResponse;
import org.pet.social.common.responses.Response;
import org.pet.social.common.responses.SuccessResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;

@RestController
@RequestMapping(path = "/tests")
public class TestController extends BaseController{
    @Autowired
    UserInterface users;
    @Autowired
    ProblemInterface problems;
    @Autowired
    ProblemService probServ;
    @Autowired
    PhotoInterface photos;
    @Autowired
    CommentInterface comments;

    @GetMapping(path = "/user")
    public @ResponseBody
    Response GetUser() {
        Iterator<User> it = users.findAll().iterator();
        if (it.hasNext()) {
            return new SuccessResponse(it.next());
        } else return new ErrorResponse();
    }

    @GetMapping(path = "/problem")
    public @ResponseBody
    Response GetProblem() {
        Iterator<Problem> it = problems.findAll().iterator();
        if (it.hasNext()) {
            return new SuccessResponse(it.next());
        } else return new ErrorResponse();
    }

    @GetMapping(path = "/problem-to-not-confirmed")
    public @ResponseBody
    Response ProblemToNotConfirmed() {
        Iterator<Problem> it = problems.findAll().iterator();
        if (it.hasNext()) {
            Problem ne = it.next();
            ne.setStatus(ProblemStatus.NOT_CONFIRMED);
            problems.save(ne);
            return new SuccessResponse(ne);
        } else return new ErrorResponse();
    }

    @GetMapping(path = "/problem-to-confirmed")
    public @ResponseBody
    Response ProblemToConfirmed() {
        Iterator<Problem> it = problems.findAll().iterator();
        if (it.hasNext()) {
            Problem ne = it.next();
            ne.setStatus(ProblemStatus.CONFIRMED);
            problems.save(ne);
            return new SuccessResponse(ne);
        } else return new ErrorResponse();
    }

    @GetMapping(path = "/problem-to-resolved")
    public @ResponseBody
    Response ProblemToResolved() {
        Iterator<Problem> it = problems.findAll().iterator();
        if (it.hasNext()) {
            Problem ne = it.next();
            ne.setStatus(ProblemStatus.RESOLVED);
            problems.save(ne);
            return new SuccessResponse(ne);
        } else return new ErrorResponse();
    }

    @GetMapping(path = "/problems")
    public @ResponseBody
    Response GetProblems() {
        Iterable<Problem> it = probServ.getLimited(ProblemStatus.MODERATION, new PageRequest(0, 100));
        return  new SuccessResponse(it);
    }


    @GetMapping(path = "/comment")
    public @ResponseBody
    Response GetComment() {
        Iterator<Comment> it = comments.findAll().iterator();
        if (it.hasNext()) {
            return new SuccessResponse(it.next());
        } else return new ErrorResponse();
    }

    @GetMapping(path = "/photo")
    public @ResponseBody
    Response GetPhoto() {
        Iterator<Photo> it = photos.findAll().iterator();
        if (it.hasNext()) {
            return new SuccessResponse(it.next());
        } else return new ErrorResponse();
    }

}
