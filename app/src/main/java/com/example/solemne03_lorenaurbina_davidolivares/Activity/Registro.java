package com.example.solemne03_lorenaurbina_davidolivares.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.solemne03_lorenaurbina_davidolivares.Activity.Login;
import com.example.solemne03_lorenaurbina_davidolivares.R;
import com.google.firebase.auth.FirebaseAuth;


public class Registro extends AppCompatActivity {


    private EditText email,pass,nombre;
    private Button btnRegistro, btnLogeo;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        email = findViewById(R.id.txtEmailR);
        pass = findViewById(R.id.txtPassR);
        btnRegistro = findViewById(R.id.btnRegistro);
        btnLogeo = findViewById(R.id.btnLogeo);

        btnRegistro.setOnClickListener(v->ProcesoRegistro(

                email.getText().toString(),
                pass.getText().toString()
        ));

        btnLogeo.setOnClickListener(v->{
            Intent intent = new Intent(this, Login.class);
            startActivity(intent);
        });
    }

    private void ProcesoRegistro(String email,String pass){
        if(email.isEmpty()){
            Toast.makeText(this, getString(R.string.errorLogin), Toast.LENGTH_SHORT).show();
            return;
        }
        if(pass.isEmpty()){
            Toast.makeText(this, getString(R.string.errorLogin), Toast.LENGTH_SHORT).show();
            return;
        }

        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth.createUserWithEmailAndPassword(email,pass)
                .addOnSuccessListener(auth ->{
                    Intent intent = new Intent(this, Login.class);
                    startActivity(intent);


                })
                .addOnFailureListener(auth->{
                    Toast.makeText(this, getString(R.string.errorLogin), Toast.LENGTH_SHORT).show();
                    System.out.println("ERROR_AUTH_FIREBASE: "+auth.getMessage());
                });
    }
}
