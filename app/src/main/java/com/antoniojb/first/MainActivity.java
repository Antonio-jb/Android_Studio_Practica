package com.antoniojb.first;

import static androidx.core.view.accessibility.AccessibilityEventCompat.setAction;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView welcomeText = findViewById(R.id.welcomeText);
        TextView addedText = findViewById(R.id.addedText);

        SharedPreferences preferences = getSharedPreferences("Usuario", MODE_PRIVATE);
        String nombre = preferences.getString("username", "anonimo");
        String password = preferences.getString("password", "contraseña");

        welcomeText.setText("Bienvenido, " + nombre + ".");
        addedText.setText("Tu contraseña es: " + password + ".");

        Button callDialogButton = findViewById(R.id.callDialogButton);
        callDialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

              //AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(MainActivity.this);
              //dialogBuilder.setMessage("Hola" + nombre);
              //dialogBuilder.setCancelable(true);
              //dialogBuilder.setPositiveButton("Si, soy yo.", new DialogInterface.OnClickListener() {
              //    @Override
              //    public void onClick(DialogInterface dialogInterface, int i) {
              //        Toast.makeText(MainActivity.this, "Bienvenido.", Toast.LENGTH_SHORT).show();
              //        dialogInterface.cancel();
              //    }
              //});

                MaterialAlertDialogBuilder dialogBuilder = new MaterialAlertDialogBuilder(MainActivity.this)
                        .setIcon(R.drawable.logo)
                        .setTitle("Bienvenido")
                        .setMessage("Hola, te llamas " + nombre.toUpperCase() + "?")
                        .setPositiveButton("Si, soy yo.", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(MainActivity.this, "Bienvenido " + nombre.toUpperCase() + ".", Toast.LENGTH_SHORT).show();
                        dialogInterface.cancel();
                    }
                });
                dialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        MaterialAlertDialogBuilder dialogBuilder2 = new MaterialAlertDialogBuilder(MainActivity.this)
                                .setIcon(R.drawable.logo)
                                .setTitle("Confirmación")
                                .setMessage("¿Quieres cambiar tu nombre?")
                                .setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        Snackbar.make(view, "Deseas cambiar tu nombre?", Snackbar.LENGTH_SHORT)
                                                .setAction("Cambiar", new View.OnClickListener() {
                                                    @Override
                                                    public void onClick(View view) {
                                                        LayoutInflater layoutInflater = LayoutInflater.from(MainActivity.this);
                                                        final View inflator = layoutInflater.inflate(R.layout.custom_alert_dialog, null);
                                                        MaterialAlertDialogBuilder materialAlertDialogBuilder = new MaterialAlertDialogBuilder(MainActivity.this)
                                                                .setIcon(R.drawable.logo)
                                                                .setTitle("Cambiar nombre")
                                                                .setView(inflator)
                                                                .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                                                                    @Override
                                                                    public void onClick(DialogInterface dialogInterface, int i) {
                                                                        EditText nuevoNombre = inflator.findViewById(R.id.nombreNuevo);
                                                                        String nombreCambiado = nuevoNombre.getText().toString();

                                                                        SharedPreferences preferences = getSharedPreferences("Usuario", Context.MODE_PRIVATE);
                                                                        SharedPreferences.Editor editor = preferences.edit();
                                                                        editor.putString("nombre", nombreCambiado);
                                                                        editor.apply(); // Hay que Asegurarse de aplicar los cambios.
                                                                        welcomeText.setText("Bienvenido, tu nombre ahora es " + nombreCambiado + ".");
                                                                    }
                                                                });
                                                        materialAlertDialogBuilder.show();
                                                    }
                                                })
                                                .show();
                                    }
                                });

                                dialogBuilder2.setNegativeButton("No", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        Toast.makeText(MainActivity.this, "No se ha cambiado el nombre.", Toast.LENGTH_SHORT).show();
                                        dialogInterface.cancel();
                                    }
                                })
                                .show();
                    }
                });

                AlertDialog alertDialog = dialogBuilder.create();
                alertDialog.show();

            }
        });
    }
}
