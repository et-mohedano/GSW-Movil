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
            case R.id.Registrar:
                Intent reg = new Intent(this, adminProductos.class);
                startActivity(reg);
                return true;
            case R.id.Consultar:
                Intent con = new Intent(this, rptProductos.class);
                startActivity(con);
                return true;
            case R.id.Seleccionar:
                Intent sel = new Intent(this, CheckActivity.class);
                startActivity(sel);
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