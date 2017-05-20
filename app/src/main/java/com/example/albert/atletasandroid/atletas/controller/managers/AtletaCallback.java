package com.example.albert.atletasandroid.atletas.controller.managers;

import com.example.albert.atletasandroid.atletas.model.Atleta;

import java.util.List;

public interface AtletaCallback {
    void onSuccess(List<Atleta> atletasList);
    void onSucces();
    void onSucces(Atleta atleta);
    void onFailure(Throwable t);
}
