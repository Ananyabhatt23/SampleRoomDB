package com.example.mynotes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.RoomDatabase;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import java.util.ArrayList;
import java.util.List;

import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator;


public class MainActivity extends AppCompatActivity {

    FloatingActionButton btn;
    ConstraintLayout constraintLayout;
    private AppDatabase appDatabase;
    RecyclerView recyclerView;
    RecyclerTaskAdapter recyclerTaskAdapter;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initDB(); //roomdb


        //+ button to go to next activity
        btn = findViewById(R.id.addbtn);
        constraintLayout=findViewById(R.id.constraint_layout);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity2();

            }
        });
        //-------------

        /*Button edit;
        edit = findViewById(R.id.edit_button);

        buildDialog();

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
            }
        });*/


        //db
        //appDatabase = AppDatabase.getInstance(this);
        //-------------


        //RecyclerView linking
        recyclerView = findViewById(R.id.recyclerview);
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


        //Swiping for deletion
        ItemTouchHelper helper = new ItemTouchHelper(callback);
        helper.attachToRecyclerView(recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        refreshAdapter(); //to refresh adapter once it is deletd, updated

    }

    //roomdb
    private void initDB()
    {
        appDatabase=AppDatabase.getInstance(getApplicationContext());

    }

    private List<DataClass> getData()
    {
        return appDatabase.taskDao().getAll();

    }

    /*private void buildDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = getLayoutInflater().inflate(R.id.dialog,null);

        EditText nameEdit = view.findViewById(R.id.nameEdit);

        builder.setView(view);
        builder.setTitle("Title").setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                addCard(nameEdit.getText().toString());
            }
        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        dialog = builder.create();
    }

    private void addCard(String toString) {
        View view = getLayoutInflater().inflate(R.id.card, null);

        TextView name = view.findViewById(R.id.text1);

    }
*/

    ItemTouchHelper.SimpleCallback callback = new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @SuppressLint("NotifyDataSetChanged")
        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            Snackbar snackbar = Snackbar.make(constraintLayout,"Item Deleted!", Snackbar.LENGTH_LONG);
            snackbar.show();
            appDatabase.taskDao().delete(getData().get(viewHolder.getBindingAdapterPosition()));
            refreshAdapter();
        }

        @Override
        public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
            new RecyclerViewSwipeDecorator.Builder(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
                    .addSwipeLeftBackgroundColor(ContextCompat.getColor(MainActivity.this,R.color.deleteColor))
                    .addSwipeLeftActionIcon(R.drawable.delete)
                    .addSwipeLeftLabel("Delete")
                    .setSwipeLeftLabelColor(ContextCompat.getColor(MainActivity.this,R.color.white))
                    .create()
                    .decorate();
            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
        }
    };



    /*ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @SuppressLint("NotifyDataSetChanged")
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
    };*/

    private void openActivity2() {
        Intent intent = new Intent(this,MainActivity2.class);
        intent.putExtra("commingfrom","save");
        startActivity(intent);
    }

    @SuppressLint("NotifyDataSetChanged")
    public void refreshAdapter()
    {
        recyclerTaskAdapter = new RecyclerTaskAdapter(this, getData());
        recyclerView.setAdapter(recyclerTaskAdapter);
        recyclerTaskAdapter.notifyDataSetChanged();
    }

}