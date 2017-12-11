package com.hellmail.hellmail.Vistas.Presenters;

/**
 * Created by jarog on 12/11/2017.
 */

public interface IPerfilPresenter {
    void verPerfil();

    void guardarPerfil(String nombre, int edad, long celular, String password);
}
