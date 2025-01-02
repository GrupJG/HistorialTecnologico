package com.dprogramacionjg.historialtecnologico;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.dprogramacionjg.historialtecnologico.model.Persona;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    private List<Persona> listPerson = new ArrayList<Persona>();
    ArrayAdapter<Persona> arrayAdapterPersona;

    EditText eTNombre, eTApellidos, eTNumeroTelefonico, eTCorreo;
    ListView lVlista_contactos;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    Persona personaSelected;



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
        inicializarFirebase();
        listarDatos();

        lVlista_contactos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                personaSelected = (Persona) parent.getItemAtPosition(position);
                eTNombre.setText(personaSelected.getNombre());
                eTApellidos.setText(personaSelected.getApellidos());
                eTNumeroTelefonico.setText(personaSelected.getNumeroTelefonico());
                eTCorreo.setText(personaSelected.getCorreo());
            }
        });


    }

    private void listarDatos() {
        databaseReference.child("Persona").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listPerson.clear();
                for (DataSnapshot objSnaptshot : dataSnapshot.getChildren()){
                    Persona p = objSnaptshot.getValue(Persona.class);
                    listPerson.add(p);
                    arrayAdapterPersona = new ArrayAdapter<Persona>(MainActivity.this, android.R.layout.simple_list_item_1, listPerson);
                    lVlista_contactos.setAdapter(arrayAdapterPersona);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }

    private void inicializarFirebase() {
        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        //firebaseDatabase.setPersistenceEnabled(true);   // para que funcione sin internet
        databaseReference = firebaseDatabase.getReference();
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
                Persona p = new Persona();
                p.setUid(UUID.randomUUID().toString());
                p.setNombre(eTNombre.getText().toString());
                p.setApellidos(eTApellidos.getText().toString());
                p.setNumeroTelefonico(eTNumeroTelefonico.getText().toString());
                p.setCorreo(eTCorreo.getText().toString());
                databaseReference.child("Persona").child(p.getUid()).setValue(p);
                Toast.makeText(this, "Agregar", Toast.LENGTH_LONG).show();
                lipiarCampos();
                return true;
            }
            if (id == R.id.icon_save) {
                Persona p = new Persona();
                p.setUid(personaSelected.getUid());
                p.setNombre(eTNombre.getText().toString().trim());//para ignorar los espacios en blanco se usa el trim
                p.setApellidos(eTApellidos.getText().toString().trim());
                p.setNumeroTelefonico(eTNumeroTelefonico.getText().toString().trim());
                p.setCorreo(eTCorreo.getText().toString().trim());
                databaseReference.child("Persona").child(p.getUid()).setValue(p);
                Toast.makeText(this, "Actualizar", Toast.LENGTH_LONG).show();
                lipiarCampos();
                return true;
            }
            if (id == R.id.icon_delete) {
                Persona p = new Persona();
                p.setUid(personaSelected.getUid());
                databaseReference.child("Persona").child(p.getUid()).removeValue();
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