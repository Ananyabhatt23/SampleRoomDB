package com.example.mynotes;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RecyclerTaskAdapter extends RecyclerView.Adapter<RecyclerTaskAdapter.ViewHolder> {
    Context context;
    List<DataClass> arrayTask;


    RecyclerTaskAdapter(Context context, List<DataClass> arrayTask){
        this.context = context;
        this.arrayTask = arrayTask;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(context).inflate(R.layout.task , parent , false);
       ViewHolder viewHolder = new ViewHolder(view);
       return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
       // holder.img.setImageResource(arrayTask.get(position).img);

        holder.taskname1.setText(arrayTask.get(position).getTask());
        holder.taskname2.setText(arrayTask.get(position).getDescription());
        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context,MainActivity2.class);
                intent.putExtra("commingfrom","edit");
                intent.putExtra("task",arrayTask.get(position).getTask());
                intent.putExtra("desc",arrayTask.get(position).getDescription());
                intent.putExtra("sno",arrayTask.get(position).getSno());
                context.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return arrayTask.size();
    }

    public class  ViewHolder extends  RecyclerView.ViewHolder{
        TextView taskname1, taskname2;
        ImageView img;
        Button edit;

        public ViewHolder(View itemView) {
            super(itemView);

            taskname1 = itemView.findViewById(R.id.text1);
            taskname2 = itemView.findViewById(R.id.text2);
//            img = itemView.findViewById(R.id.imgTask);
            edit = itemView.findViewById(R.id.edit_button);

        }
    }
}

