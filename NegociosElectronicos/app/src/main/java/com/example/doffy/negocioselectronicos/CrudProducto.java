package com.example.doffy.negocioselectronicos;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Request.Method;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.doffy.negocioselectronicos.tienda.Producto;
import com.google.android.gms.common.api.Response;
import com.google.firebase.auth.FirebaseAuth;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class CrudProducto extends AppCompatActivity{

    /*DECLARACION DE VARIABLES*/
    //Interfaz
    private EditText txtIdProd;
    private EditText txtNomPro;
    private EditText txtDesPro;
    private EditText txtPrecio;
    private EditText txtStock;
    private EditText txtCategoria;
    private ImageButton btnAddProducto;
    private ImageButton btnBuscar;
    //Progreso
    private ProgressDialog progressDialog;
    //Web Service
    private RequestQueue requestQueue;
    private JsonObjectRequest jsonObjectRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crud_producto);

        /*INICIALIZACION DE VARIABLES*/
        //Interfaz
        txtIdProd = (EditText) findViewById(R.id.txtIdProd);
        txtNomPro = (EditText) findViewById(R.id.txtNomPro);
        txtDesPro = (EditText) findViewById(R.id.txtDesPro);
        txtPrecio = (EditText) findViewById(R.id.txtPrecio);
        txtStock = (EditText) findViewById(R.id.txtStock);
        txtCategoria = (EditText) findViewById(R.id.txtCategoria);
        btnAddProducto = (ImageButton) findViewById(R.id.btnAddProducto);
        btnBuscar = (ImageButton) findViewById(R.id.btnBuscar);
        //Progreso
        //Web Service
        requestQueue= Volley.newRequestQueue(CrudProducto.this);


        /**/


        /*EVENTOS*/
        //click
        btnAddProducto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cargarWebService();
            }
        });
        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wsBuscarProducto();
            }
        });


    }

    private void cargarWebService() {
        progressDialog = new ProgressDialog(CrudProducto.this);
        progressDialog.setMessage("Registrando...");
        progressDialog.show();
        String url = "http://mydolucaly.000webhostapp.com/wsJsonA01MPROD.php?" +
                "cIdProdu="+txtIdProd.getText().toString()+
                "&cNombre="+txtNomPro.getText().toString()+
                "&cDesProdu="+txtDesPro.getText().toString()+
                "&nPrecio="+txtPrecio.getText().toString()+
                "&mImagen=img.png&nStock="+txtStock.getText().toString()+
                "&cIdCateg="+txtCategoria.getText().toString()+
                "&cIdUser=123456789123";
        url=url.replace(" ","%20");
        //jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,url,null,this,this);
        StringRequest stringRequest = new StringRequest(Method.GET, url, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(CrudProducto.this,"Registrado",Toast.LENGTH_SHORT).show();
                progressDialog.hide();
                txtIdProd.setText("");
                txtNomPro.setText("");
                txtDesPro.setText("");
                txtPrecio.setText("");
                txtStock.setText("");
                txtCategoria.setText("");
            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(CrudProducto.this,"NO se pudo registrar ERROR: "+error.getMessage().toString(),Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(stringRequest);
        //url=url.replace(" ","%20");
    }

    private void wsBuscarProducto(){
        progressDialog = new ProgressDialog(CrudProducto.this);
        progressDialog.setMessage("Buscando...");
        progressDialog.show();

        String url ="http://mydolucaly.000webhostapp.com/buscarWsJsonA01MPROD.php?" +
                "cIdProdu="+txtIdProd.getText().toString();
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Method.GET,url, new com.android.volley.Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

            }
        });
        requestQueue.add(jsonObjectRequest);
    }

}
