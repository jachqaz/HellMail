package com.hellmail.hellmail.Vistas.Presenters;

import com.hellmail.hellmail.Dominio.CallBackInteractor;
import com.hellmail.hellmail.Dominio.ILUsuario;
import com.hellmail.hellmail.Dominio.LUsuario;
import com.hellmail.hellmail.Modelo.Usuario;
import com.hellmail.hellmail.Vistas.Fragmentos.IRegistroFragment;

/**
 * Created by jarog on 11/11/2017.
 */

public class RegistroPresenter implements IRegistroPresenter {
    private IRegistroFragment view;
    private ILUsuario lUsuario;

    public RegistroPresenter(IRegistroFragment view) {
        this.view = view;
        lUsuario = new LUsuario();
    }

    @Override
    public void registrar(String nombres, String email,
                          String password) {

        view.deshabilitarControles();
        view.mostrarProgress();

        try {
            Usuario usuario = new Usuario();
            usuario.setEmail(email);
            usuario.setNombres(nombres);

            lUsuario.crearUsuario(password, usuario, new CallBackInteractor<String>() {
                @Override
                public void success(String uid) {
                    view.habilitarControles();
                    view.ocultarProgress();
                    view.irAPerfil(uid);
                }

                @Override
                public void error(String error) {
                    view.habilitarControles();
                    view.ocultarProgress();
                    view.mostrarError(error);
                }
            });


        } catch (Exception e) {
            view.habilitarControles();
            view.ocultarProgress();
            view.mostrarError(e.getMessage());
        }

    }
}
