package com.example.appdev;

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
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    DatabaseReference reff;
    Button signup, login, librarianSignInB;
    EditText emailtxt, passtxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        signup = findViewById(R.id.button2);
        login = findViewById(R.id.loginB);
        librarianSignInB = findViewById(R.id.button13);
        emailtxt = findViewById(R.id.editTextTextPersonName);
        passtxt = findViewById(R.id.editTextTextPersonName2);
        mAuth = FirebaseAuth.getInstance();

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, SignUp.class));
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = emailtxt.getText().toString();
                String pass = passtxt.getText().toString();

                if (email.isEmpty()) {
                    emailtxt.setError("Username Required");
                    emailtxt.requestFocus();
                    return;
                }

                if (pass.isEmpty()) {
                    passtxt.setError("Password Required");
                    passtxt.requestFocus();
                    return;
                }

                if (pass.length() < 6) {
                    passtxt.setError("Password must be greater than 6 characters");
                    passtxt.requestFocus();
                    return;
                }

                mAuth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(MainActivity.this,
                                    "You Signed in successfully", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(MainActivity.this, ClientActivity.class));
                        }else {
                            Toast.makeText(MainActivity.this,
                                    "Failed to login check your email and pass", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        librarianSignInB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!emailtxt.getText().toString().isEmpty() || !passtxt.getText().toString().isEmpty()) {
                    if (emailtxt.getText().toString().equals("LIBRARIAN")) {
                        if (passtxt.getText().toString().equals("123456")/*LbDb.PassCorrect(usertxt.getText().toString(), passtxt.getText().toString())*/) {
                            Toast.makeText(MainActivity.this,
                                    "Wait While we log you in", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(MainActivity.this, LibrarianActivity.class).putExtra("username", emailtxt.getText().toString()));
                        }else {
                            Toast.makeText(MainActivity.this,
                                    "Password Doesn't Match The Username", Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        Toast.makeText(MainActivity.this,
                                "Account Doesn't Exist", Toast.LENGTH_SHORT).show();
                    }
                }else
                    Toast.makeText(MainActivity.this,
                            "Fill out all the fields", Toast.LENGTH_SHORT).show();
            }
        });
    }

}