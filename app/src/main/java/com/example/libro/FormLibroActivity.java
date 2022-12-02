package com.example.libro;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
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

public class FormLibroActivity extends AppCompatActivity {

    Libro libro = new Libro();
    private EditText etTitulo, etAutor;
    private Button btnGuardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_libro);

        etTitulo = findViewById(R.id.etTitulo);
        etAutor = findViewById(R.id.etAutor);
        btnGuardar = findViewById(R.id.btnGuardar);


        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                libro.titulo = etTitulo.getText().toString();
                libro.autor = etAutor.getText().toString();


                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("https://6359bef538725a1746b71cf0.mockapi.io/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                LibroService service = retrofit.create(LibroService.class);
                service.create(libro).enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {

                        if(response.isSuccessful()){

                            Toast.makeText(getApplicationContext(), "Se guardaron los datos correctamente", Toast.LENGTH_SHORT).show();

                        }
                        else{

                            Toast.makeText(getApplicationContext(), "Error...", Toast.LENGTH_SHORT).show();

                        }


                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {

                    }
                });
            }
        });


    }
}