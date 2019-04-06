package org.pet.social.controllers;

import org.pet.social.BLL.contracts.UserControlInterface;
import org.pet.social.BLL.contracts.entity.ProblemServiceInterface;
import org.pet.social.BLL.implementation.UserControlService;
import org.pet.social.DAL.contracts.UserInterface;
import org.pet.social.common.entity.Problem;
import org.pet.social.common.entity.User;
import org.pet.social.common.enums.ProblemStatus;
import org.pet.social.common.exceptions.*;
import org.pet.social.common.responses.Response;
import org.pet.social.common.viewmodels.AddProblemViewModel;
import org.pet.social.utils.AuthUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
public class ProblemController extends BaseController {
    @Autowired
    private ProblemServiceInterface problemServiceInterface;
    @Autowired
    private UserControlInterface userControl;

    AuthUtils authUtils;

    @GetMapping("/problem/get")
    public @ResponseBody
    Response get(HttpServletResponse response,
                 @RequestParam(required = false) Integer id,
                 @RequestParam(value = "100", required = false) Integer limit,
                 @RequestParam(value = "0", required = false) Integer offset) {

        if (id == null) {
            return this.success(response, problemServiceInterface.getLimited(ProblemStatus.MODERATION,limit, offset));
        }

        Optional<Problem> problem = problemServiceInterface.get(id);
        if (problem.isPresent()) {
            return this.success(response, problem.get());
        }

        return this.error(response, 404, "Проблема не обнаружена. Радуйтесь");
    }

    @GetMapping("/problem/")
    public @ResponseBody
    Response getMock(HttpServletResponse response,
                     @RequestParam(required = false) Integer id,
                     @RequestParam(value = "100", required = false) Integer limit,
                     @RequestParam(value = "0", required = false) Integer offset) {

        if (id == null) {
            List<Problem> mockProblems = new ArrayList<>();

            for (int i = 0; i < 5; i++) {
                Problem problem = new Problem();
                problem.setId(i);
                problem.setTitle("Test title for your mock problem with id " + i);
                problem.setText("Пробелма у меня такая. Вот есть Настя по фамилии Гранчак. Стерва блять такая. Пиздов ей надо нахуярить");

                mockProblems.add(problem);
            }

            return this.success(response, mockProblems);
        }

        Optional<Problem> problem = problemServiceInterface.get(id);
        if (problem.isPresent()) {
            return this.success(response, problem.get());
        }

        return this.error(response, 404, "Проблема не обнаружена. Радуйтесь");
    }

    @CrossOrigin(origins="*")
    @PostMapping("/problems/add")
    public @ResponseBody
    Response add(
                HttpServletRequest request,
                HttpServletResponse response,
                @RequestBody AddProblemViewModel model
    ) {
        if(authUtils == null) authUtils = new AuthUtils(userControl);
        User user = authUtils.getCurrentUser(request);
        if (user == null) {
            return unauthorized(response);
        }

        if (problemServiceInterface.add(user, model)) {
            return this.success(response, problemServiceInterface.getProblem(), 201);
        }

        return this.error(response, 501);
    }

    @GetMapping("/problems/approve")
    public @ResponseBody
    Response approve(HttpServletRequest request,HttpServletResponse response, @RequestParam Integer id) {

        if(authUtils == null) authUtils = new AuthUtils(userControl);

        boolean isLogined = authUtils.getCurrentUser(request) != null;
        if (!isLogined) {
            return this.error(response, 401);
        }

        try {
            User user = userControl.getUser();
            if (problemServiceInterface.approve(id, user.getId())) {
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
    public @ResponseBody
    Response resolve(HttpServletResponse response, @RequestParam Integer id) {
        boolean isLogined = userControl.getUser() != null;
        if (!isLogined) {
            return this.error(response, 401);
        }

        try {
            User user = userControl.getUser();
            if (problemServiceInterface.resolve(id, user.getId())) {
                return this.success(response, "Успешно");
            }
        } catch (ProblemNotApprovedException e) {
            return this.error(response, 400, e.getMessage());
        } catch (ObjectNotFoundException e) {
            return this.error(response, 404, e.getMessage());
        }
        return this.error(response);
    }

    @GetMapping("/problems/moderate")
    public @ResponseBody
    Response moderate(HttpServletResponse response, @RequestParam Integer id) {
        boolean isLogined = userControl.getUser() != null;
        if (!isLogined) {
            return this.error(response, 401);
        }

        User user = new User(); // TODO: get from service
        try {
            if(problemServiceInterface.moderate(id, user)) {
                return this.success(response, "Успешно");
            }
        } catch (NotModeratorException e) {
            return this.error(response, 403, e.getMessage());
        } catch (ObjectNotFoundException e) {
            return this.error(response, 404, e.getMessage());
        } catch (ShouldNotModerateException e) {
            return this.error(response, 400, e.getMessage());
        }

        return this.error(response, 500);
    }
}
