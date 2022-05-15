package com.example.appdev;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class AddBooks extends AppCompatActivity {
    TextView title, author, year, quantity;
    Button add, back;
    DAOBook dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_books);

        title = findViewById(R.id.bookTitle2);
        author = findViewById(R.id.authNameTW2);
        year = findViewById(R.id.yearReleasedTW2);
        quantity = findViewById(R.id.quantInStock2);
        add = findViewById(R.id.addBookB2);

        dao = new DAOBook();

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Title = title.getText().toString();
                String Author =  author.getText().toString();
                String Year = year.getText().toString();
                String Quantity = quantity.getText().toString();

                if (Title.isEmpty()) {
                    title.setError("Title Required");
                    title.requestFocus();
                    return;
                }

                if (Author.isEmpty()) {
                    author.setError("Author Name Required");
                    author.requestFocus();
                    return;
                }

                if (Year.isEmpty()) {
                    year.setError("Year Of Release Required");
                    year.requestFocus();
                    return;
                }
                if (Quantity.isEmpty()) {
                    quantity.setError("Quantity In Stock Required");
                    quantity.requestFocus();
                    return;
                }

                Book book = new Book(title.getText().toString(), Integer.parseInt(year.getText().toString()), Integer.parseInt(quantity.getText().toString()),  author.getText().toString(), 0);
                dao.Add(book).addOnSuccessListener(suc ->{
                    Toast.makeText(getApplicationContext(), "Book added successfully", Toast.LENGTH_SHORT).show();
                }).addOnFailureListener(er -> {
                    Toast.makeText(getApplicationContext(), "Book not added", Toast.LENGTH_SHORT).show();
                });
            }
        });

//        back.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(getApplicationContext(), LibrarianActivity.class));
//            }
//        });

    }
}