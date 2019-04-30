package com.example.lenovo.newapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.w3c.dom.Text;

public class RegistrationActivity extends AppCompatActivity {
  private EditText userName,userPassword,userEmail;
  private Button regButton;
  private TextView userLogin;
  private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        setupUIViews();
        firebaseAuth = firebaseAuth.getInstance();

        regButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if( validate()){
                   //update to database
                   String user_email = userEmail.getText().toString().trim();
                   String user_password = userPassword.getText().toString().trim();

                   firebaseAuth.createUserWithEmailAndPassword(user_email ,user_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                       @Override
                       public void onComplete(@NonNull Task<AuthResult> task) {
                           if(task.isSuccessful()) {
                          Toast.makeText(RegistrationActivity.this,"registration succefful" , Toast.LENGTH_SHORT).show();
                       }else
                           {
                               Toast.makeText(RegistrationActivity.this, "Registration failed", Toast.LENGTH_SHORT).show();
                           }

                       }
                   });
               }
            }
        });
        userLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegistrationActivity.this,MainActivity.class));
            }
        });
    }

    private void setupUIViews(){
        userName = (EditText)findViewById(R.id.etUserName);
        userPassword = (EditText)findViewById(R.id.etUserPass);
        userEmail = (EditText)findViewById(R.id.etUserEmail);
        regButton = (Button) findViewById(R.id.regbtn);
        userLogin = (TextView)findViewById(R.id.Login);
    }

    private boolean validate(){
        Boolean result = false;
         String name = userName.getText().toString();
         String password = userPassword.getText().toString();
         String Email = userEmail.getText().toString();

         if(name.isEmpty() || password.isEmpty() || Email.isEmpty()){
             Toast.makeText(this,"please enter all detail", Toast.LENGTH_SHORT).show();
         }else {
             result=true;

         }
             return result;
    }
}
