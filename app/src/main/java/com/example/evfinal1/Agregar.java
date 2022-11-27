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
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Agregar extends AppCompatActivity {
    Button btn_add, btn_volver;
    EditText nombreProducto, descripcion;
    private FirebaseFirestore mFirestore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar);
        mFirestore = FirebaseFirestore.getInstance();

        nombreProducto=(EditText) findViewById(R.id.edNombre);
        descripcion=(EditText) findViewById(R.id.edDescripcion);
        btn_add= findViewById(R.id.bAgregar);

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombre = nombreProducto.getText().toString().trim();
                String descripcionProducto = descripcion.getText().toString().trim();



                if (nombre.isEmpty() && descripcionProducto.isEmpty() ){
                    Toast.makeText(getApplicationContext(), "Ingresar los datos", Toast.LENGTH_SHORT).show();
                }else{
                    postProducto(nombre, descripcionProducto);
                }
            }
        });
    }

    private void postProducto(String nombre, String descripcionProducto) {
        Map<String, Object> map = new HashMap<>();
        map.put("nombreProducto", nombre);
        map.put("descripcion", descripcionProducto);




        mFirestore.collection("Producto").add(map).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                Toast.makeText(getApplicationContext(), "Creado exitosamente", Toast.LENGTH_SHORT).show();
                finish();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(), "Error al ingresar producto", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void volverAtras (View view){
        Intent volverAtras= new Intent(this, MainActivity2.class);
        startActivity(volverAtras);
    }


}