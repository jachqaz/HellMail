package com.hellmail.hellmail.Vistas.Presenters;

import com.hellmail.hellmail.Modelo.Mensajes;

import java.util.List;

/**
 * Created by Rodolhan on 13/11/2017.
 */

public interface IMsgEnviadoPresenter {
    void addMensaje(String mensaje, String remitente, String fecha);

    List<Mensajes> obtenerMensajesEnviados();
}
