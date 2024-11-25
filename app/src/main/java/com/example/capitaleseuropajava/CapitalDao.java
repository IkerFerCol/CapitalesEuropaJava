package com.example.capitaleseuropajava;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface CapitalDao {
    @Query("select * from capital")
    LiveData<List<Capital>> getCapital();

    @Insert
    void addCapital(Capital capital);

    @Insert
    void addCapitals(List<Capital> capitals);

    @Delete
    void deleteCapital(Capital capital);

    @Query("DELETE FROM capital")
    void deleteCapitales();
}
