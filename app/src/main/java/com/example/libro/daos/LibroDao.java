package com.example.libro.daos;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.libro.entities.Libro;

import java.util.List;

@Dao
public interface LibroDao {

    @Query("SELECT * FROM libros")
    List<Libro> getAll();

    @Query("SELECT * FROM libros WHERE id = :id")
    Libro find(int id);

    @Update
    void update(Libro libro);

    @Insert
    void create(Libro libro);
}
