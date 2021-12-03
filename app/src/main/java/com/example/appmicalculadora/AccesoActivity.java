package com.example.appmicalculadora;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class AccesoActivity extends AppCompatActivity {

    String resultadoId = "", resultadoNom = "";
    EditText eNombre;
    EditText eContra;
    Button btnAceptar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acceso);
        eNombre = (EditText) findViewById(R.id.edtUsuario);
        eContra = (EditText) findViewById(R.id.edtContrasena);
        btnAceptar = (Button) findViewById(R.id.btnAccesoUsuario);

        try {
            btnAceptar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(getApplicationContext(), "Entramos al hilo " + resultadoNom, Toast.LENGTH_SHORT).show();
                    Thread tr = new Thread() {

                        @Override
                        public void run() {

                            final String res = acceso();

                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    String valor=objJSON(res);
                                    if (!valor.equals("0")){
                                        Toast.makeText(getApplicationContext(), "Bienvenido " + resultadoNom, Toast.LENGTH_SHORT).show();
                                        Intent reg = new Intent(getApplicationContext(), MainActivity.class);
                                        startActivity(reg);
                                    }
                                    else
                                    {
                                        Toast.makeText(getApplicationContext(), "Acceso denegado " + resultadoNom, Toast.LENGTH_SHORT).show(); }
                                }
                            });
                        }

                    };
                    tr.start();
                }
            });
        }catch (Exception ex){
            Toast.makeText(getApplicationContext(), "Error: " + ex.toString(), Toast.LENGTH_SHORT).show();
        }
    }
    public String acceso() {
        Toast.makeText(getApplicationContext(), "Entre el acceso:", Toast.LENGTH_SHORT).show();

        String parametros = "usu=" + eNombre.getText() + "&pwd=" + eContra.getText();
        HttpURLConnection con = null;
        String resultado = "";

        try{
            // Revisar su url del servicio ...
            URL url = new URL("https://pw183110356.000webhostapp.com/progmovil/sitioweb/paginas/verificaracceso.php");
            con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Length",""+Integer.toString(parametros.getBytes().length));
            con.setDoOutput(true);
            DataOutputStream escribir = new DataOutputStream(con.getOutputStream());
            escribir.writeBytes(parametros);
            escribir.close();

            Scanner inCadena =  new Scanner(con.getInputStream());

            while(inCadena.hasNextLine()){
                resultado += (inCadena.nextLine());
            }
            Toast.makeText(getApplicationContext(), "Datos:" + resultado, Toast.LENGTH_SHORT).show();
        }
        catch (Exception ex) {
            Toast.makeText(getApplicationContext(), "Error:" + ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
        return resultado.toString();


    }
    public String objJSON(String datos){
        try
        {
            JSONArray JsonDatos = new JSONArray(datos);
            resultadoId = JsonDatos.getJSONObject(0).getString("ID");
            if(!resultadoId.equals("0")){
                resultadoNom = JsonDatos.getJSONObject(1).getString("NOMBRE");
            }
        }
        catch(JSONException ex){
            ex.printStackTrace();
            Toast.makeText(this.getApplicationContext(), "Error:" + ex.getMessage(), Toast.LENGTH_LONG).show();
        }
        return resultadoId;
    }
    public void regresar(View view) {
        Intent acc = new Intent(this, MainActivity.class);
        startActivity(acc);
    }
}