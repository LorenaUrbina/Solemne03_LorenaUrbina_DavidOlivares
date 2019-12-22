package com.example.solemne03_lorenaurbina_davidolivares.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.solemne03_lorenaurbina_davidolivares.Adapter.AdapterMensaje;
import com.example.solemne03_lorenaurbina_davidolivares.SQLite.DBSQLite;
import com.example.solemne03_lorenaurbina_davidolivares.Entidades.MensajeEnviar;
import com.example.solemne03_lorenaurbina_davidolivares.Entidades.MensajeRecibir;
import com.example.solemne03_lorenaurbina_davidolivares.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;

public class MainActivity extends AppCompatActivity {

    private EditText mensaje;
    private RecyclerView rvMensajes;
    private Button btnEnviar,cerrarSesion;

    private AdapterMensaje adapter;

    private FirebaseDatabase db;
    private DatabaseReference root;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Buscar por id
        mensaje = findViewById(R.id.txtMensaje);
        rvMensajes = findViewById(R.id.rvMensajes);
        btnEnviar = findViewById(R.id.btnEnviar);
        cerrarSesion = findViewById(R.id.btnCerrar);

        //Conexion con Firebase
        db = FirebaseDatabase.getInstance();
        root = db.getReference("chat");
        adapter = new AdapterMensaje(this);
        LinearLayoutManager l = new LinearLayoutManager(this);

        //llenar recyclerview segun la posicion del adaptador
        rvMensajes.setLayoutManager(l);
        rvMensajes.setAdapter(adapter);

        //conexion a base de datos en SQLite

        final DBSQLite dbsqLite = new DBSQLite(getApplicationContext());

        btnEnviar.setOnClickListener(v ->{

            String msg = mensaje.getText().toString();
            if(msg.isEmpty()){

                Toast.makeText(this,getString(R.string.errorVacio), Toast.LENGTH_SHORT).show();

            }else {

                dbsqLite.AgregarMensaje(mensaje.getText().toString());
                root.push().setValue(new MensajeEnviar(mensaje.getText().toString(), ServerValue.TIMESTAMP));
                mensaje.setText("");
            }
        });
        cerrarSesion.setOnClickListener(v->{
            FirebaseAuth.getInstance().signOut();
            finish();
        });

        adapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onItemRangeInserted(int positionStart, int itemCount) {
                super.onItemRangeInserted(positionStart, itemCount);
                Scroll();
            }
        });


        //no sobre escribe el mensaje en firebase lo agrega
        root.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                MensajeRecibir m = dataSnapshot.getValue(MensajeRecibir.class);
                adapter.addMensaje(m);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void Scroll(){
        rvMensajes.scrollToPosition(adapter.getItemCount()-1);
    }
}
