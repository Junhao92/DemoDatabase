package com.example.a14049472.demodatabase;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button btnInsert;
    Button btnGetTask;
    TextView tvResults;
    ListView lv;
    private ArrayAdapter aa;
    ArrayList<Task> al;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnInsert = (Button) findViewById(R.id.btnInsert);
        btnGetTask=(Button)findViewById(R.id.btnGetTasks);
        tvResults=(TextView)findViewById(R.id.tvResults);
        lv=(ListView)findViewById(R.id.lv);



        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // create the DBHelper object,passing in the
                // activity's Context
                DbHelper db = new DbHelper(MainActivity.this);

                //insert a Task
                db.insertTask("Submit RJ","25 Apr 2016");
                db.close();

            }
        });

        btnGetTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  create the DBHelper object, passing in the

                // activity context

                DbHelper db=new DbHelper(MainActivity.this);

                // Insert a task
                ArrayList<String>data=db.getTaskContent();
                db.close();

                String txt="";

                for (int i=0; i<data.size();i++){
                    Log.d("Database Content",i+". "+data.get(i));
                    txt+=i+". " + data.get(i)+ "\n";

                }
                tvResults.setText(txt);

                //CUSTOM LIST VIEW
                DbHelper db2=new DbHelper(MainActivity.this);
                al=db2.getTasks();
                db2.close();

                aa=new TaskAdapter(MainActivity.this,R.layout.row,al);
                lv.setAdapter(aa);
            }
        });




    }
}
