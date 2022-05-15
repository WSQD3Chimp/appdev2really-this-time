package com.example.appdev;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.widget.LinearLayout;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class BooksView extends AppCompatActivity {

    RecyclerView rv;
    BooksViewAdapter BVA;
    SwipeRefreshLayout swipeRefreshLayout;
    DAOBook doa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books_view);
        rv = findViewById(R.id.rvContacts);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        rv.setLayoutManager(manager);
        BVA = new BooksViewAdapter(this);
        rv.setAdapter(BVA);
        doa = new DAOBook();
        loadData();
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