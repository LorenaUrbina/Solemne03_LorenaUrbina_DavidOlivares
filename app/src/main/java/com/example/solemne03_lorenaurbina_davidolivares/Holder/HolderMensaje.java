package com.example.solemne03_lorenaurbina_davidolivares.Holder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.solemne03_lorenaurbina_davidolivares.R;


public class HolderMensaje extends RecyclerView.ViewHolder {

    private TextView mensaje,hora;

    public HolderMensaje(@NonNull View itemView) {
        super(itemView);

        mensaje = itemView.findViewById(R.id.mensajeMensaje);
        hora = itemView.findViewById(R.id.horaMensaje);
    }

    public TextView getMensaje() {
        return mensaje;
    }

    public void setMensaje(TextView mensaje) {
        this.mensaje = mensaje;
    }

    public TextView getHora() {
        return hora;
    }

    public void setHora(TextView hora) {
        this.hora = hora;
    }
}
