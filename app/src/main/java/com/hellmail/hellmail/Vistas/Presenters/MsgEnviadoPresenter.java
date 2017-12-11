package com.hellmail.hellmail.Vistas.Presenters;

import com.hellmail.hellmail.Dominio.CallBackInteractor;
import com.hellmail.hellmail.Dominio.ILMensaje;
import com.hellmail.hellmail.Dominio.ILUsuario;
import com.hellmail.hellmail.Dominio.LMensaje;
import com.hellmail.hellmail.Dominio.LUsuario;
import com.hellmail.hellmail.Modelo.Mensajes;
import com.hellmail.hellmail.Vistas.Fragmentos.IMsgEnviadoFragment;

import java.util.List;

/**
 * Created by Rodolhan on 13/11/2017.
 */

public class MsgEnviadoPresenter implements IMsgEnviadoPresenter {
    private IMsgEnviadoFragment view;
    private ILUsuario lUsuario;
    private ILMensaje lMensaje;

    public MsgEnviadoPresenter(IMsgEnviadoFragment view) {
        this.view = view;
        lUsuario = new LUsuario();
        lMensaje = new LMensaje();
    }

    @Override
    public void addMensaje(String mensaje, String remitente, String fecha) {
        Mensajes objMensaje = new Mensajes();
        objMensaje.setMensaje(mensaje);
        objMensaje.setRemitente(remitente);
        objMensaje.setFecha(fecha);
        lMensaje.Enviar(objMensaje, remitente, new CallBackInteractor<String>() {
            @Override
            public void success(String data) {
                view.errorenviar(data);
            }

            @Override
            public void error(String error) {

            }
        });
        view.refrescarListaMensajesEnviados();
    }

    @Override
    public List<Mensajes> obtenerMensajesEnviados() {
        return lMensaje.getMensajesEnv();
    }


}
