package org.pet.social.controllers;

import org.pet.social.BLL.contracts.CommentsServiceInterface;
import org.pet.social.BLL.implementation.CommentsService;
import org.pet.social.DAL.contracts.CommentInterface;
import org.pet.social.DAL.contracts.ProblemInterface;
import org.pet.social.DAL.contracts.UserInterface;
import org.pet.social.common.entity.Comment;
import org.pet.social.common.entity.Problem;
import org.pet.social.common.entity.User;
import org.pet.social.common.responses.ErrorResponse;
import org.pet.social.common.responses.Response;
import org.pet.social.common.responses.SuccessResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;

@RestController
@RequestMapping(path="/comments")
public class CommentsController extends BaseController {

    @Autowired
    CommentsServiceInterface commentService;

    @PostMapping(path="/add")
    public @ResponseBody Response addComment (HttpServletResponse response, @RequestParam String text,
                                            @RequestParam int problemId,
                                            @RequestParam int userId)
    {
        Response resp = null;
        if(commentService.Add(text, problemId, userId)){
            resp = this.success(response, "");
        }else{
            resp = this.error(response, 500);
        }
        return resp;
    }

    @GetMapping(path="/get")
    public  @ResponseBody Response getComment(HttpServletResponse response, @RequestParam int commentId)
    {
        Response resp = null;
        Comment c = commentService.Get(commentId);
        if(c != null){
            resp  = this.success(response, c);
        }else{
            resp = new ErrorResponse();
        }
        return resp;
    }

    @PostMapping(path="/like")
    public  @ResponseBody Response likeComment(HttpServletResponse response, @RequestParam int commentId,
                                               @RequestParam int userId)
    {
        Response resp = null;
        if(commentService.Like(userId, commentId)){
            resp = this.success(response, "");
        }else{
            resp = this.error(response, 500);
        }
        return resp;
    }

    @PostMapping(path="/dislike")
    public  @ResponseBody Response dislikeComment(HttpServletResponse response, @RequestParam int commentId,
                                               @RequestParam int userId)
    {
        Response resp = null;
        if(commentService.Dislike(userId, commentId)){
            resp = this.success(response, "");
        }else{
            resp = this.error(response, 500);
        }
        return resp;
    }

   /*
    @Autowired
    UserInterface users;
    @Autowired
    ProblemInterface problems;

    @GetMapping(path="/test")
    public @ResponseBody Response testComment(){

        User u = new User();
        u.setEmail("kamalovak@bk.ru");
        u.setFirstName("Akmal");
        u.setLastName("Kamalov");
        u.setRegisterAt(new Timestamp(System.currentTimeMillis()));
        u.setBirthday(new Timestamp(System.currentTimeMillis()));
        u.setPasswordHash("asf322faf");
        u.setRole("default");
        u.setSalt("123414123123aq3rf23#");
        u.setAccountStatus("normie");
        u = users.save(u);

        Problem p = new Problem();
        p.setCategoryId(0);
        p.setText("GREAT");
        p.setTitle("Good");
        p.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        p.setUserId(u.getId());
        problems.save(p);

        Object[] arr = {u,p};

        return new SuccessResponse(arr);
    }*/
}
