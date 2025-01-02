package com.dprogramacionjg.historialtecnologico;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;


public class MainActivity extends AppCompatActivity {

    EditText eTNombre, eTApellidos, eTNumeroTelefonico, eTCorreo;
    ListView lVlista_contactos;


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

        eTNombre = findViewById(R.id.et_username);
        eTApellidos = findViewById(R.id.et_apellidos);
        eTNumeroTelefonico = findViewById(R.id.et_numerotelefonico);
        eTCorreo = findViewById(R.id.et_correo);
        lVlista_contactos = findViewById(R.id.lv_contactos);

    }



    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (eTNombre.getText().toString().isEmpty() || eTApellidos.getText().toString().isEmpty() || eTNumeroTelefonico.getText().toString().isEmpty() || eTCorreo.getText().toString().isEmpty()) {
            Toast.makeText(this, "Por favor llene todos los campos", Toast.LENGTH_LONG).show();
            validacion();
        }else {
            if (id == R.id.icon_add) {
                Toast.makeText(this, "Agregar", Toast.LENGTH_LONG).show();
                lipiarCampos();
                return true;
            }
            if (id == R.id.icon_save) {
                Toast.makeText(this, "Guardar", Toast.LENGTH_LONG).show();
                lipiarCampos();
                return true;
            }
            if (id == R.id.icon_delete) {
                Toast.makeText(this, "Eliminar", Toast.LENGTH_LONG).show();
                lipiarCampos();
                return true;
            }
            else if (id == R.id.icon_delete || id == R.id.icon_save || id == R.id.icon_add) {
                Toast.makeText(this, "Sin opccion", Toast.LENGTH_LONG).show();
            }
        }
        return super.onOptionsItemSelected(item);
    }

    private void lipiarCampos() {
        eTNombre.setText("");
        eTApellidos.setText("");
        eTNumeroTelefonico.setText("");
        eTCorreo.setText("");
    }

    private void validacion() {
        String nombre = eTNombre.getText().toString();
        String apellidos = eTApellidos.getText().toString();
        String numerotelefonico = eTNumeroTelefonico.getText().toString();
        String correo = eTCorreo.getText().toString();
        if (nombre.equals("")) {
            eTNombre.setError("Requerido");
        } else if (apellidos.equals("")) {
            eTApellidos.setError("Requerido");
        } else if (numerotelefonico.equals("")) {
            eTNumeroTelefonico.setError("Requerido");
        } else if (correo.equals("")) {
            eTCorreo.setError("Requerido");
        }
    }
}