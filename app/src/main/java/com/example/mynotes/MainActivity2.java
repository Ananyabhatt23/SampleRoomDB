package com.example.mynotes;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity2 extends AppCompatActivity {

    EditText et_task , et_desc;
    Button save_btn,button_update;
    int sno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Intent i= getIntent();
        String type=i.getStringExtra("commingfrom");



        et_task = findViewById(R.id.textview_first);
        et_desc = findViewById(R.id.textview_second);
        save_btn = findViewById(R.id.button_save);
        button_update = findViewById(R.id.button_update);

        if(type.equalsIgnoreCase("edit"))
        {

                save_btn.setVisibility(View.INVISIBLE);
                button_update.setVisibility(View.VISIBLE);
                et_task.setText(i.getStringExtra("task"));
                et_desc.setText(i.getStringExtra("desc"));
                sno=i.getIntExtra("sno",-1);




        }else {
            button_update.setVisibility(View.INVISIBLE);
            save_btn.setVisibility(View.VISIBLE);

        }


        save_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DataClass dataClass = new DataClass();
                dataClass.setTask(et_task.getText().toString());
                dataClass.setDescription(et_desc.getText().toString());

                AppDatabase.getInstance(getApplicationContext()).taskDao().insert(dataClass);

              //  startActivity(new Intent(this,MainActivity.class));
                Intent intent = new Intent(MainActivity2.this,MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

        button_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DataClass dataClass = new DataClass();
                dataClass.setTask(et_task.getText().toString());
                dataClass.setDescription(et_desc.getText().toString());
                dataClass.setSno(sno);

                AppDatabase.getInstance(getApplicationContext()).taskDao().insert(dataClass);

                //  startActivity(new Intent(this,MainActivity.class));
                Intent intent = new Intent(MainActivity2.this,MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });


    }

}
