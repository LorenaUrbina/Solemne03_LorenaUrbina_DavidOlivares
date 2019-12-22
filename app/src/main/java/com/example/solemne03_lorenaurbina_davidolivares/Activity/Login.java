package com.example.solemne03_lorenaurbina_davidolivares.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import com.example.solemne03_lorenaurbina_davidolivares.R;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {

    public static String UID;
    private EditText email,pass;
    private Button ingresar,registrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.txtEmail);
        pass = findViewById(R.id.txtPass);
        ingresar = findViewById(R.id.btnLogin);
        registrar = findViewById(R.id.btnRegistrar);

        ingresar.setOnClickListener(v->ProcesoLogin(
                email.getText().toString(),
                pass.getText().toString()
        ));
        registrar.setOnClickListener(v->{
            Intent intent = new Intent(this, Registro.class);
            startActivity(intent);
        });
    }

    private void ProcesoLogin(String email,String pass){
        if(email.isEmpty()){
            Toast.makeText(this, getString(R.string.errorLogin), Toast.LENGTH_SHORT).show();
            return;
        }
        if(pass.isEmpty()){
            Toast.makeText(this, getString(R.string.errorLogin), Toast.LENGTH_SHORT).show();
            return;
        }

        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth.signInWithEmailAndPassword(email,pass)
                .addOnSuccessListener(auth->{
                    UID = auth.getUser().getUid();
                    Intent intent = new Intent(this, MainActivity.class);
                    startActivity(intent);
                })
                .addOnFailureListener(auth->{
                    Toast.makeText(this, getString(R.string.errorLogin), Toast.LENGTH_SHORT).show();
                    System.out.println("ERROR_AUTH_FIREBASE: "+auth.getMessage());
                });
    }
}
