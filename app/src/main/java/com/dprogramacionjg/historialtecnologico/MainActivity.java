package com.dprogramacionjg.historialtecnologico;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.icon_add) {
            Toast.makeText(this, "Agregar", Toast.LENGTH_LONG).show();
            return true;
        }
        if (id == R.id.icon_save) {
            Toast.makeText(this, "Guardar", Toast.LENGTH_LONG).show();
            return true;
        }
        if (id == R.id.icon_delete) {
            Toast.makeText(this, "Eliminar", Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(this, "Sin opccion", Toast.LENGTH_LONG).show();
        }
        return super.onOptionsItemSelected(item);
    }
}