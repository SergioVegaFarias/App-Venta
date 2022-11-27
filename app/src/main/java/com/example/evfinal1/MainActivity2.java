package com.example.evfinal1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.evfinal1.adapter.ProductoAdapter;
import com.example.evfinal1.model.Producto;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity {

    Button salir;
    Button ubicacion;
    FirebaseAuth mAuth;
    RecyclerView mRecycler;
    ProductoAdapter mAdapter;
    FirebaseFirestore mFirestore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        mAuth= FirebaseAuth.getInstance();
        salir=  findViewById(R.id.button4);


        mFirestore = FirebaseFirestore.getInstance();
        mRecycler = findViewById(R.id.rview);
        mRecycler.setLayoutManager(new LinearLayoutManager(this));
        Query query = mFirestore.collection("Producto");

        FirestoreRecyclerOptions<Producto> firestoreRecyclerOptions = new FirestoreRecyclerOptions.Builder<Producto>().setQuery(query,Producto.class).build();

        mAdapter = new ProductoAdapter(firestoreRecyclerOptions, this);
        mAdapter.notifyDataSetChanged();
        mRecycler.setAdapter(mAdapter);


        salir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                finish();
                startActivity(new Intent(MainActivity2.this, MainActivity.class));

            }
        });
        ubicacion= (Button) findViewById(R.id.button7);
        ubicacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity2.this, MapsActivity.class));
            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();
        mAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mAdapter.stopListening();
    }

    public void agregarP(View view) {
        Intent agregarProducto = new Intent(this, Agregar.class);
        startActivity(agregarProducto);
    }

}