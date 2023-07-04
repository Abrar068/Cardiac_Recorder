package com.example.cardiacrecorder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class details extends AppCompatActivity {

    DatabaseReference databaserefence= FirebaseDatabase.getInstance().getReferenceFromUrl("https://cardiac-recorder-8b794-default-rtdb.firebaseio.com");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        TextView date=findViewById(R.id.datevalueText);
        TextView time=findViewById(R.id.timevalueText);
        TextView systolic=findViewById(R.id.systolicvalueText);
        TextView diastolic=findViewById(R.id.diastolicvalueText);
        TextView heart=findViewById(R.id.heartratevalueText);
        TextView comment=findViewById(R.id.commentvalueText);

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
    }
}