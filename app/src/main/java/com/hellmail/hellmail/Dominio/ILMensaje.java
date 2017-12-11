package com.hellmail.hellmail.Dominio;

import com.hellmail.hellmail.Modelo.Mensajes;

import java.util.List;

/**
 * Created by Rodolhan on 16/11/2017.
 */

public interface ILMensaje {
    void addMensajeRec(Mensajes mensaje);

    List<Mensajes> getMensajesRec();

    void addMensajeEnv(Mensajes mensaje);

    List<Mensajes> getMensajesEnv();

    void Enviar(Mensajes mensaje, String email, CallBackInteractor<String> callBackInteractor);

}