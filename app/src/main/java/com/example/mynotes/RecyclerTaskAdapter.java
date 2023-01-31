package com.example.mynotes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RecyclerTaskAdapter extends RecyclerView.Adapter<RecyclerTaskAdapter.ViewHolder> {

    public ImageView deleteimg;
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
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
       // holder.img.setImageResource(arrayTask.get(position).img);
        holder.taskname.setText(arrayTask.get(position).getTask());

    }

    @Override
    public int getItemCount() {
        return arrayTask.size();
    }


    public class  ViewHolder extends  RecyclerView.ViewHolder{
        TextView taskname;
        ImageView img;

        public ViewHolder(View itemView) {
            super(itemView);

            taskname = itemView.findViewById(R.id.text1);
            img = itemView.findViewById(R.id.imgTask);

            deleteimg = itemView.findViewById(R.id.delete);

        }
    }
}
