package com.example.appmicalculadora;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

public class CheckActivity extends AppCompatActivity {
    CheckBox  chkLen1, chkLen2, chkLen3, chkLen4, chkLen5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check);
        chkLen1 = (CheckBox) findViewById(R.id.chkLenguaje1);
        chkLen2 = (CheckBox) findViewById(R.id.chkLenguaje2);
        chkLen3 = (CheckBox) findViewById(R.id.chkLenguaje3);
        chkLen4 = (CheckBox) findViewById(R.id.chkLenguaje4);
        chkLen5 = (CheckBox) findViewById(R.id.chkLenguaje5);
    }
    public void siguiente(View view){
        Intent sig = new Intent(this, ListSpinActivity.class);
        startActivity(sig);
    }
    public void anterior(View view){
        Intent ant = new Intent(this, RadioActivity.class);
        startActivity(ant);
    }
    public void checkGroup(View view){
        String mensaje = "Seleccionaste: ";
        if (chkLen1.isChecked())
            mensaje += chkLen1.getText() + ", ";
        if (chkLen2.isChecked())
            mensaje += chkLen2.getText() + ", ";
        if (chkLen3.isChecked())
            mensaje += chkLen3.getText() + ", ";
        if (chkLen4.isChecked())
            mensaje += chkLen4.getText() + ", ";
        if (chkLen5.isChecked())
            mensaje += chkLen5.getText() + ", ";
        if (mensaje.equals("Seleccionaste: ")){
            Toast.makeText(getApplicationContext(),  "No has seleccionado ningun lenguaje...", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(getApplicationContext(),  mensaje, Toast.LENGTH_SHORT).show();
        }
    }

}