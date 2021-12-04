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

public class adminProductos extends AppCompatActivity {
    String resultadoId = "";
    Button btnInsertarProducto, btnActualizarProducto, btnEliminarProducto;
    EditText edtClave, edtNombre, edtDescripcion, edtPrecio, edtCantidad, edtActivo, edtFoto, edtStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_productos);
        btnInsertarProducto = (Button) findViewById(R.id.btnInsert);
        btnActualizarProducto = (Button) findViewById(R.id.btnUpdate);
        btnEliminarProducto = (Button) findViewById(R.id.btnDelete);
        edtClave = (EditText) findViewById(R.id.edtClave);
        edtNombre = (EditText) findViewById(R.id.edtNombrePro);
        edtDescripcion = (EditText) findViewById(R.id.edtDescripcion);
        edtPrecio = (EditText) findViewById(R.id.edtPrecio);
        edtCantidad = (EditText) findViewById(R.id.edtCantidad);
        edtActivo = (EditText) findViewById(R.id.edtActivo);
        edtFoto = (EditText) findViewById(R.id.edtProdFoto);
        edtStatus = (EditText) findViewById(R.id.edtStatus);
        btnInsertarProducto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Entramos al hilo", Toast.LENGTH_SHORT).show();
                Thread tr = new Thread() {

                    @Override
                    public void run() {

                        final String res = acceso();

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                String valor = objJSON(res);
                                if (!valor.equals("0")) {
                                    Toast.makeText(getApplicationContext(), "Producto Insertado!", Toast.LENGTH_SHORT).show();
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
        btnActualizarProducto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Entramos al hilo", Toast.LENGTH_SHORT).show();
                Thread tr = new Thread() {

                    @Override
                    public void run() {

                        final String res = modificar();

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                String valor = objJSON(res);
                                if (!valor.equals("0")) {
                                    Toast.makeText(getApplicationContext(), "Producto Modificado!", Toast.LENGTH_SHORT).show();
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
        btnEliminarProducto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Entramos al hilo", Toast.LENGTH_SHORT).show();
                Thread tr = new Thread() {

                    @Override
                    public void run() {

                        final String res = eliminar();

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                String valor = objJSON(res);
                                if (!valor.equals("0")) {
                                    Toast.makeText(getApplicationContext(), "Producto Eliminado!", Toast.LENGTH_SHORT).show();
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
    public String acceso(){
        Toast.makeText(getApplicationContext(), "Insercion en preceso: ", Toast.LENGTH_SHORT).show();

        String parametros="nom=RefrescoLimon&desc=limon&precio=25&cantidad=2&foto=imagenes/refresco.png";
        if(edtNombre.getText().equals("")||edtDescripcion.getText().equals("")||edtPrecio.getText().equals("")||edtCantidad.getText().equals("")||edtFoto.getText().equals("")){
            Toast.makeText(getApplicationContext(), "Error: No deje ningún campo vacio", Toast.LENGTH_SHORT).show();
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
    public String modificar(){
        Toast.makeText(getApplicationContext(), "Modificación en preceso: ", Toast.LENGTH_SHORT).show();

        String parametros="id=7&nom=Refresco3&desc=limon&precio=25&cantidad=2&foto=imagenes/refresco.png";
        if(edtClave.getText().equals("")||edtNombre.getText().equals("")||edtDescripcion.getText().equals("")||edtPrecio.getText().equals("")||edtCantidad.getText().equals("")||edtFoto.getText().equals("")){
            Toast.makeText(getApplicationContext(), "Error: No deje ningún campo vacio", Toast.LENGTH_SHORT).show();
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
    public String eliminar(){
        Toast.makeText(getApplicationContext(), "Eliminación en preceso: ", Toast.LENGTH_SHORT).show();

        String parametros="id=7";
        if(edtClave.getText().equals("")){
            Toast.makeText(getApplicationContext(), "Error: Se necesita la clave del producto para eliminar", Toast.LENGTH_SHORT).show();
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

}