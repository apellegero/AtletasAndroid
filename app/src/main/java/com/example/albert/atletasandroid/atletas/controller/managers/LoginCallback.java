package com.example.albert.atletasandroid.atletas.controller.managers;
import com.example.albert.atletasandroid.atletas.model.UserToken;

public interface LoginCallback {
    void onSuccess(UserToken userToken);
    void onFailure(Throwable t);
}