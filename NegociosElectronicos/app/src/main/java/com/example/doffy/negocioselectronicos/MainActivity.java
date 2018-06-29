package com.example.doffy.negocioselectronicos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    /*[DECLARACION DE VARIABLES]*/

    //objeto de autentificacion
    private FirebaseAuth fbAuth;

    //Controles de interfaz
    private TextView txtTmpLogeado;
    private Button btnEmpezar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*INICIALIZACION DE VARIABLES*/
        //objeto de autentificacion
        fbAuth = FirebaseAuth.getInstance();
        //Controles de Interfaz
        txtTmpLogeado=(TextView) findViewById(R.id.txtTmpLogeado);
        btnEmpezar =  (Button) findViewById(R.id.btnComnezar);

        btnEmpezar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iMenu = new Intent(MainActivity.this,Menu.class);
                startActivity(iMenu);
                finish();
            }
        });

    }
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = fbAuth.getCurrentUser();
        if(currentUser==null) {
            Intent iNumAuth= new Intent(MainActivity.this,NumberVerification.class);
            startActivity(iNumAuth);
            finish();
        }
        else{
            txtTmpLogeado.setText("Bienvenido: Usted ya está logeado :D¡");
            btnEmpezar.setVisibility(View.VISIBLE);
        }
    }

}
