package com.example.appdev;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class BooksViewLibrarian extends AppCompatActivity {
    /*RecyclerView rv;
    BooksViewAdapterLibrarian BVAL;
    SwipeRefreshLayout swipeRefreshLayout;
    DAOBook doa;
    private ArrayList<Book> bookList = new ArrayList<>();*/

    Button back;
    RecyclerView rv;
    BooksViewAdapterLibrarian BVAL;
    DatabaseReference ref;
    ArrayList<Book> bookList;
    SwipeRefreshLayout swipeRefreshLayout;
    DAOBook doa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books_view_librarian);

        rv = findViewById(R.id.recycler_view_lib);
        ref = FirebaseDatabase.getInstance().getReference("Books");
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(this));

        bookList = new ArrayList<>();
        BVAL = new BooksViewAdapterLibrarian(bookList, this);
        rv.setAdapter(BVAL);

        back = findViewById(R.id.button15);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),LibrarianActivity.class));
            }
        });

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    Book book = dataSnapshot.getValue(Book.class);
                    bookList.add(book);
                }
                BVAL.notifyDataSetChanged();
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
                BVAL.setBooks(books);
                BVAL.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}