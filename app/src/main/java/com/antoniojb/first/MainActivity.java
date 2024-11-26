package com.antoniojb.first;

import static androidx.core.view.accessibility.AccessibilityEventCompat.setAction;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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

        SharedPreferences sharedPreferences = getSharedPreferences("Usuario", MODE_PRIVATE);
        String nombre = sharedPreferences.getString("username", "anonimo");
        String password = sharedPreferences.getString("password", "contraseña");

        welcomeText.setText("Bienvenido, " + nombre);
        addedText.setText("Tu contraseña es: " + password);

        Button callDialogButton = findViewById(R.id.callDialogButton);
                callDialogButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(MainActivity.this);
                        dialogBuilder.setMessage("Hola" + nombre);
                        dialogBuilder.setCancelable(true);
                        dialogBuilder.setPositiveButton("Si, soy yo.", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(MainActivity.this, "Bienvenido.", Toast.LENGTH_SHORT).show();
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
                                                //Ir al layout de cambio de nombre de usuario
                                                Toast.makeText(MainActivity.this, "Cambiando nombre...", Toast.LENGTH_SHORT).show();
                                            }
                                        });
                                dialogBuilder2.setNegativeButton("No", new DialogInterface.OnClickListener() {

                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        //Cerrar la aplicación
                                        Toast.makeText(MainActivity.this, "No se cambió el nombre.", Toast.LENGTH_SHORT).show();
                                        AlertDialog alertDialog2 = dialogBuilder2.create();
                                        alertDialog2.show();
                                        dialogInterface.cancel();
                                    }
                                })
                                        .show();
                            }
                        });
                        AlertDialog alertDialog = dialogBuilder.create();
                        alertDialog.show();

                        welcomeText.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                MaterialAlertDialogBuilder materialAlertDialogBuilder = new MaterialAlertDialogBuilder(MainActivity.this)
                                        .setIcon(R.drawable.logo)
                                        .setTitle("Hola " + nombre)
                                        .setMessage("Bienvendios a nuevo estilo de Alert Dialog")
                                        .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                Snackbar.make(view, "Si ese no es tu nombre puedes cambiarlo", Snackbar.LENGTH_SHORT)
                                                        .setAction("Cambiar", new View.OnClickListener() {
                                                            @Override
                                                            public void onClick(View view) {
                                                                Toast.makeText(MainActivity.this, "Clickado", Toast.LENGTH_SHORT).show();
                                                            }
                                                        })
                                                        .show();
                                            }
                                        })
                                        .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                Toast.makeText(MainActivity.this, "Has cancelado", Toast.LENGTH_SHORT).show();
                                            }
                                        })
                                        .setView(R.layout.custom_alert_dialog);
                                materialAlertDialogBuilder.show();
                            }
                        });
                        welcomeText.setOnClickListener(new View.OnClickListener() {

                            @Override
                            public void onClick(View view) {

                            }
                        });

                    }
                });
    }
}
