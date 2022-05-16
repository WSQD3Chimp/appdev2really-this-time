package com.example.appdev;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class BooksViewAdapterLibrarian extends RecyclerView.Adapter<BooksViewAdapterLibrarian.MyViewHolder> {
    private ArrayList<Book> booksList;
    Context context;
    private DAOBook dao;

    public BooksViewAdapterLibrarian(ArrayList<Book> booksList, Context context) {
        this.booksList = booksList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.childviewlibrarian,parent,false);

        return new MyViewHolder(view);
    }

    public void setBooks(ArrayList<Book> books) {
        this.booksList.addAll(books);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Book book = booksList.get(position);
        holder.title.setText(book.getTitle());
        holder.year.setText(book.getYearReleased());
        holder.author.setText(book.getAuthorName());
        holder.update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /*DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
                Query booksQuery = ref.child("firebase-test").orderByChild("title").equalTo("Apple");

                booksQuery.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot appleSnapshot: dataSnapshot.getChildren()) {
                            appleSnapshot.getRef().removeValue();
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Log.e("TAG", "onCancelled", databaseError.toException());
                    }
                });*/

            }
        });
    }

    @Override
    public int getItemCount() {
        return booksList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView title, year, author;
        public Button delete, update;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            title  = (TextView)itemView.findViewById(R.id.booktitleTWL);
            year   = (TextView)itemView.findViewById(R.id.yearOfReleaseTWL);
            author = (TextView)itemView.findViewById(R.id.authornameTWL);
            delete = (Button)itemView.findViewById(R.id.deleteB);
            update = (Button)itemView.findViewById(R.id.updateBL);
        }
    }

    public BooksViewAdapterLibrarian(ArrayList<Book> booksList) {
        this.booksList = booksList;
    }
}
