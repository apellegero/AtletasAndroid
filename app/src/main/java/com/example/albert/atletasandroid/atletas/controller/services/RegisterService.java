package com.example.albert.atletasandroid.atletas.controller.services;

import com.example.albert.atletasandroid.atletas.model.UserDTO;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RegisterService {
    @POST("api/register")
    Call<Void> registerAccount(
            @Body UserDTO userDTO
    );
}