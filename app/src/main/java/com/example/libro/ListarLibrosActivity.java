package com.example.libro;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.libro.adapter.LibroAdapter;
import com.example.libro.database.AppDatabase;
import com.example.libro.entities.Libro;
import com.example.libro.service.LibroService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ListarLibrosActivity extends AppCompatActivity {

    RecyclerView rvListarLibros;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_libros);

        rvListarLibros = findViewById(R.id.rvListarLibros);

        AppDatabase db = AppDatabase.getInstance(getApplicationContext());


        List<Libro> libros2 = db.libroDao().getAll();

        rvListarLibros.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        rvListarLibros.setAdapter(new LibroAdapter(libros2));
    }
}