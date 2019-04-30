package com.example.lenovo.newapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
private EditText Name;
private EditText password;
private TextView info;
private Button login;
private int counter=5;
private TextView userRegistration;
private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Name = findViewById(R.id.etName);
        password = findViewById(R.id.etPass);
        info = findViewById(R.id.textView1);
        login = findViewById(R.id.btn);
        userRegistration = findViewById(R.id.textView3);
        info.setText("no. of attemps remaining 5");
        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser();
        if(user !=null){
            finish();
            startActivity(new Intent(MainActivity.this, SecondActivity.class));
        }

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate(Name.getText().toString(),password.getText().toString());
            }
        });
        userRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, RegistrationActivity.class));
            }
        });


    }
    private void validate(String userName, String userPassword){
       firebaseAuth.signInWithEmailAndPassword(userName, userPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
           @Override
           public void onComplete(@NonNull Task<AuthResult> task) {
             if(task.isSuccessful()){
                 Toast.makeText(MainActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
                 startActivity(new Intent(MainActivity.this, SecondActivity.class));
             }  else{
                 Toast.makeText(MainActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
                 counter--;
                 info.setText("no.of Attempts remaining = " + counter);
                 if(counter == 0){
                     login.setEnabled(false);
                 }
             }
           }
       });
    }

    @Override
    protected void onStop() {
        super.onStop();
        firebaseAuth.signOut();
    }

}
