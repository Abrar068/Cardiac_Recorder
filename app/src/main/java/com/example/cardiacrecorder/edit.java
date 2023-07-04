package com.example.cardiacrecorder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class edit extends AppCompatActivity {

    DatabaseReference databaserefence= FirebaseDatabase.getInstance().getReferenceFromUrl("https://cardiac-recorder-8b794-default-rtdb.firebaseio.com");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        EditText date=findViewById(R.id.datevalueText);
        EditText time=findViewById(R.id.timevalueText);
        EditText systolic=findViewById(R.id.systolicvalueText);
        EditText diastolic=findViewById(R.id.diastolicvalueText);
        EditText heart=findViewById(R.id.heartratevalueText);
        EditText comment=findViewById(R.id.commentvalueText);
        Button save=findViewById(R.id.saveButton);

        Intent intent=getIntent();
        String user=intent.getStringExtra("username");
        String id=intent.getStringExtra("id");

        databaserefence.child("data").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String datevalue = snapshot.child(user).child(id).child("date").getValue(String.class);
                String timevalue = snapshot.child(user).child(id).child("time").getValue(String.class);
                String sysvalue = snapshot.child(user).child(id).child("sys").getValue(String.class);
                String disvalue = snapshot.child(user).child(id).child("dis").getValue(String.class);
                String bpmvalue = snapshot.child(user).child(id).child("bpm").getValue(String.class);
                String cmntvalue = snapshot.child(user).child(id).child("cmnt").getValue(String.class);

                date.setText(datevalue);
                time.setText(timevalue);
                systolic.setText(sysvalue);
                diastolic.setText(disvalue);
                heart.setText(bpmvalue);
                comment.setText(cmntvalue);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String datevalue = date.getText().toString();
                String timevalue = time.getText().toString();
                String sysvalue = systolic.getText().toString();
                String disvalue = diastolic.getText().toString();
                String bpmvalue = heart.getText().toString();
                String cmntvalue = comment.getText().toString();
                if(datevalue.isEmpty() || timevalue.isEmpty() || sysvalue.isEmpty() || disvalue.isEmpty() || bpmvalue.isEmpty() || cmntvalue.isEmpty()){
                    Toast.makeText(edit.this,"Please fill all fields",Toast.LENGTH_SHORT).show();
                }
                else{
                    edit_data(user,id,datevalue,timevalue,sysvalue,disvalue,bpmvalue,cmntvalue);
                    Intent intent =  new Intent(edit.this, MainActivity.class);
                    intent.putExtra("username",user);
                    startActivity(intent);
                }
            }
        });
    }

    public void edit_data(String user,String id,String datevalue,String timevalue,String sysvalue,String disvalue,String bpmvalue,String cmntvalue){
        databaserefence.child("data").addListenerForSingleValueEvent(new ValueEventListener(){
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot){
                databaserefence.child("data").child(user).child(id).child("date").setValue(datevalue);
                databaserefence.child("data").child(user).child(id).child("time").setValue(timevalue);
                databaserefence.child("data").child(user).child(id).child("sys").setValue(sysvalue);
                databaserefence.child("data").child(user).child(id).child("dis").setValue(disvalue);
                databaserefence.child("data").child(user).child(id).child("bpm").setValue(bpmvalue);
                databaserefence.child("data").child(user).child(id).child("cmnt").setValue(cmntvalue);
                Toast.makeText(edit.this,"Data Update Successful",Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error){

            }
        }
        );
    }
}