package com.example.solemne03_lorenaurbina_davidolivares.Adapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.solemne03_lorenaurbina_davidolivares.Holder.HolderMensaje;
import com.example.solemne03_lorenaurbina_davidolivares.Entidades.MensajeRecibir;
import com.example.solemne03_lorenaurbina_davidolivares.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AdapterMensaje extends RecyclerView.Adapter<HolderMensaje> {

    private List<MensajeRecibir> listMensaje = new ArrayList<>();
    private Context c;

    public AdapterMensaje(Context c) {
        this.c = c;
    }

    public void addMensaje(MensajeRecibir m){
        listMensaje.add(m);
        notifyItemInserted(listMensaje.size());
    }

    @NonNull
    @Override
    public HolderMensaje onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(c).inflate(R.layout.activity_mensaje,parent,false);
        return new HolderMensaje(v);
    }

    @Override
    public void onBindViewHolder(@NonNull HolderMensaje holder, int position) {
        holder.getMensaje().setText(listMensaje.get(position).getMensaje());
        Long codigoDeHora = listMensaje.get(position).getHora();
        Date d = new Date(codigoDeHora);
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a"); // el comando de a es para pm o am
        holder.getHora().setText(sdf.format(d));
    }

    @Override
    public int getItemCount() {
        return listMensaje.size();
    }
}
