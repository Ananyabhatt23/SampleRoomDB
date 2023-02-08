package com.example.mynotes;

import androidx.room.Entity;

@Entity
public class TaskModel {
    int img;
    String task;

    public TaskModel(int img, String task)
    {
        this.task = task;
        this.img = img;

    }

}
