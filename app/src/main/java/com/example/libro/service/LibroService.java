package com.example.libro.service;


import com.example.libro.entities.Libro;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;


public interface LibroService {

    //Listar
    @GET("libro")
    Call<List<Libro>> listLibros();

    //Guardar
    @POST("libro")
    Call<Void> create(@Body Libro libro);
}
