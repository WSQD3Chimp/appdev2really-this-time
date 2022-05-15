package com.example.appdev;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SignUp extends AppCompatActivity {

    private FirebaseAuth mAuth;

    Button signup;
    TextView username, flname, password, confirmpass, email, phone;
    Context mContext;
    Name name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        signup = findViewById(R.id.button);
        username = findViewById(R.id.editTextTextPersonName3);
        flname = findViewById(R.id.editTextTextPersonName4);
        password = findViewById(R.id.editTextTextPersonName5);
        confirmpass = findViewById(R.id.editTextTextPersonName6);
        phone = findViewById(R.id.editTextTextPersonName7);
        email = findViewById(R.id.editTextTextPersonName8);
        mAuth = FirebaseAuth.getInstance();

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Username = username.getText().toString();
                String Flname = flname.getText().toString();
                String Password = password.getText().toString();
                String Confirmpass = confirmpass.getText().toString();
                String Email = email.getText().toString();
                String Phone = phone.getText().toString();

                if (Username.isEmpty()) {
                    username.setError("Username Required");
                    username.requestFocus();
                    return;
                }

                if (Flname.isEmpty()) {
                    flname.setError("First and Last name is Required");
                    flname.requestFocus();
                    return;
                }

                if (Password.isEmpty()) {
                    password.setError("Password Required");
                    password.requestFocus();
                    return;
                }

                if (Password.length() < 6) {
                    password.setError("Password must be greater than 6 characters");
                    password.requestFocus();
                    return;
                }

                if (Confirmpass.isEmpty()) {
                    confirmpass.setError("Confirmpass Required");
                    confirmpass.requestFocus();
                    return;
                }

                if (!Confirmpass.equals(Password)) {
                    confirmpass.setError("passwords do not match");
                    confirmpass.requestFocus();
                    return;
                }

                if (Email.isEmpty()) {
                    email.setError("Email Required");
                    email.requestFocus();
                    return;
                }

                if (!Patterns.EMAIL_ADDRESS.matcher(Email).matches()){
                    email.setError("Provide valid email");
                    email.requestFocus();
                    return;
                }


                if (Phone.isEmpty()) {
                    phone.setError("Phone Required");
                    phone.requestFocus();
                    return;
                }

                if (!Patterns.PHONE.matcher(Phone).matches()) {
                    phone.setError("Provide valid phone number");
                    phone.requestFocus();
                    return;
                }

                mAuth.createUserWithEmailAndPassword(Email,Password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()) {
                                Name name = new Name(Username,Flname,Password,Email,Phone);
                                FirebaseDatabase.getInstance().getReference("Accounts")
                                        .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                        .setValue(name).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {
                                            Toast.makeText(SignUp.this,
                                                "You registered successfully", Toast.LENGTH_SHORT).show();
                                            startActivity(new Intent(SignUp.this, MainActivity.class));
                                        }else {
                                            Toast.makeText(SignUp.this,
                                              "Registration failed ", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                            }
                        }
                    });
            }
        });
    }
}