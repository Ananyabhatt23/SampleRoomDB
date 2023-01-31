package com.example.mynotes;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity2 extends AppCompatActivity {

    EditText et_task , et_desc;
    Button save_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        et_task = findViewById(R.id.textview_first);
        et_desc = findViewById(R.id.textview_second);
        save_btn = findViewById(R.id.button_save);

        save_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DataClass dataClass = new DataClass();
                dataClass.setTask(et_task.getText().toString());
                dataClass.setDescription(et_desc.getText().toString());

                AppDatabase.getInstance(getApplicationContext()).taskDao().insert(dataClass);
            }
        });


    }

}
