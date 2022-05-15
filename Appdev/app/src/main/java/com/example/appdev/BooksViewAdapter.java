package com.example.appdev;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class BooksViewAdapter extends RecyclerView.Adapter<BooksViewAdapter.ViewHolder> {
    ArrayList<Book> Books = new ArrayList<>();
    Context mContext;
    LayoutInflater minflator;

    public void setBooks(ArrayList<Book> books) {
        books.addAll(books);
    }

    public BooksViewAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = minflator.inflate(R.layout.childbooksview, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Book book = Books.get(position);
        holder.title.setText(book.getTitle());
        holder.author.setText(book.getAuthorName());
        holder.year.setText(book.getYearReleased());
        holder.borrow.setText(book.getQuantityBorrowed());
    }

    @Override
    public int getItemCount() {
        return Books.size();
    }

    public class ViewHolder  extends RecyclerView.ViewHolder {
        TextView title, author, year;
        Button borrow;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.booktitleTW);
            author = itemView.findViewById(R.id.authornameTW);
            year = itemView.findViewById(R.id.yearOfReleaseTW);
            borrow = itemView.findViewById(R.id.borrowB);
        }
    }
}
