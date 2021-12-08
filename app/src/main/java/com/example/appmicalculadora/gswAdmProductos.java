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

public class gswAdmProductos extends AppCompatActivity {

    String resultadoId = "";
    Button btnInsertarProducto, btnActualizarProducto, btnEliminarProducto;
    EditText edtProducto, edtPres, edtCant, edtFecha, edtPrecioAd, edtPrecioVen, edtProveedor, edtCat, edtUbi, edtFoto, edtClave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gsw_adm_productos);
        btnInsertarProducto = (Button) findViewById(R.id.btnInsProducto);
        btnActualizarProducto = (Button) findViewById(R.id.btnModProducto);
        btnEliminarProducto = (Button) findViewById(R.id.btnEliProducto);
        edtProducto = (EditText) findViewById(R.id.edtProdNombre);
        edtPres = (EditText) findViewById(R.id.edtProdPres);
        edtCant = (EditText) findViewById(R.id.edtProdCant);
        edtFecha = (EditText) findViewById(R.id.edtProdFecha);
        edtPrecioAd = (EditText) findViewById(R.id.edtProdPrecioAd);
        edtPrecioVen = (EditText) findViewById(R.id.edtProdPrecioVen);
        edtProveedor = (EditText) findViewById(R.id.edtProdProvedor);
        edtCat = (EditText) findViewById(R.id.edtProdCat);
        edtUbi = (EditText) findViewById(R.id.edtProdUbi);
        edtFoto = (EditText) findViewById(R.id.edtProdFoto);
        edtClave = (EditText) findViewById(R.id.edtProdClave);

        btnInsertarProducto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Entramos al hilo", Toast.LENGTH_SHORT).show();
                Thread tr = new Thread() {

                    @Override
                    public void run() {

                        final String res = insProducto();

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

                        final String res = modificarProducto();

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

                        final String res = eliminarProducto();

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

    public String insProducto(){
        Toast.makeText(getApplicationContext(), "Insercion en preceso: ", Toast.LENGTH_SHORT).show();

        String parametros;
        if(edtProducto.getText().equals("")||edtPres.getText().equals("")||edtCant.getText().equals("")||edtFecha.getText().equals("")||edtPrecioAd.getText().equals("")||edtPrecioVen.getText().equals("")||edtProveedor.getText().equals("")||edtCat.getText().equals("")||edtUbi.getText().equals("")||edtFoto.getText().equals("")){
            Toast.makeText(getApplicationContext(), "Error: No deje ningún campo vacio", Toast.LENGTH_SHORT).show();
            parametros = "";
        }else{
            parametros="prov=" + edtProveedor.getText() + "&pres=" + edtPres.getText() + "&nombre=" + edtProducto.getText() + "&cant=" + edtCant.getText() + "&caduc=" + edtFecha.getText() + "&usu=1&ven=" + edtPrecioVen.getText() + "&adqu=" + edtPrecioAd.getText() + "&foto=" + edtFoto.getText();
            Toast.makeText(getApplicationContext(), "Parametros: " + parametros, Toast.LENGTH_LONG).show();
//          https://pw183110356.000webhostapp.com/practicas/GSW/sitioweb/paginas/INSESTARPRODUCTOS.php?prov=3&pres=3&nombre=WapITas&cant=6&caduc=2022-05-20&usu=3&ven=15&adqu=10&foto=imagenes/wapas
        }
        HttpURLConnection con=null;
        String resultado="";
        try {
            URL url = new URL("https://pw183110356.000webhostapp.com/practicas/GSW/sitioweb/paginas/INSESTARPRODUCTOS.php");
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
    public String modificarProducto(){
        Toast.makeText(getApplicationContext(), "Modificación en preceso: ", Toast.LENGTH_SHORT).show();

//        String parametros="id=7&nom=Refresco3&desc=limon&precio=25&cantidad=2&foto=imagenes/refresco.png";
        if(edtClave.getText().equals("")||edtProducto.getText().equals("")||edtPres.getText().equals("")||edtCant.getText().equals("")||edtFecha.getText().equals("")||edtPrecioAd.getText().equals("")||edtPrecioVen.getText().equals("")||edtProveedor.getText().equals("")||edtCat.getText().equals("")||edtUbi.getText().equals("")||edtFoto.getText().equals("")){
            Toast.makeText(getApplicationContext(), "Error: No deje ningún campo vacio", Toast.LENGTH_SHORT).show();
        }
        String parametros="cve=" + edtClave.getText() + "&prov=" + edtProveedor.getText() + "&pres=" + edtPres.getText() + "&nombre=" + edtProducto.getText() + "&cant=" + edtCant.getText() + "&caduc=" + edtFecha.getText() + "&usu=1&ven=" + edtPrecioVen.getText() + "&adq=" + edtPrecioAd.getText() + "&foto=" + edtFoto.getText();
        HttpURLConnection con=null;
        String resultado="";
        try {
//          https://pw183110356.000webhostapp.com/practicas/GSW/sitioweb/paginas/MODIFICARPRODUCTOS.php?cve=20&prov=3&pres=3&nombre=Wapas&cant=6&caduc=2022-05-20&usu=3&ven=15&adqu=10&foto=paginas/imagenes/productos/Wapas.jpg
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

    public String eliminarProducto(){
        Toast.makeText(getApplicationContext(), "Eliminación en preceso: ", Toast.LENGTH_SHORT).show();

//        String parametros="id=7";
        if(edtClave.getText().equals("")){
            Toast.makeText(getApplicationContext(), "Error: Se necesita la clave del producto para eliminar", Toast.LENGTH_SHORT).show();
        }
        String parametros="cve=" + edtClave.getText();
        HttpURLConnection con=null;
        String resultado="";
        try {
//          https://pw183110356.000webhostapp.com/practicas/GSW/sitioweb/paginas/ELIMINARPRODUCTOS.php?cve=1
            URL url = new URL("https://pw183110356.000webhostapp.com/practicas/GSW/sitioweb/paginas/ELIMINARPRODUCTOS.php");
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