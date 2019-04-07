package org.pet.social.controllers;

import org.pet.social.BLL.contracts.PhotoServiceInterface;
import org.pet.social.BLL.contracts.UserControlInterface;
import org.pet.social.BLL.contracts.entity.ProblemServiceInterface;
import org.pet.social.BLL.implementation.UserControlService;
import org.pet.social.DAL.contracts.UserInterface;
import org.pet.social.common.entity.Photo;
import org.pet.social.common.entity.Problem;
import org.pet.social.common.entity.User;
import org.pet.social.common.enums.ProblemStatus;
import org.pet.social.common.exceptions.*;
import org.pet.social.common.responses.Response;
import org.pet.social.common.viewmodels.AddProblemViewModel;
import org.pet.social.common.viewmodels.GetProblemViewModel;
import org.pet.social.utils.AuthUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@RestController
@CrossOrigin(origins = "*")
public class ProblemController extends BaseController {
    @Autowired
    private ProblemServiceInterface problemServiceInterface;
    @Autowired
    private UserControlInterface userControl;
    @Autowired
    private PhotoServiceInterface photos;

    AuthUtils authUtils;

    @GetMapping("/problem/get")
    public @ResponseBody
    Response get(HttpServletResponse response,
                 @RequestParam(required = false) Integer id,
                 @RequestParam(required = false) Integer limit,
                 @RequestParam(required = false) Integer offset) {

        if (id == null) {
            if(offset == null) offset = 0;
            if(limit == null) limit = 50;

            Pageable pageable = PageRequest.of(offset, limit, Sort.Direction.DESC, "createdAt");
            Page<Problem> problems = problemServiceInterface.getLimited(ProblemStatus.MODERATION, pageable);

            GetProblemViewModel[] gets = new GetProblemViewModel[(int)problems.getTotalElements()];
            List<Problem> strm = problems.getContent();
            for(int i =0;i<gets.length;i++){
                gets[i] = createGetProblemViewModel(strm.get(i), true);
            }
            return this.success(response, gets);
        }

        Optional<Problem> problem = problemServiceInterface.get(id);
        if (problem.isPresent()) {
            GetProblemViewModel prob = createGetProblemViewModel(problem.get(), false);
            return this.success(response, prob);
        }

        return this.error(response, 404, "Проблема не обнаружена. Радуйтесь");
    }

    GetProblemViewModel createGetProblemViewModel(Problem problem, boolean onlyTop1){
        GetProblemViewModel prob = new GetProblemViewModel();

        List<Photo> phots = new ArrayList<>();
        if(!onlyTop1) phots.addAll(photos.GetByProblem(problem.getId()));
        else{
            Photo el = photos.GetOneByProblem(problem.getId());
            if(el != null)
            phots.add(el);
        }
        String[] arrs = new String[phots.size()];

        for(int i = 0;i<phots.size();i++) {
            arrs[i] = phots.get(i).getData();
        }

        prob.setProblem(problem);
        prob.setImages(arrs);
        return prob;
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
            User user = authUtils.getCurrentUser(request);
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
