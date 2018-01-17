package com.delphine.starwars.models;

/**
 * Created by Delphine on 12/01/2018.
 */

public class HttpError {
    private String message;
    private String code;

    public String getMessage() {
        return message;
    }

    public void setMessage(String newMessage){
        this.message = newMessage;
    }

    public String getCode(){
        return code;
    }

    public void setCode(String newCode){
        this.code = newCode;
    }
}
