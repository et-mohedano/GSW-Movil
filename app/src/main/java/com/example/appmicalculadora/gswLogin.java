package com.example.appmicalculadora;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class gswLogin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gsw_login);

        getSupportActionBar().hide();
    }
    public void pas(View view) {
        Intent acc = new Intent(this, PrincipalActivity.class);
        startActivity(acc);
    }
}