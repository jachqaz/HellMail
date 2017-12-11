package com.hellmail.hellmail.Vistas.Fragmentos;

/**
 * Created by jarog on 11/11/2017.
 */

public interface ILoginFragment {
    void habilitarVistas();
    void deshabilitarVistas();
    void mostrarProgress();
    void ocultarProgress();
    void login();
    void irARegistro();

    void irAPerfil(String uid);
    void mostrarError(String mensaje);
}
