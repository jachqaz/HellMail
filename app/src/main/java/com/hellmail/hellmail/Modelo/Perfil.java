package com.hellmail.hellmail.Modelo;

/**
 * Created by Rodolhan on 12/11/2017.
 */

public class Perfil {
    String nombres;
    int edad;
    long celular;
    String password;

    public Perfil() {
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public long getCelular() {
        return celular;
    }

    public void setCelular(long celular) {
        this.celular = celular;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
