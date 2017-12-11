package com.hellmail.hellmail.Vistas.Presenters;

import com.hellmail.hellmail.Dominio.CallBackInteractor;
import com.hellmail.hellmail.Dominio.ILUsuario;
import com.hellmail.hellmail.Dominio.LUsuario;
import com.hellmail.hellmail.Vistas.Fragmentos.ILoginFragment;

/**
 * * Created by Hellmail on 11-Nov-17.
 */

public class LoginPresenter implements ILoginPresenter {
    private ILoginFragment view;
    private ILUsuario lUsuario;

    public LoginPresenter(ILoginFragment view) {
        this.view = view;
        lUsuario = new LUsuario();
    }

    @Override
    public void login(String email, String password) {
        view.deshabilitarVistas();
        view.mostrarProgress();

        try {
            lUsuario.authUsuario(email, password, new CallBackInteractor<String>() {
                @Override
                public void success(String uid) {
                    view.habilitarVistas();
                    view.ocultarProgress();
                    view.irAPerfil(uid);
                }

                @Override
                public void error(String error) {
                    view.habilitarVistas();
                    view.ocultarProgress();
                    view.mostrarError(error);
                }
            });
        } catch (Exception e) {
            view.habilitarVistas();
            view.ocultarProgress();
            view.mostrarError(e.getMessage());
        }
    }
}
