package org.pet.social.BLL.implementation;

import org.pet.social.BLL.contracts.PhotoServiceInterface;
import org.pet.social.DAL.contracts.PhotoInterface;
import org.pet.social.common.entity.Photo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;

@Service
public class PhotoService implements PhotoServiceInterface {

    @Autowired
    PhotoInterface photos;
    private final String[] photoAcceptableFormats = {"image/jpeg"};

    public Photo Add(byte[] image, String imageFormat, int userId, int problemId) {

        boolean rightFormat = false;
        for (String f : photoAcceptableFormats) {
            if (f.equals(imageFormat)) {
                rightFormat = true;
            }
        }
        if (!rightFormat) {
            return null;
        }

        Photo p = new Photo();
        p.setImage(image);
        p.setImageFormat(imageFormat);
        p.setProblemId(problemId);
        p.setUserId(userId);
        p.setAddedAt(new Timestamp(System.currentTimeMillis()));
        p = photos.save(p);

        if (p == null) {
            return null;
        }
        return p;
    }

    public boolean AddMany(MultipartFile[] images, int problemId, int userId) {
        ArrayList<Photo> pics = new ArrayList<>(images.length);
        try {
            for (MultipartFile file : images) {
                Photo p = Add(file.getBytes(), file.getContentType(), userId, problemId);
                if (p != null) {
                    pics.add(p);
                }
            }

            if (pics.size() != images.length) {
                return false;
            }
        } catch (IOException ex) {
            return false;
        }
        return true;
    }

    public Photo Get(int photoId) {
        return photos.findById(photoId).orElse(null);
    }
}
