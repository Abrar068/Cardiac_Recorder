package com.example.cardiacrecorder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddActivity extends AppCompatActivity {

    EditText e1,e2,e3,e4,e5,e6;
    Button btn1;
    DatabaseReference databaseReference;
    String passeduser;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(AddActivity.this,MainActivity.class);
        intent.putExtra("key", passeduser);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        Intent i = getIntent();
        passeduser = i.getStringExtra("key");

        e1 = findViewById(R.id.datetext);
        e2 = findViewById(R.id.timetext);
        e3 = findViewById(R.id.systext);
        e4 = findViewById(R.id.distext);
        e5 = findViewById(R.id.bpmtext);
        e6 = findViewById(R.id.cmnttext);
        btn1 = findViewById(R.id.addbutton);
        databaseReference = FirebaseDatabase.getInstance().getReference();

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Insertdata();
            }
        });
    }
    private void Insertdata() {

        String time = e1.getText().toString();
        String date = e2.getText().toString();
        String sys = e3.getText().toString();
        String dis = e4.getText().toString();
        String bpm = e5.getText().toString();
        String cmnt = e6.getText().toString();
        String id = databaseReference.push().getKey();

        DataClass dataClass = new DataClass(date,time,sys,dis,bpm,cmnt,passeduser,id);
        if(time.isEmpty() || date.isEmpty() || sys.isEmpty() || dis.isEmpty() || bpm.isEmpty())
        {
            Toast.makeText(AddActivity.this,"Please fill all required fields",Toast.LENGTH_SHORT).show();
        }
        else{
            add_data(id,dataClass,passeduser);
            Intent intent = new Intent(AddActivity.this,MainActivity.class);
            intent.putExtra("key", passeduser);
            startActivity(intent);
            finish();
        }

    }

    public void add_data(String id,DataClass dataClass,String passeduser){
        databaseReference.child("data").child(passeduser).child(id).setValue(dataClass).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(AddActivity.this,"Data added",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}