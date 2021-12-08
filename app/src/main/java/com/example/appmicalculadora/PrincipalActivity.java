package com.example.appmicalculadora;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class PrincipalActivity extends AppCompatActivity {
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        // Verificar opción seleccionada
        switch (item.getItemId()){
            case R.id.Productos:
                Intent reg = new Intent(this, gswRptProductos.class);
                startActivity(reg);
                return true;
            case R.id.Usuarios:
                Intent con = new Intent(this, adminUsuarios.class);
                startActivity(con);
                return true;
            case R.id.AcercaDe:
                Intent sel = new Intent(this, acercaDe.class);
                startActivity(sel);
                return true;
            case R.id.Salir:
                Intent sal = new Intent(this, gswLogin.class);
                startActivity(sal);
                return true;
                default:
                    return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Enlace al xml para inflar el menú
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
    }
}