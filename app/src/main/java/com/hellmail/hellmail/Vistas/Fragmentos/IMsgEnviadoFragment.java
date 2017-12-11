package com.hellmail.hellmail.Vistas.Fragmentos;

import android.view.View;

/**
 * Created by Duvan on 11/11/2017.
 */

public interface IMsgEnviadoFragment {
    void AddMensajeEnviados(View view);

    void errorenviar(String error);
    void refrescarListaMensajesEnviados();
}
