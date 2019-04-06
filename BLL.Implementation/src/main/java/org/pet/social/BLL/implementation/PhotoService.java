package org.pet.social.BLL.implementation;

import org.pet.social.BLL.contracts.PhotoServiceInterface;
import org.pet.social.DAL.contracts.PhotoInterface;
import org.pet.social.common.entity.Photo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
public class PhotoService implements PhotoServiceInterface {

    @Autowired
    PhotoInterface photos;
    private final String[] photoAcceptableFormats = {"image/jpeg"};

    public Photo Add(String image, int userId, int problemId) {

//        boolean rightFormat = false;
//        for (String f : photoAcceptableFormats) {
//            if (f.equals(imageFormat)) {
//                rightFormat = true;
//            }
//        }
//        if (!rightFormat) {
//            return null;
//        }

        Photo p = new Photo();
        p.setData(image);
//        p.setImageFormat(imageFormat);
        p.setProblemId(problemId);
        p.setUserId(userId);
        p.setAddedAt(new Timestamp(System.currentTimeMillis()));
        p = photos.save(p);

        if (p == null) {
            return null;
        }
        return p;
    }

    public boolean AddMany(String[] base64Images, int problemId, int userId) {
        ArrayList<Photo> pics = new ArrayList<>(base64Images.length);
        for (String file : base64Images) {
            Photo p = Add(file, userId, problemId);
            if (p != null) {
                pics.add(p);
            }
        }

        if (pics.size() != base64Images.length) {
            return false;
        }
        return true;
    }

    public Photo Get(int photoId) {
        return photos.findById(photoId).orElse(null);
    }
    public List<Photo> GetByProblem(int problemId) {
        return photos.findByProblemId(problemId);
    }
}
