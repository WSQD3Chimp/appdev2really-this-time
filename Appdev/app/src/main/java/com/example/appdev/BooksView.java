package com.example.appdev;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class BooksView extends AppCompatActivity {

    RecyclerView rv;
    BooksViewAdapter BVA;
    DatabaseReference ref;
    Button back;
    ArrayList<Book> bookList;
    SwipeRefreshLayout swipeRefreshLayout;
    DAOBook doa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books_view);

        rv = findViewById(R.id.rvContacts);
        ref = FirebaseDatabase.getInstance().getReference("Users");
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(this));

        bookList = new ArrayList<>();
        BVA = new BooksViewAdapter(bookList, this);
        rv.setAdapter(BVA);

        back = findViewById(R.id.button14);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ClientActivity.class));
            }
        });

        /*rv = findViewById(R.id.rvContacts);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        rv.setLayoutManager(manager);
        BVA = new BooksViewAdapter(this);
        rv.setAdapter(BVA);
        ref = FirebaseDatabase.getInstance().getReference("Books");
        doa = new DAOBook();
        loadData();*/
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    Book book = dataSnapshot.getValue(Book.class);
                    bookList.add(book);
                }
                BVA.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void loadData() {
        doa.get().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<Book> books = new ArrayList<>();
                for(DataSnapshot data: snapshot.getChildren()) {

                    Book book = data.getValue(Book.class);
                    books.add(book);
                }
                BVA.setBooks(books);
                BVA.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}