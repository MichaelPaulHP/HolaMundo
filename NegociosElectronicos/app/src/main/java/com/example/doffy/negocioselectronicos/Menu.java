package com.example.doffy.negocioselectronicos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

public class Menu extends AppCompatActivity {

    /*DECLARACION DE VARIABLES*/

    //Variables de interfaz
    private ImageButton btnShowOpcions;
    private ImageButton btnHideOpcions;
    private LinearLayout llShowOpcions;
    private LinearLayout llHideOpcions;
    private LinearLayout llOpciones;
    private ImageButton btnVentas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        /*INICIALIZACION DE VARIABLES*/
        //Variables de interfaz
        llShowOpcions = (LinearLayout) findViewById(R.id.llShowOpcions);
        llHideOpcions = (LinearLayout) findViewById(R.id.llHideOpcions);
        llOpciones = (LinearLayout) findViewById(R.id.llOpciones);
        btnShowOpcions = (ImageButton) findViewById(R.id.btnShowOpcions);
        btnHideOpcions = (ImageButton) findViewById(R.id.btnHideOpcions);
        btnVentas =(ImageButton) findViewById(R.id.btnVentas);

        btnShowOpcions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                llShowOpcions.setVisibility(View.GONE);
                llHideOpcions.setVisibility(View.VISIBLE);
                llOpciones.setVisibility(View.VISIBLE);
            }
        });
        btnHideOpcions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                llShowOpcions.setVisibility(View.VISIBLE);
                llHideOpcions.setVisibility(View.GONE);
                llOpciones.setVisibility(View.GONE);
            }
        });

        btnVentas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iVentas=new Intent(Menu.this,Ventas.class);
                startActivity(iVentas);
                //finish();
            }
        });


    }

}
