package com.hellmail.hellmail.Dominio;

import com.hellmail.hellmail.Modelo.Perfil;
import com.hellmail.hellmail.Modelo.Usuario;


/**
 * Created by jggomez on 24-Oct-17.
 */

public interface ILUsuario {

    void crearUsuario(String password, Usuario usuario,
                      CallBackInteractor<String> callBackInteractor);

    void authUsuario(String email, String password,
                     CallBackInteractor<String> callBackInteractor);

    void verperfil(CallBackInteractor<Perfil> callBackInteractor);

    void guardarPerfil(String nombre, int edad, long celular, String password);

}
