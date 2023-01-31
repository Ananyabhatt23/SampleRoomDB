package com.example.mynotes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button btn;
    ArrayList<TaskModel> arraytask = new ArrayList<>();
    private AppDatabase appDatabase;
    RecyclerTaskAdapter recyclerTaskAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = findViewById(R.id.addbtn);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity2();

            }
        });

        //db
        //appDatabase = AppDatabase.getInstance(this);



        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));



     /*   arraytask.add(new TaskModel(R.drawable.notes,"Task 1"));
        arraytask.add(new TaskModel(R.drawable.notes,"Task 2"));
        arraytask.add(new TaskModel(R.drawable.notes,"Task 3"));
        arraytask.add(new TaskModel(R.drawable.notes,"Task 4"));
        arraytask.add(new TaskModel(R.drawable.notes,"Task 5"));
        arraytask.add(new TaskModel(R.drawable.notes,"Task 6"));
        arraytask.add(new TaskModel(R.drawable.notes,"Task 7"));
        arraytask.add(new TaskModel(R.drawable.notes,"Task 8"));
        arraytask.add(new TaskModel(R.drawable.notes,"Task 9"));
        arraytask.add(new TaskModel(R.drawable.notes,"Task 10"));
        arraytask.add(new TaskModel(R.drawable.notes,"Task 11"));
*/

        List<DataClass> list= AppDatabase.getInstance(getApplicationContext()).taskDao().getAll();

        RecyclerTaskAdapter adapter = new RecyclerTaskAdapter(this,list);
        recyclerView.setAdapter(adapter);


        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);


    }


    ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            int position = viewHolder.getBindingAdapterPosition();
        switch (direction){
            case ItemTouchHelper.LEFT:
                arraytask.remove(position);
                recyclerTaskAdapter.notifyDataSetChanged();
                break;
            case ItemTouchHelper.RIGHT:
                break;
        }

        }
    };

    private void openActivity2() {
        Intent intent = new Intent(this,MainActivity2.class);
        startActivity(intent);
    }
}