package com.example.mynotes;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface TaskDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(DataClass task);

    @Delete
    void delete(DataClass task);

    @Query("select * from dataclass")
    List<DataClass> getAll();

}
