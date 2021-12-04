package com.example.appmicalculadora;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public class gswRptProductos extends AppCompatActivity {

    private ListView lstProductos=null;
    private ArrayList<String> claves;
    ArrayList<String> lista=new ArrayList<>();
    ArrayAdapter adaptador;
    Button btnConsultar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gsw_rpt_productos);
        lstProductos = (ListView) findViewById(R.id.lstRptProductos);
        btnConsultar = (Button) findViewById(R.id.btnRptProducto);

        btnConsultar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mostrarProductos();
            }
        });
    }

    public void mostrarProductos()
    {
        AsyncHttpClient cliente=new AsyncHttpClient();
        String url="https://pw183110356.000webhostapp.com/progmovil/sitioweb/paginas/mostrarproductos.php";

        cliente.post(url, null, new AsyncHttpResponseHandler() {


            @Override
            public void onSuccess(int statusCode, org.apache.http.Header[] headers, byte[] responseBody) {
                obDatosJSON(new String(responseBody));
            }

            @Override
            public void onFailure(int statusCode, org.apache.http.Header[] headers, byte[] responseBody, Throwable error) {
                //error.printStackTrace(System.out);
            }

        });
    }
    public String obDatosJSON(String response) {
        String datos = "0";
        try {
            JSONArray jsonDatos;
            jsonDatos = new JSONArray(response);
            for (int i = 0; i < jsonDatos.length(); i++) {
                lista.add(jsonDatos.getJSONObject(i).getString("CLAVE") + "-" + jsonDatos.getJSONObject(i).getString("PRODUCTO") + "-" + jsonDatos.getJSONObject(i).getString("COSTO"));
                datos = "1";
            }
            adaptador = new ArrayAdapter(this, android.R.layout.simple_list_item_1, lista);
            lstProductos.setAdapter(adaptador);
        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(this.getApplicationContext(), "Error" + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        return datos;
    }

    public void pas(View view) {
        Intent acc = new Intent(this, gswAdmProductos.class);
        startActivity(acc);
    }
}