package com.example.capitaleseuropajava;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface CapitalDao {
    @Query("select * from CapitalsofEurope")
    LiveData<List<Capital>> getCapital();
    //LiveData para observar los cambios en los datos de la bd

    @Insert
    void addCapital(Capital capital);

    @Insert
    void addCapitals(List<Capital> capitals);

    @Delete
    void deleteCapital(Capital capital);

    @Query("DELETE FROM CapitalsofEurope")
    void deleteCapitales();
    //Esto es util para limpiar la tabla
}
