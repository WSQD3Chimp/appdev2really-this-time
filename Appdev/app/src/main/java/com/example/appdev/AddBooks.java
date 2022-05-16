package com.example.appdev;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AddBooks extends AppCompatActivity {
    TextView title, author, year, quantity;
    Button add, back;
    DAOBook dao;
    long maxid;
    DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_books);

        title = findViewById(R.id.bookTitle2);
        author = findViewById(R.id.authNameTW2);
        year = findViewById(R.id.yearReleasedTW2);
        quantity = findViewById(R.id.quantInStock2);
        add = findViewById(R.id.addBookB2);
        back = findViewById(R.id.backToLibrAct2);
        dao = new DAOBook();

        ref = FirebaseDatabase.getInstance().getReference().child("Book");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    maxid = (snapshot.getChildrenCount());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

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

                //      ref.push().setValue(member);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), LibrarianActivity.class));
            }
        });

    }
}