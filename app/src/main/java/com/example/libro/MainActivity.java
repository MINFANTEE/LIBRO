package com.example.libro;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.libro.adapter.LibroAdapter;
import com.example.libro.database.AppDatabase;
import com.example.libro.entities.Libro;
import com.example.libro.service.LibroService;
import com.google.gson.Gson;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    RecyclerView rvListarLibros;
    int i = 0;

    private Button btnCrearLibro, btnListarLibro, btnSincronizar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCrearLibro = findViewById(R.id.btnCrearLibro);


        btnCrearLibro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), FormLibroActivity.class);
                startActivity(intent);

            }
        });

        btnListarLibro = findViewById(R.id.btnListarLibros);

        btnListarLibro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), ListarLibrosActivity.class);
                startActivity(intent);

            }
        });

        btnSincronizar = findViewById(R.id.btnSincronizar);

        btnSincronizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                AppDatabase db = AppDatabase.getInstance(getApplicationContext());

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("https://6359bef538725a1746b71cf0.mockapi.io/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                LibroService service = retrofit.create(LibroService.class);

                service.listLibros().enqueue(new Callback<List<Libro>>() {
                    @Override
                    public void onResponse(Call<List<Libro>> call, Response<List<Libro>> response) {


                        List<Libro> libros = response.body();

                        for(i = 0; i < libros.size(); i++){
                            Libro libroAux = libros.get(i);

                           Libro libro2 = db.libroDao().find(libroAux.id);

                           if(libro2 != null){

                               db.libroDao().update(libroAux);

                           }
                           else{
                               db.libroDao().create(libroAux);
                           }


                        }

                        List<Libro> lb = db.libroDao().getAll();

                        Log.i("MAIN_APP", new Gson().toJson(lb));

                        Toast.makeText(getApplicationContext(), "Sincronizado correctamente", Toast.LENGTH_SHORT).show();






                    }

                    @Override
                    public void onFailure(Call<List<Libro>> call, Throwable t) {

                    }
                });


            }
        });

    }
}