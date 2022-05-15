package com.example.appdev;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class LibrarianActivity extends AppCompatActivity {

    Button AddBook, viewBorBookBut, viewclientDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_librarian);
        AddBook = findViewById(R.id.button10);
        viewclientDB = findViewById(R.id.button11);
        viewBorBookBut = findViewById(R.id.button12);

        AddBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), AddBooks.class));
            }
        });
//        viewclientDB.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(getApplicationContext(), ClientsView.class));
//            }
//        });

    }
}