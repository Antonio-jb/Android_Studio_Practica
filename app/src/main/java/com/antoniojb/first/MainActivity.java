package com.antoniojb.first;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView welcomeText = findViewById(R.id.welcomeText);
        TextView addedText = findViewById(R.id.addedText);

        SharedPreferences sharedPreferences = getSharedPreferences("Usuario", MODE_PRIVATE);
        String nombre = sharedPreferences.getString("username", "anonimo");
        String password = sharedPreferences.getString("password", "contraseña");

        welcomeText.setText("Bienvenido, " + nombre);
        addedText.setText("Tu contraseña es: " + password);
    }

}