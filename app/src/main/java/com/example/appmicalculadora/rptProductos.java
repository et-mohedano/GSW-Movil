package com.example.appmicalculadora;

import androidx.appcompat.app.AppCompatActivity;

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

import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

public class rptProductos extends AppCompatActivity {
    private ListView lstProductos=null;
    private ArrayList<String> claves;
    ArrayList<String> lista=new ArrayList<>();
    ArrayAdapter adaptador;
    Button btnMostrar, btnMostrar2da;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rpt_productos);
        lstProductos = (ListView) findViewById(R.id.lstProductos);
        btnMostrar = (Button) findViewById(R.id.btnPasProducto);
        btnMostrar2da = (Button) findViewById(R.id.btnRptProducto2);
        btnMostrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Thread tr = new Thread() {
                    @Override
                    public void run() {
                        final String res = consultarProductos();
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                String valor = objJSON(res);
                            }
                        });
                    }
                };
                tr.start();

            }
        });

        // -----------
        btnMostrar2da.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarProductos();
            }
        });
    }
    public String consultarProductos(){

        HttpURLConnection con=null;
        String resultado="";
        try {
            URL url = new URL("https://pw183110356.000webhostapp.com/progmovil/sitioweb/paginas/mostrarproductos.php");
            con=(HttpURLConnection)url.openConnection();
            con.setRequestMethod("POST");
            con.setDoOutput(true);
            DataOutputStream escribir=new DataOutputStream(con.getOutputStream());
            escribir.close();

            Scanner inCadena=new Scanner(con.getInputStream());

            while(inCadena.hasNextLine()){
                resultado+=(inCadena.nextLine());
            }
            Toast.makeText(getApplicationContext(), "Datos: " + resultado, Toast.LENGTH_SHORT).show();

        }
        catch (Exception ex) {
            Toast.makeText(getApplicationContext(), "Error: " + ex.toString(), Toast.LENGTH_SHORT).show();
        }
        return resultado.toString();
    }
    public String objJSON(String datos){
        String res = "0";
        try {
            JSONArray jsonDatos;
            jsonDatos = new JSONArray(datos);
            //SE OBTIENEN LOS REGISTROS DEL JSONARRAY Y SE DEJAN EN EL ARRAY LIST
            for (int i = 0; i < jsonDatos.length(); i++) {
                lista.add(jsonDatos.getJSONObject(i).getString("CLAVE") + ".- " + jsonDatos.getJSONObject(i).getString("PRODUCTO") + ", $" + jsonDatos.getJSONObject(i).getString("COSTO"));
                res = "1";
            }
            //SE PASAN LOS DATOS DEL ARRAY LIST AL ADAPTER  CONFIGURANDO LA LISTA
            adaptador = new ArrayAdapter(this, android.R.layout.simple_list_item_1, lista);
            //SE ASIGNAN LOS VALORES A LISTVIEW
            lstProductos.setAdapter(adaptador);
        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(this.getApplicationContext(), "Error" + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        return res;
    }
    // --------------
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


}