package com.example.cardiacrecorder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class otp_verification extends AppCompatActivity {

    int d1;
    String user,name,pass,phone;
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://cardiac-recorder-8b794-default-rtdb.firebaseio.com");

    String otpFromGoogle = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp_verification);
        Intent intent=getIntent();
        user=intent.getStringExtra("user");
        name=intent.getStringExtra("name");
        pass=intent.getStringExtra("pass");
        phone=intent.getStringExtra("phone");
        //phone = "01767650366";

        EditText input1=findViewById(R.id.input1);
        EditText input2=findViewById(R.id.input2);
        EditText input3=findViewById(R.id.input3);
        EditText input4=findViewById(R.id.input4);
        EditText input5=findViewById(R.id.input5);
        EditText input6=findViewById(R.id.input6);
        Button verify=findViewById(R.id.verify1);
        int min = 1000;
        int max = 9999;

        Random random = new Random();
        //int randomNumber = random.nextInt(max - min + 1) + min;

        //System.out.println("Random 4-digit number: " + randomNumber);

        //SmsManager smsManager = SmsManager.getDefault();
        //smsManager.sendTextMessage(phone, null, "Your OTP is"+randomNumber, null, null);

        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;


        mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

            @Override
            public void onVerificationCompleted(@NonNull PhoneAuthCredential credential) {
                // This callback will be invoked in two situations:
                // 1 - Instant verification. In some cases the phone number can be instantly
                //     verified without needing to send or enter a verification code.
                // 2 - Auto-retrieval. On some devices Google Play services can automatically
                //     detect the incoming verification SMS and perform verification without
                //     user action.
                add_user(user,name,pass,phone);
                Intent intent1 =  new Intent(otp_verification.this,LoginActivity.class);
                startActivity(intent1);
                finish();
            }

            @Override
            public void onVerificationFailed(@NonNull FirebaseException e) {
                // This callback is invoked in an invalid request for verification is made,
                // for instance if the the phone number format is not valid.

                Toast.makeText(otp_verification.this,e.getMessage(),Toast.LENGTH_LONG).show();
                e.printStackTrace();


                if (e instanceof FirebaseAuthInvalidCredentialsException) {
                    // Invalid request
                } else if (e instanceof FirebaseTooManyRequestsException) {
                    // The SMS quota for the project has been exceeded
                }

                // Show a message and update the UI
            }

            @Override
            public void onCodeSent(@NonNull String verificationId,
                                   @NonNull PhoneAuthProvider.ForceResendingToken token) {
                // The SMS verification code has been sent to the provided phone number, we
                // now need to ask the user to enter the code and then construct a credential
                // by combining the code with a verification ID.

                // Save verification ID and resending token so we can use them later
                otpFromGoogle = verificationId;
                Toast.makeText(otp_verification.this,"Sent",Toast.LENGTH_LONG).show();

                //mResendToken = token;
            }
        };

        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(mAuth)
                        .setPhoneNumber("+88"+phone)       // Phone number to verify
                        .setTimeout(120L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(this)                 // (optional) Activity for callback binding
                        // If no activity is passed, reCAPTCHA verification can not be used.
                        .setCallbacks(mCallbacks)          // OnVerificationStateChangedCallbacks
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);

        verify.setOnClickListener(v -> {
            String a=input1.getText().toString();
            String a2=input2.getText().toString();
            String a3=input3.getText().toString();
            String a4=input4.getText().toString();
            String a5=input5.getText().toString();
            String a6=input6.getText().toString();

            if(a.isEmpty()||a2.isEmpty()||a3.isEmpty()||a4.isEmpty()){
                Toast.makeText(otp_verification.this,"Please fill all fields",Toast.LENGTH_SHORT).show();
            }
            else{
                String code = a+a2+a3+a4+a5+a6;

                String codeFromUser = code+"";
                System.out.println(d1);
                verifyUser(codeFromUser);
            }
        });
    }
    private void verifyUser(String code){
        try{
            PhoneAuthCredential credential = PhoneAuthProvider.getCredential(otpFromGoogle,code);
            FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
            firebaseAuth.signInWithCredential(credential)
                    .addOnSuccessListener(authResult -> {
                        add_user(user,name,pass,phone);
                        Intent intent1 =  new Intent(otp_verification.this,LoginActivity.class);
                        startActivity(intent1);
                        finish();
                    }).addOnFailureListener(e -> {
                        e.printStackTrace();
                        Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    });

        }
        catch (Exception e){
            e.printStackTrace();
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    public void add_user(String user,String name,String pass,String phone){
        databaseReference.child("users").addListenerForSingleValueEvent(new ValueEventListener(){
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot){
                databaseReference.child("users").child(user).child("name").setValue(name);
                databaseReference.child("users").child(user).child("pass").setValue(pass);
                databaseReference.child("users").child(user).child("email").setValue(phone);
                Toast.makeText(otp_verification.this,"Register successful",Toast.LENGTH_SHORT).show();

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error){
            }
        });
    }


}
