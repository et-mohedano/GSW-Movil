package com.example.appmicalculadora;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
clsCalculadora obj;
EditText edtDato1, edtDato2;
Button btnSuma, btnResta, btnMultiplicacion, btnDivision, btnResultado, btnLimpiar, btnPotencia;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        obj = new clsCalculadora();
        btnSuma = (Button) findViewById(R.id.btnSuma);
        btnResta = (Button) findViewById(R.id.btnResta);
        btnMultiplicacion = (Button) findViewById(R.id.btnMultiplicacion);
        btnResultado = (Button) findViewById(R.id.btnCalculo);
        btnDivision = (Button) findViewById(R.id.btnDivision);
        btnLimpiar = (Button) findViewById(R.id.btnClean);
        btnPotencia = (Button) findViewById(R.id.btnPotencia);
        edtDato1 = (EditText) findViewById(R.id.edtDato1);
        edtDato2 = (EditText) findViewById(R.id.edtDato2);

        btnSuma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                obj.sumar();
                Toast.makeText(getApplicationContext(), "Se aplicará la suma", Toast.LENGTH_SHORT).show();
            }
        });

        btnResta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                obj.restar();
                Toast.makeText(getApplicationContext(), "Se aplicará la resta", Toast.LENGTH_SHORT).show();
            }
        });

        btnMultiplicacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                obj.multiplicar();
                Toast.makeText(getApplicationContext(), "Se aplicará la multiplicación", Toast.LENGTH_SHORT).show();
            }
        });

        btnDivision.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                obj.dividir();
                Toast.makeText(getApplicationContext(), "Se aplicará la división", Toast.LENGTH_SHORT).show();
            }
        });
        btnPotencia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                obj.potencia();
                Toast.makeText(getApplicationContext(), "Se aplicará la potencia", Toast.LENGTH_SHORT).show();
            }
        });

        btnResultado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    // Validación
                    if (edtDato1.getText().toString().equals("") || edtDato2.getText().toString().equals("")) {
                        Toast.makeText(getApplicationContext(), "Debes capturar números válidos", Toast.LENGTH_SHORT).show();
                    } else {
                        if (obj.getOperador().equals("")){
                            Toast.makeText(getApplicationContext(), "Debes elegir una operación primero", Toast.LENGTH_LONG).show();
                        }else{
                            obj.setOperando1(Double.parseDouble(edtDato1.getText().toString()));
                            obj.setOperando2(Double.parseDouble(edtDato2.getText().toString()));
                            obj.operaciones();
                            Toast.makeText(getApplicationContext(), "Resultado = " + obj.getResultado(), Toast.LENGTH_LONG).show();
                        }
                    }
                }catch(Exception ex){
                    Toast.makeText(getApplicationContext(), "Surgió un error en los datos, verificar... = " + obj.getResultado(), Toast.LENGTH_LONG).show();
                }

            }
        });

        btnLimpiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                obj.limpiar();
                edtDato1.setText("");
                edtDato2.setText("");
                Toast.makeText(getApplicationContext(), "Los valores se han reseteado", Toast.LENGTH_LONG).show();
            }
        });

    }
    public void siguiente(View view){
        Intent sig = new Intent(this, RadioActivity.class);
        startActivity(sig);
    }
    public void acceso(View view){
        Intent acc = new Intent(this, AccesoActivity.class);
        startActivity(acc);
    }
}