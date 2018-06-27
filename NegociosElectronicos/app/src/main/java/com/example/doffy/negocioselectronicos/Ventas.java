package com.example.doffy.negocioselectronicos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class Ventas extends AppCompatActivity {

    /*DECLARACION DE VARIABLES*/
    //Interfaz
    private ImageButton btnAddProducto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ventas);
        /*INICIALIZACION DE VARIABLES*/
        //Interfaz
        btnAddProducto = (ImageButton) findViewById(R.id.btnAddProducto);

        //evento click
        btnAddProducto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iCrudProducto= new Intent(Ventas.this,CrudProducto.class);
                startActivity(iCrudProducto);
            }
        });
    }
}
