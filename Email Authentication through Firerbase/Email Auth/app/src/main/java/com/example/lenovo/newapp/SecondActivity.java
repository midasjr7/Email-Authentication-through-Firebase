package com.example.lenovo.newapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;

public class SecondActivity extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
    }

    @Override
    protected void onStop() {
        super.onStop();
        firebaseAuth.signOut();

    }
}
