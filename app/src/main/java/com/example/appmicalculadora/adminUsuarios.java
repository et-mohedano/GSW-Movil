package com.example.appmicalculadora;

import androidx.appcompat.app.AppCompatActivity;

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

public class adminUsuarios extends AppCompatActivity {
    String resultadoId = "";
    Button btnInsertar, btnModificar, btnEliminar;
    EditText edtClave, edtNombre, edtApellidoPat, edtApellidoMat, edtTelefono, edtPerfil, edtContrasena, edtRol;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_usuarios);
        btnInsertar = (Button) findViewById(R.id.btnInsertarUsu);
        btnModificar = (Button) findViewById(R.id.btnModificarUsu);
        btnEliminar = (Button) findViewById(R.id.btnEliminarUsu);
        edtClave = (EditText) findViewById(R.id.edtIdUsu);
        edtNombre = (EditText) findViewById(R.id.edtNomUsu);
        edtApellidoPat = (EditText) findViewById(R.id.edtPatUsu);
        edtApellidoMat = (EditText) findViewById(R.id.edtMatUsu);
        edtTelefono = (EditText) findViewById(R.id.edtTelUsu);
        edtPerfil = (EditText) findViewById(R.id.edtPerfilUsu);
        edtContrasena = (EditText) findViewById(R.id.edtPasswordUsu);
        edtRol = (EditText) findViewById(R.id.edtRolUsu);
        btnInsertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Entramos al hilo", Toast.LENGTH_SHORT).show();
                Thread tr = new Thread() {

                    @Override
                    public void run() {

                        final String res = insertarUsuario();

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                String valor = objJSON(res);
                                if (!valor.equals("0")) {
                                    Toast.makeText(getApplicationContext(), "Usuario Insertado!", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(getApplicationContext(), "Error en inserción, verificar ...", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }

                };
                tr.start();
            }
        });
        btnModificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Entramos al hilo", Toast.LENGTH_SHORT).show();
                Thread tr = new Thread() {

                    @Override
                    public void run() {

                        final String res = modificarUsuario();

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                String valor = objJSON(res);
                                if (!valor.equals("0")) {
                                    Toast.makeText(getApplicationContext(), "Usuario Modificado!", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(getApplicationContext(), "Error en modificación, verificar ...", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }

                };
                tr.start();
            }
        });
        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Entramos al hilo", Toast.LENGTH_SHORT).show();
                Thread tr = new Thread() {

                    @Override
                    public void run() {

                        final String res = eliminarUsuario();

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                String valor = objJSON(res);
                                if (!valor.equals("0")) {
                                    Toast.makeText(getApplicationContext(), "Usuario Eliminado!", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(getApplicationContext(), "Error en eliminación, verificar ...", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }

                };
                tr.start();
            }
        });
    }
    public String objJSON(String datos){
        try
        {
            JSONArray JsonDatos = new JSONArray(datos);
            resultadoId = JsonDatos.getJSONObject(0).getString("ID");
        }
        catch(JSONException ex){
            ex.printStackTrace();
            Toast.makeText(this.getApplicationContext(), "Error:" + ex.getMessage(), Toast.LENGTH_LONG).show();
        }

        return resultadoId;
    }
    public String insertarUsuario(){
        Toast.makeText(getApplicationContext(), "Inserción en preceso: ", Toast.LENGTH_SHORT).show();

        String parametros="nom=RefrescoLimon&desc=limon&precio=25&cantidad=2&foto=imagenes/refresco.png";
        if(edtNombre.getText().equals("")||edtApellidoPat.getText().equals("")||edtApellidoMat.getText().equals("")||edtTelefono.getText().equals("")||edtRol.getText().equals("")||edtContrasena.getText().equals("")){
            Toast.makeText(getApplicationContext(), "Error: No debe haber campos vacios para insertar", Toast.LENGTH_SHORT).show();
        }
        //String parametros="nom=" + edtNombre.getText() + "&desc=" + edtDescripcion.getText() + "&precio=" + edtPrecio.getText() + "&cantidad=" + edtCantidad.getText() + "&foto=" + edtFoto.getText();
        HttpURLConnection con=null;
        String resultado="";
        try {
            URL url = new URL("https://pw183110356.000webhostapp.com/progmovil/sitioweb/paginas/insertarproducto.php");
            con=(HttpURLConnection)url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Length",""+Integer.toString(parametros.getBytes().length));

            con.setDoOutput(true);
            DataOutputStream escribir=new DataOutputStream(con.getOutputStream());
            escribir.writeBytes(parametros);
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
    public String modificarUsuario(){
        Toast.makeText(getApplicationContext(), "Modificación en preceso: ", Toast.LENGTH_SHORT).show();

        String parametros="id=7&nom=Refresco3&desc=limon&precio=25&cantidad=2&foto=imagenes/refresco.png";
        if(edtClave.getText().equals("")||edtNombre.getText().equals("")||edtApellidoPat.getText().equals("")||edtApellidoMat.getText().equals("")||edtTelefono.getText().equals("")||edtRol.getText().equals("")||edtContrasena.getText().equals("")){
            Toast.makeText(getApplicationContext(), "Error: No debe haber campos vacios para modificar", Toast.LENGTH_SHORT).show();
        }
        //String parametros="id=" + edtClave.getText() + "nom=" + edtNombre.getText() + "&desc=" + edtDescripcion.getText() + "&precio=" + edtPrecio.getText() + "&cantidad=" + edtCantidad.getText() + "&foto=" + edtFoto.getText();
        HttpURLConnection con=null;
        String resultado="";
        try {
            URL url = new URL("https://pw183110356.000webhostapp.com/progmovil/sitioweb/paginas/modificarproducto.php");
            con=(HttpURLConnection)url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Length",""+Integer.toString(parametros.getBytes().length));

            con.setDoOutput(true);
            DataOutputStream escribir=new DataOutputStream(con.getOutputStream());
            escribir.writeBytes(parametros);
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
    public String eliminarUsuario(){
        Toast.makeText(getApplicationContext(), "Eliminación en preceso: ", Toast.LENGTH_SHORT).show();

        String parametros="id=7";
        if(edtClave.getText().equals("")){
            Toast.makeText(getApplicationContext(), "Error: Se necesita la clave del usuario para eliminar", Toast.LENGTH_SHORT).show();
        }
        //String parametros="id=" + edtClave.getText();
        HttpURLConnection con=null;
        String resultado="";
        try {
            URL url = new URL("https://pw183110356.000webhostapp.com/progmovil/sitioweb/paginas/eliminarproducto.php");
            con=(HttpURLConnection)url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Length",""+Integer.toString(parametros.getBytes().length));

            con.setDoOutput(true);
            DataOutputStream escribir=new DataOutputStream(con.getOutputStream());
            escribir.writeBytes(parametros);
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
}