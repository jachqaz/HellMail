package com.hellmail.hellmail.Vistas.Fragmentos;

/**
 * Created by Duvan on 11/11/2017.
 */

public interface IRegistroFragment {
    void habilitarControles();
    void deshabilitarControles();
    void mostrarProgress();
    void ocultarProgress();
    void registrar();
    void mostrarError(String error);
    void irALogin();

    void irAPerfil(String uid);
}
