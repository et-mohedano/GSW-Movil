package com.example.appmicalculadora;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ListSpinActivity extends AppCompatActivity {
    Spinner spnPeliculas;
    TextView txtMensaje;
    ListView lstProductos;
    ArrayAdapter<String> spinnAdapt, lstAdapt;
    ArrayList<String> Lista = new ArrayList<>();
    String[] lstProductosTI = {"SSD 512GB", "HD 1TB", "RAM 8GB", "CPU I7", "Teclador matricial", "Manos libres"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_spin);
        spnPeliculas = (Spinner) findViewById(R.id.spnPeliculas);
        txtMensaje = (TextView) findViewById(R.id.txtMensaje);
        lstProductos = (ListView) findViewById(R.id.lstProductos);
        try {
            // LISTVIEW
            for(int i=0; i<lstProductosTI.length; i++){
                Lista.add(lstProductosTI[i]);
            }
            lstAdapt = new ArrayAdapter(this, android.R.layout.simple_list_item_1, Lista);
            lstProductos.setAdapter(lstAdapt);
            lstProductos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Toast.makeText(getApplicationContext(), "Se seleccionó " + adapterView.getItemAtPosition(i), Toast.LENGTH_SHORT).show();
                }
            });
            // SPINNER
            String[] opciones =
                    {"Toy Story", "Viernes 13/20", "Transformers", "Avatar", "Titanic", "Más allá de los sueños"};
            spinnAdapt =  new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, opciones);
            spnPeliculas.setAdapter(spinnAdapt);
            spnPeliculas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    Toast.makeText(getApplicationContext(), "Se seleccionó " + adapterView.getItemAtPosition(i), Toast.LENGTH_SHORT).show();
                    txtMensaje.setText(txtMensaje.getText() + ", " + adapterView.getItemAtPosition(i));
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });
        }catch (Exception ex){
            Toast.makeText(getApplicationContext(), "Error: " + ex.getMessage().toString(), Toast.LENGTH_SHORT).show();
        }
    }
    public void siguiente(View view){
        Intent sig = new Intent(this, MainActivity.class);
        startActivity(sig);
    }
    public void anterior(View view){
        Intent ant = new Intent(this, CheckActivity.class);
        startActivity(ant);
    }
}