package com.example.libro.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "libros")
public class Libro {
    @PrimaryKey(autoGenerate = true)
    public int id;
    @ColumnInfo(name = "titulo")
    public String titulo;
    @ColumnInfo(name = "autor")
    public String autor;

}
