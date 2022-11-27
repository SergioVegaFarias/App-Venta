package com.example.evfinal1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Editar extends AppCompatActivity {
    Button bActualizar;
    EditText EnombreProducto, Edescripcion;
    private String productoId;
    private FirebaseFirestore mFirestore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar);
        EnombreProducto=(EditText) findViewById(R.id.edNombre);
        Edescripcion=(EditText) findViewById(R.id.edDescripcion);

        bActualizar = findViewById(R.id.bAgregar);

        mFirestore = FirebaseFirestore.getInstance();
        productoId = getIntent().getStringExtra("productoId");

        bActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String nombre = EnombreProducto.getText().toString().trim();
                String descripcionProducto = Edescripcion.getText().toString().trim();

                if (nombre.isEmpty() && descripcionProducto.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Ingresar los datos", Toast.LENGTH_SHORT).show();
                }else{
                    postProducto(nombre, descripcionProducto);
                }

            }
        });
    }

    private void postProducto(String nombre, String descripcionProducto){
        Map<String, Object> map = new HashMap<>();
        map.put("nombreProducto", nombre);
        map.put("descripcion", descripcionProducto);

        mFirestore.collection("Producto").document(productoId).update(map).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(Editar.this, "Producto actualizado exitosamente", Toast.LENGTH_SHORT).show();
                finish();
                startActivity(new Intent(Editar.this, MainActivity2.class));
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(Editar.this, "Error al actualizar producto", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void volver(View view){
        Intent volver= new Intent(this, MainActivity2.class);
        startActivity(volver);
    }
}