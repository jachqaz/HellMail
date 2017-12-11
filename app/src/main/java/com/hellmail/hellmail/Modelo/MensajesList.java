package com.hellmail.hellmail.Modelo;

import java.util.HashMap;

/**
 * Created by Rodolhan on 14/11/2017.
 */

public class MensajesList {

    private HashMap<String, Mensajes> enviados = new HashMap<>();
    private HashMap<String, Mensajes> recibidos = new HashMap<>();
    public MensajesList() {
    }

    public HashMap<String, Mensajes> getRecibidos() {
        return recibidos;
    }

    public void setRecibidos(HashMap<String, Mensajes> recibidos) {
        this.recibidos = recibidos;
    }

    public HashMap<String, Mensajes> getEnviados() {
        return enviados;
    }

    public void setEnviados(HashMap<String, Mensajes> enviados) {
        this.enviados = enviados;
    }
}
