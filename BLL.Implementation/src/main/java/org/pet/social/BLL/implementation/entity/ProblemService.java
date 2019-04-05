package org.pet.social.BLL.implementation.entity;

import org.pet.social.BLL.contracts.entity.ProblemServiceInterface;
import org.pet.social.DAL.contracts.ProblemInterface;
import org.pet.social.common.entity.Problem;
import org.pet.social.common.entity.User;
import org.pet.social.common.enums.ProblemStatus;
import org.pet.social.common.enums.Resolvers;
import org.pet.social.common.exceptions.ObjectNotFoundException;
import org.pet.social.common.exceptions.ProblemNotApprovedException;
import org.pet.social.common.exceptions.ProblemShouldNotApprove;
import org.pet.social.common.servicesClasses.GeoPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
public class ProblemService implements ProblemServiceInterface {
    @Autowired
    private ProblemInterface problems;

    @Override
    public List<Problem> getLimited(Integer limit, Integer offset) {
       return problems.findTopByOrderById(PageRequest.of(offset, limit));
    }

    @Override
    public Optional<Problem> get(Integer id) {
        return problems.findById(id);
    }

    @Override
    public boolean add(User user, String title, String body, GeoPoint point) {
        Problem problem = new Problem();
        problem.setStatus(ProblemStatus.NOT_CONFIRMED);
        problem.setText(body);
        problem.setTitle(title);
        problem.setUserId(user.getId());
        problem.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        problem.setLat(point.getLat());
        problem.setLon(point.getLon());

        return problems.save(problem) != null;
    }

    @Override
    public boolean resolve(Integer id) throws ProblemNotApprovedException, ObjectNotFoundException {
        var problem = problems.findById(id);

        if(!problem.isPresent()) {
            throw new ObjectNotFoundException("Проблема не найдена");
        }

        Problem readyproblem = problem.get();

        if (readyproblem.getStatus() != ProblemStatus.CONFIRMED) {
            throw new ProblemNotApprovedException("Проблема не была подтверждена");
        }

        readyproblem.setResolveCount(readyproblem.getResolveCount() + 1);

        if(readyproblem.getResolveCount() < 5) {
            return false;
        }

        readyproblem.setStatus(ProblemStatus.RESOLVED);

        return problems.save(readyproblem) != null;
    }

    @Override
    public boolean approve(Integer id) throws ProblemShouldNotApprove, ObjectNotFoundException {
        var problem = problems.findById(id);

        if(!problem.isPresent()) {
            throw new ObjectNotFoundException("Проблема не найдена");
        }

        Problem readyproblem = problem.get();

        if(readyproblem.getStatus() != ProblemStatus.NOT_CONFIRMED) {
            throw new ProblemShouldNotApprove("Проблему не надо подтверждать");
        }

        readyproblem.setApproveCount(readyproblem.getApproveCount() + 1);

        if(readyproblem.getApproveCount() < 5) {
            return false;
        }


        readyproblem.setStatus(ProblemStatus.CONFIRMED);

        return problems.save(readyproblem) != null;
    }

    /**
     * Для того, на, чтобы установить кто же зарезолвил сию проблему на
     * @param id
     * @param resolver
     * @throws ObjectNotFoundException
     */
    @Override
    public void setResolver(Integer id, Resolvers resolver) throws ObjectNotFoundException {
        var problem = problems.findById(id);

        if(!problem.isPresent()) {
            throw new ObjectNotFoundException("Проблема не найдена");
        }

        Problem readyproblem = problem.get();

        readyproblem.setResolver(resolver);

        problems.save(readyproblem);
    }


}
