package org.pet.social.BLL.contracts;

import org.pet.social.common.entity.Photo;

import java.util.List;

public interface PhotoServiceInterface {
    Photo Add(String image, int userId, int problemId);
    Photo Get(int photoId);
    List<Photo> GetByProblem(int problemId);
    Photo GetOneByProblem(int problemId);
}
