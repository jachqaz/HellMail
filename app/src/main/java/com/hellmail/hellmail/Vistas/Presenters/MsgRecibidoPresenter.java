package com.hellmail.hellmail.Vistas.Presenters;

import com.hellmail.hellmail.Dominio.ILMensaje;
import com.hellmail.hellmail.Dominio.ILUsuario;
import com.hellmail.hellmail.Dominio.LMensaje;
import com.hellmail.hellmail.Dominio.LUsuario;
import com.hellmail.hellmail.Modelo.Mensajes;
import com.hellmail.hellmail.Vistas.Fragmentos.IMsgRecibidoFragment;

import java.util.List;

/**
 * Created by Rodolhan on 13/11/2017.
 */

public class MsgRecibidoPresenter implements IMsgRecibidoPresenter {
    private IMsgRecibidoFragment view;
    private ILUsuario lUsuario;
    private ILMensaje lMensaje;

    public MsgRecibidoPresenter(IMsgRecibidoFragment view) {
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
        lMensaje.addMensajeRec(objMensaje);
        view.refrescarListaMensajesRecibidos();
    }

    @Override
    public List<Mensajes> obtenerMensajesRecibidos() {

        return lMensaje.getMensajesRec();
    }


}
