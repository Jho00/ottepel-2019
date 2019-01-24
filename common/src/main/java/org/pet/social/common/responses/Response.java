package org.pet.social.common.responses;

import org.springframework.web.bind.annotation.ResponseBody;

@ResponseBody
public abstract class Response {
    protected int code;

    public int getCode() {
        return code;
    }
}
