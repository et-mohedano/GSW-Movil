package com.example.appmicalculadora;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

public class RadioActivity extends AppCompatActivity {
    RadioButton rbtMateria1, rbtMateria2, rbtMateria3, rbtMateria4, rbtMateria5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_radio);
        rbtMateria1 = (RadioButton) findViewById(R.id.rbtMateria1);
        rbtMateria2 = (RadioButton) findViewById(R.id.rbtMateria2);
        rbtMateria3 = (RadioButton) findViewById(R.id.rbtMateria3);
        rbtMateria4 = (RadioButton) findViewById(R.id.rbtMateria4);
        rbtMateria5 = (RadioButton) findViewById(R.id.rbtMateria5);
    }
    public void siguiente(View view){
        Intent sig = new Intent(this, CheckActivity.class);
        startActivity(sig);
    }
    public void anterior(View view){
        Intent ant = new Intent(this, MainActivity.class);
        startActivity(ant);
    }
    // Metodo para validar radioButton seleccionado
    public void radioCheck(View view){
        if(rbtMateria1.isChecked())
            Toast.makeText(getApplicationContext(),"Has seleccionado " + rbtMateria1.getText(), Toast.LENGTH_SHORT).show();
        if(rbtMateria2.isChecked())
            Toast.makeText(getApplicationContext(),"Has seleccionado " + rbtMateria2.getText(), Toast.LENGTH_SHORT).show();
        if(rbtMateria3.isChecked())
            Toast.makeText(getApplicationContext(),"Has seleccionado " + rbtMateria3.getText(), Toast.LENGTH_SHORT).show();
        if(rbtMateria4.isChecked())
            Toast.makeText(getApplicationContext(),"Has seleccionado " + rbtMateria4.getText(), Toast.LENGTH_SHORT).show();
        if(rbtMateria5.isChecked())
            Toast.makeText(getApplicationContext(),"Has seleccionado " + rbtMateria5.getText(), Toast.LENGTH_SHORT).show();
    }
}