package com.hellmail.hellmail.Vistas.Presenters;

import com.hellmail.hellmail.Dominio.CallBackInteractor;
import com.hellmail.hellmail.Dominio.ILUsuario;
import com.hellmail.hellmail.Dominio.LUsuario;
import com.hellmail.hellmail.Modelo.Perfil;
import com.hellmail.hellmail.Vistas.Fragmentos.IPerfilFrangment;


/**
 * Created by jarog on 12/11/2017.
 */

public class PerfilPresenter implements IPerfilPresenter {
    private IPerfilFrangment view;
    private ILUsuario lUsuario;

    public PerfilPresenter(IPerfilFrangment view) {
        this.view = view;
        lUsuario = new LUsuario();
    }

    @Override
    public void verPerfil() {
        lUsuario.verperfil(new CallBackInteractor<Perfil>() {
            @Override
            public void success(Perfil data) {
                view.modificarPerfil(data.getNombres(), data.getEdad(), data.getCelular(), data.getPassword());
            }

            @Override
            public void error(String error) {

            }
        });
    }

    @Override
    public void guardarPerfil(String nombre, int edad, long celular, String password) {
        lUsuario.guardarPerfil(nombre, edad, celular, password);
    }


}
