package com.hellmail.hellmail.Vistas.Fragmentos;

/**
 * Created by Duvan on 11/11/2017.
 */

public interface IPerfilFrangment {
    void habilitarControles();
    void deshabilitarControles();
    void Guardar();
    void mostrarError(String error);
    void irAMsg();

    void avatar();

    void modificarPerfil(String nombre, int edad, long celular, String password);
}
