package com.example.master;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

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

import com.agrawalsuneet.dotsloader.loaders.AllianceLoader;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;

public class SignUpActivity<allianceLoader> extends AppCompatActivity implements View.OnClickListener {
    EditText edit_username, edit_password,edit_repassword;
    Button btn_register;
    TextView login_textview;
    private FirebaseAuth mAuth;
    ProgressBar progressBar;
    AllianceLoader allianceLoader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signupactivity);
        edit_username = (EditText) findViewById(R.id.username);
        edit_password = (EditText) findViewById(R.id.password);
        edit_repassword = findViewById(R.id.confirm_pass);
        btn_register = (Button) findViewById(R.id.Register_btn);
        login_textview = (TextView) findViewById(R.id.login_textview);
        login_textview.setOnClickListener(this);
        btn_register.setOnClickListener(this);
        mAuth = FirebaseAuth.getInstance();
        progressBar=(ProgressBar)findViewById(R.id.progressbar);
        allianceLoader = findViewById(R.id.loader);


    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_textview:
                startActivity(new Intent(this, LoginActivity.class));
                break;
            case R.id.Register_btn:
                registeruser();
                break;
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

    private void registeruser() {

        String username = edit_username.getText().toString().trim();
        String Password = edit_password.getText().toString().trim();
        validate_username_and_password(username, Password);
        allianceLoader.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(username,Password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        allianceLoader.setVisibility(View.GONE);
                        if(task.isSuccessful())
                        {
                            Intent intent =new Intent(getApplicationContext(),UserProfileActivity.class);
                            //stack mai jo bhi activity add hoi vo delete ho jayengi is function sai jese login or signup ab user jab back btn dbaye to app close hogo
                            Toast.makeText(SignUpActivity.this, "Successfully Registered ", Toast.LENGTH_SHORT).show();
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                            startActivity(intent);
                        }
                        else
                        {
                            if(task.getException()  instanceof FirebaseAuthUserCollisionException)
                            {
                                Toast.makeText(SignUpActivity.this, "You are already registered ,Log In with your Credentials", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(),LoginActivity.class));
                            }
                            else
                            {
                                Log.d("SignUpFailed",task.getException().getMessage());
                                Toast.makeText(getApplicationContext(), "Some error occurred ,please try again after some time ", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });

        AllianceLoader allianceLoader = new AllianceLoader(this,40,6,true,10,
                ContextCompat.getColor(this, R.color.Blue),
                ContextCompat.getColor(this, R.color.Blue),
                ContextCompat.getColor(this, R.color.Blue));

        allianceLoader.setAnimDuration(500);
    }



}