package com.example.cardiacrecorder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SignupActivity extends AppCompatActivity {
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://cardiac-recorder-8b794-default-rtdb.firebaseio.com/");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        EditText name=findViewById(R.id.editTextText2);
        EditText username=findViewById(R.id.editTextText3);
        EditText pass=findViewById(R.id.editTextTextPassword2);
        EditText conpass=findViewById(R.id.editTextTextPassword3);
        EditText email=findViewById(R.id.editTextText4);

        Button btn=findViewById(R.id.button2);
        TextView logtxt=findViewById(R.id.loginText);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nametxt=name.getText().toString();
                String usernametxt=username.getText().toString();
                String passtxt=pass.getText().toString();
                String conpasstxt=conpass.getText().toString();
                String emailtxt=email.getText().toString();

                if(nametxt.isEmpty() || usernametxt.isEmpty() || passtxt.isEmpty() || conpasstxt.isEmpty() || emailtxt.isEmpty())
                {
                    Toast.makeText(SignupActivity.this,"Please fill all fields",Toast.LENGTH_SHORT).show();
                }
                else if (!passtxt.equals(conpasstxt))
                {
                    Toast.makeText(SignupActivity.this,"Passwords are not matching",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    databaseReference.child("users").addListenerForSingleValueEvent(new ValueEventListener(){
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot){
                            if(snapshot.hasChild(usernametxt)){
                                Toast.makeText(SignupActivity.this,"Username is already registered",Toast.LENGTH_SHORT).show();
                            }
                            else{
                                Intent intent =  new Intent(SignupActivity.this,otp_verification.class);
                                intent.putExtra("user", usernametxt);
                                intent.putExtra("name", nametxt);
                                intent.putExtra("pass", passtxt);
                                intent.putExtra("phone", emailtxt);
                                startActivity(intent);
                                finish();
                            }
                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError error){

                        }
                    });
                }
            }
        });

        logtxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(SignupActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}
//package com.example.cardiacrecorder;
//
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import com.google.firebase.database.DataSnapshot;
//import com.google.firebase.database.DatabaseError;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.database.ValueEventListener;
//
//public class SignupActivity extends AppCompatActivity {
//    String user,name1,pass1,phone;
//    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://cardiac-recorder-8b794-default-rtdb.firebaseio.com/");
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_signup);
//
//        Intent intent=getIntent();
//        user=intent.getStringExtra("user");
//        name1=intent.getStringExtra("name");
//        pass1=intent.getStringExtra("pass");
//        phone=intent.getStringExtra("phone");
//
//        EditText name=findViewById(R.id.editTextText2);
//        EditText username=findViewById(R.id.editTextText3);
//        EditText pass=findViewById(R.id.editTextTextPassword2);
//        EditText conpass=findViewById(R.id.editTextTextPassword3);
//        EditText email=findViewById(R.id.editTextText4);
//
//        Button btn=findViewById(R.id.button2);
//        TextView logtxt=findViewById(R.id.loginText);
//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String nametxt=name.getText().toString();
//                String usernametxt=username.getText().toString();
//                String passtxt=pass.getText().toString();
//                String conpasstxt=conpass.getText().toString();
//                String emailtxt=email.getText().toString();
//
//                if(nametxt.isEmpty() || usernametxt.isEmpty() || passtxt.isEmpty() || conpasstxt.isEmpty() || emailtxt.isEmpty())
//                {
//                    Toast.makeText(SignupActivity.this,"Please fill all fields",Toast.LENGTH_SHORT).show();
//                }
//                else if (!passtxt.equals(conpasstxt))
//                {
//                    Toast.makeText(SignupActivity.this,"Passwords are not matching",Toast.LENGTH_SHORT).show();
//                }
//                else
//                {
//                    databaseReference.child("users").addListenerForSingleValueEvent(new ValueEventListener(){
//                        @Override
//                        public void onDataChange(@NonNull DataSnapshot snapshot){
//                            if(snapshot.hasChild(usernametxt)){
//                                Toast.makeText(SignupActivity.this,"Username is already registered",Toast.LENGTH_SHORT).show();
//                            }
//                            else{
//                                add_user(user,name1,pass1,phone);
//                                Intent intent =  new Intent(SignupActivity.this,LoginActivity.class);
//                                startActivity(intent);
//                                finish();
//
//                            }
//                        }
//                        @Override
//                        public void onCancelled(@NonNull DatabaseError error){
//
//                        }
//                    });
//                }
//            }
//        });
//
//        logtxt.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent =  new Intent(SignupActivity.this,LoginActivity.class);
//                startActivity(intent);
//            }
//        });
//    }
//    public void add_user(String user,String name,String pass,String phone){
//        databaseReference.child("users").addListenerForSingleValueEvent(new ValueEventListener(){
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot){
//                databaseReference.child("users").child(user).child("name").setValue(name);
//                databaseReference.child("users").child(user).child("pass").setValue(pass);
//                databaseReference.child("users").child(user).child("email").setValue(phone);
//                Toast.makeText(SignupActivity.this,"Register successful",Toast.LENGTH_SHORT).show();
//
//            }
//            @Override
//            public void onCancelled(@NonNull DatabaseError error){
//            }
//        });
//    }
//}

