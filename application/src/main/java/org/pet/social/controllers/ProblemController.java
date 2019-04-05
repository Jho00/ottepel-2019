package org.pet.social.controllers;

import org.pet.social.BLL.contracts.entity.ProblemServiceInterface;
import org.pet.social.common.entity.User;
import org.pet.social.common.enums.Resolvers;
import org.pet.social.common.exceptions.ObjectNotFoundException;
import org.pet.social.common.exceptions.ProblemNotApprovedException;
import org.pet.social.common.exceptions.ProblemShouldNotApprove;
import org.pet.social.common.responses.Response;
import org.pet.social.common.servicesClasses.GeoPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
public class ProblemController extends BaseController {
    @Autowired
    private ProblemServiceInterface problemServiceInterface;

    @GetMapping("/problem/get")
    public @ResponseBody
    Response get(HttpServletResponse response,
                 @RequestParam(required = false) Integer id,
                 @RequestParam(value = "100", required = false) Integer limit,
                 @RequestParam(value = "0", required = false) Integer offset) {

        if(id == null) {
            return this.success(response, problemServiceInterface.getLimited(limit, offset));
        }

        var problem = problemServiceInterface.get(id);
        if(problem.isPresent()) {
            return this.success(response, problem.get());
        }

        return this.error(response, 404, "Проблема не обнаружена. Радуйтесь");
    }

    @PostMapping("/problems/add")
    public @ResponseBody Response add(HttpServletResponse response,
                                      @RequestParam String title,
                                      @RequestParam String body,
                                      @RequestParam GeoPoint point) {
        User user = new User(); // TODO: get from service
        if(problemServiceInterface.add(user, title, body, point)) {
            return this.success(response, "Успешно", 201);
        }

        return this.error(response, 501);
    }

    @PostMapping("/problems/approve")
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

    @PostMapping("/problems/resolve")
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
