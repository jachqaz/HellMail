package com.hellmail.hellmail.Modelo;

/**
 * Created by Rodolhan on 13/11/2017.
 */

public class Mensajes {
    String mensaje;
    String remitente;
    String fecha;
    public Mensajes() {
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getRemitente() {
        return remitente;
    }

    public void setRemitente(String remitente) {
        this.remitente = remitente;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}
