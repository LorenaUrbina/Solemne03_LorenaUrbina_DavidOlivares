package com.example.solemne03_lorenaurbina_davidolivares.Entidades;
import java.util.Map;

public class MensajeEnviar extends Mensaje{
    private Map hora;

    public MensajeEnviar() {
    }

    public MensajeEnviar(Map hora) {
        this.hora = hora;
    }

    public MensajeEnviar(String mensaje, Map hora) {
        super(mensaje);
        this.hora = hora;
    }

    public Map getHora() {
        return hora;
    }

    public void setHora(Map hora) {
        this.hora = hora;
    }
}
