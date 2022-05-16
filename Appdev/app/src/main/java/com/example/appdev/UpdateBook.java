package com.example.appdev;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UpdateBook extends AppCompatActivity {
    TextView title, author, year, quantity;
    Button update, back;
    DAOBook dao;
    DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_book);


        title = findViewById(R.id.updateBookTitle2);
        author = findViewById(R.id.updateAuthNameTW2);
        year = findViewById(R.id.updateYearReleasedTW2);
        quantity = findViewById(R.id.updateQuantInStock2);
        update = findViewById(R.id.updateBookB2);
        back = findViewById(R.id.backToLibrAct3);
        dao = new DAOBook();

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), LibrarianActivity.class));
            }
        });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}