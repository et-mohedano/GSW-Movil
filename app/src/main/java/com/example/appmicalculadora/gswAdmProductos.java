package com.example.appmicalculadora;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class gswAdmProductos extends AppCompatActivity {

    String resultadoId = "";
    Button btnInsertarProducto, btnActualizarProducto, btnEliminarProducto;
    EditText edtProducto, edt, edtDescripcion, edtPrecio, edtCantidad, edtActivo, edtFoto, edtStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gsw_adm_productos);
    }


}