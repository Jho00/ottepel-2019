package org.pet.social.controllers;

import org.pet.social.common.responses.Response;
import org.pet.social.common.servicesClasses.GeoPoint;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
public class ProblemController extends BaseController {

    @GetMapping("/problem/get")
    public @ResponseBody
    Response get(HttpServletResponse response, @RequestParam(required = false) int id, @RequestParam(value = "100", required = false) Integer limit) {
        return this.error(response, 501);
    }

    @PostMapping("/problems/add")
    public @ResponseBody Response add(HttpServletResponse response, @RequestParam String body, @RequestParam GeoPoint point) {
        return this.error(response, 501);
    }


}
