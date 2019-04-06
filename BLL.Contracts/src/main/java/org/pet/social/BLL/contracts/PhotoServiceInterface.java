package org.pet.social.BLL.contracts;

import org.pet.social.common.entity.Photo;

public interface PhotoServiceInterface {
    Integer Add(byte[] image, String imageFormat,int userId, int problemId);
    Photo Get(int photoId);
}
