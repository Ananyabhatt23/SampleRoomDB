package com.example.mynotes;
import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class DataClass {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "Sno")
    private int Sno;


    @ColumnInfo(name = "Task")
    private String task;


    @ColumnInfo(name = "Description")
    private String description;

    public int getSno() {

        return Sno;
    }

    public void setSno(int sno) {

        Sno = sno;
    }

    @NonNull
    public String getTask() {

        return task;
    }

    public void setTask(@NonNull String task) {

        this.task = task;
    }

    @NonNull
    public String getDescription() {

        return description;
    }

    public void setDescription(@NonNull String description) {

        this.description = description;
    }
}



