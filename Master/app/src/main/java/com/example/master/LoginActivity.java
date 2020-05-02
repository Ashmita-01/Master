package com.example.master;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    EditText edit_username, edit_password;
    Button bn_login;
    TextView signup_textview;
    private FirebaseAuth mAuth;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
<<<<<<< HEAD
        setContentView(R.layout.login_activity);
=======
        setContentView(R.layout.activity_login);
        edit_username = (EditText) findViewById(R.id.username);
        edit_password = (EditText) findViewById(R.id.password);
        bn_login = (Button) findViewById(R.id.login_btn);
        signup_textview = (TextView) findViewById(R.id.signup_textview);
        signup_textview.setOnClickListener(this);
        bn_login.setOnClickListener(this);
        mAuth = FirebaseAuth.getInstance();
        progressBar=(ProgressBar)findViewById(R.id.progressbar);
>>>>>>> 7ba45fba1a2c566d123325dd386aa0e0bcb66d95
        findViewById(R.id.signup_textview).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.signup_textview:
                startActivity(new Intent(this,SignUpActivity.class));
                break;
            case R.id.login_btn:
                userlogin();
        }

    }
    public void validate_username_and_password(String username, String Password) {
        if (username.isEmpty()) {
            edit_username.setError("Username is required ");
            edit_username.requestFocus();
        } else if (username.contains("@") || username.matches(".*[a-z].*")) {
            if (!Patterns.EMAIL_ADDRESS.matcher(username).matches()) {
                edit_username.setError("Username is not valid!!");
                edit_username.requestFocus();

            }
        }


        if (Password.isEmpty()) {
            edit_password.setError("Password is required ");
            edit_password.requestFocus();
        } else if (Password.length() < 6) {
            edit_password.setError("Password length must be greater than 6 ");
            edit_password.requestFocus();
        }

    }


    private void userlogin() {
        String username = edit_username.getText().toString().trim();
        String Password = edit_password.getText().toString().trim();
        validate_username_and_password(username, Password);
        progressBar.setVisibility(View.VISIBLE);
        mAuth.signInWithEmailAndPassword(username,Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressBar.setVisibility(View.GONE);
                if (task.isSuccessful())
                {
                    Intent intent =new Intent(getApplicationContext(),UserProfileActivity.class);
                    //stack mai jo bhi activity add hoi vo delete ho jayengi is function sai jese login or signup ab user jab back btn dbaye to app close hogo
                    Toast.makeText(getApplicationContext(), "Login Successfull!", Toast.LENGTH_SHORT).show();
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);


                }
                else
                {
                    Log.d("Loginfailed",task.getException().getMessage());
                    Toast.makeText(getApplicationContext(), "Some error occurred ,please try again after some time ", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
