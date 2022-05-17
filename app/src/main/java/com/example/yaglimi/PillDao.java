package com.example.yaglimi;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface PillDao {

    @Query("SELECT * FROM pill")
    List<Pill> getAll();

    @Query("SELECT * FROM pill WHERE id IN (:pillIds)")
    List<Pill> loadAllByIds(int[] pillIds);

    @Insert
    void insertAll(Pill...pills);

    @Delete
    void delete(Pill pill);
}
