package com.example.albert.atletasandroid.atletas.controller.managers;

import android.util.Base64;
import android.util.Log;

import com.example.albert.atletasandroid.atletas.controller.services.TokenService;
import com.example.albert.atletasandroid.atletas.model.UserToken;
import com.example.albert.atletasandroid.atletas.util.CustomProperties;

import java.io.UnsupportedEncodingException;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UserTokenManager {
    private static UserTokenManager ourInstance;
    private Retrofit retrofit;
    private TokenService tokenService;
    private String grantType;
    private String scope;
    private String client_id;
    private String client_secret;
    private String authorization;
    public static UserTokenManager getInstance() {
        if(ourInstance == null){
            ourInstance = new UserTokenManager();
        }
        return ourInstance;
    }
    private UserTokenManager() {
        try {
            //grant_type=password&scope=read%20write&client_secret=mySecretOAuthSecret&client_id=atletaapp
            grantType = CustomProperties.grantType;
            scope = CustomProperties.scope;
            client_secret = CustomProperties.clientSecret;
            client_id = CustomProperties.clientId;
            String source = client_id + ":" + client_secret;
            byte[] byteArray = source.getBytes("UTF-8");
            authorization = "Basic " + Base64.encodeToString(byteArray, 0, byteArray.length, Base64.DEFAULT).trim();

            retrofit = new Retrofit.Builder()
                    .baseUrl(CustomProperties.baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            tokenService = retrofit.create(TokenService.class);
        } catch (UnsupportedEncodingException e) {
            Log.e("UserTokenManager->", "constructor->source.getBytes('UTF-8') ERROR: " + e);
        }
    }
    public synchronized Call<UserToken> getUserToken(String username, String password) {
        Call<UserToken> call = tokenService.requestToken(authorization, username, password, grantType, scope, client_secret, client_id);
        return call;
    }
}
