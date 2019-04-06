package org.pet.social.BLL.implementation;

import org.pet.social.BLL.contracts.PhotoServiceInterface;
import org.pet.social.DAL.contracts.PhotoInterface;
import org.pet.social.common.entity.Photo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class PhotoService implements PhotoServiceInterface {

    @Autowired
    PhotoInterface photos;

    public Integer Add(byte[] image, String imageFormat,int userId, int problemId){
        Photo p = new Photo();
        p.setImage(image);
        p.setImageFormat(imageFormat);
        p.setProblemId(problemId);
        p.setUserId(userId);
        p.setAddedAt(new Timestamp(System.currentTimeMillis()));
        p = photos.save(p);

        if(p == null) return null;
        return p.getId();
    }
    public Photo Get(int photoId){
        return photos.findById(photoId).orElse(null);
    }
}
