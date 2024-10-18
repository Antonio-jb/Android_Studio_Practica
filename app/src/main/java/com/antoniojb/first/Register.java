    package com.antoniojb.first;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputLayout;

    public class Register extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        TextInputLayout registerUsernameTIL = findViewById(R.id.registerUsernameTIL);
        TextInputLayout registerPasswordTIL = findViewById(R.id.registerPasswordTIL);
        TextInputLayout registerPasswordConfirmTIL = findViewById(R.id.registerPasswordConfirmTIL);
        Button registerButton = findViewById(R.id.registerButton);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userName = String.valueOf(registerUsernameTIL.getEditText().getText()).trim();
                String userPassword = String.valueOf(registerPasswordTIL.getEditText().getText()).trim();
                String userPasswordCheck = String.valueOf(registerPasswordConfirmTIL.getEditText().getText()).trim();

                // Validación de campos vacíos
                if (userName.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Por favor, ingrese un nombre de usuario", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (userPassword.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Por favor, ingrese una contraseña", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!userPassword.equals(userPasswordCheck)) {
                    Toast.makeText(getApplicationContext(), "Tus contraseñas no coinciden", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Guardar en SharedPreferences
                SharedPreferences preferences = getSharedPreferences("Usuario", MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("username", userName);
                editor.putString("password", userPassword);
                editor.apply();

                Toast.makeText(getApplicationContext(), "Registro exitoso", Toast.LENGTH_SHORT).show();
                launchLogin();
            }
        });

    }
        public void launchLogin() {
            Intent intent = new Intent(Register.this, Login.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
}