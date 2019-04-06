package org.pet.social.controllers;

import org.pet.social.BLL.contracts.entity.ProblemServiceInterface;
import org.pet.social.DAL.contracts.UserInterface;
import org.pet.social.common.entity.Problem;
import org.pet.social.common.entity.User;
import org.pet.social.common.exceptions.ObjectNotFoundException;
import org.pet.social.common.exceptions.ProblemNotApprovedException;
import org.pet.social.common.exceptions.ProblemShouldNotApprove;
import org.pet.social.common.responses.Response;
import org.pet.social.common.viewmodels.AddProblemViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Optional;

@RestController
public class ProblemController extends BaseController {
    @Autowired
    private ProblemServiceInterface problemServiceInterface;
    @Autowired
    private UserInterface users;


    @GetMapping("/problem/get")
    public @ResponseBody
    Response get(HttpServletResponse response,
                 @RequestParam(required = false) Integer id,
                 @RequestParam(value = "100", required = false) Integer limit,
                 @RequestParam(value = "0", required = false) Integer offset) {

        if(id == null) {
            return this.success(response, problemServiceInterface.getLimited(limit, offset));
        }

        Optional<Problem> problem = problemServiceInterface.get(id);
        if(problem.isPresent()) {
            return this.success(response, problem.get());
        }

        return this.error(response, 404, "Проблема не обнаружена. Радуйтесь");
    }

    @PostMapping("/problems/add")
    public @ResponseBody Response add(HttpServletResponse response,
                                      @RequestBody @Valid  AddProblemViewModel model
    ) {
        User user = users.findById(4).get(); // TODO: get from service
        if(problemServiceInterface.add(user, model)) {
            return this.success(response, "Успешно", 201);
        }

        return this.error(response, 501);
    }

    @GetMapping("/problems/approve")
    public @ResponseBody Response approve(HttpServletResponse response, @RequestParam Integer id) {
        try {
            if(problemServiceInterface.approve(id)) {
                return this.success(response, "Успешно");
            }
        } catch (ProblemShouldNotApprove problemShouldNotApprove) {
            return this.error(response, 400, problemShouldNotApprove.getMessage());
        } catch (ObjectNotFoundException e) {
            return this.error(response, 404, e.getMessage());
        }

        return this.error(response);
    }

    @GetMapping("/problems/resolve")
    public @ResponseBody Response resolve(HttpServletResponse response, @RequestParam Integer id) {
        try {
            if(problemServiceInterface.resolve(id)) {
                return this.success(response, "Успешно");
            }
        } catch (ProblemNotApprovedException e) {
            return this.error(response, 400, e.getMessage());
        } catch (ObjectNotFoundException e) {
            return this.error(response, 404, e.getMessage());
        }
        return  this.error(response);
    }
}
