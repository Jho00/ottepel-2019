package org.pet.social.common.viewmodels;

import org.pet.social.common.entity.Problem;

public class GetProblemViewModel {
    private Problem problem;
    private String[] images;

    public Problem getProblem() {
        return problem;
    }

    public void setProblem(Problem problem) {
        this.problem = problem;
    }

    public String[] getImages() {
        return images;
    }

    public void setImages(String[] images) {
        this.images = images;
    }
}
