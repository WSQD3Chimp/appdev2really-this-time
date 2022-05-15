package com.example.appdev;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.HashMap;

public class DAOBook {
    DatabaseReference databaseReference;

    public DAOBook() {
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        databaseReference = db.getReference(Book.class.getSimpleName());
    }

    public Task<Void> Add(Book book) {
        return databaseReference.push().setValue(book);
    }

    public Task<Void> Update (String key, HashMap<String, Object> hashMap) {
        return databaseReference.child(key).updateChildren(hashMap);
    }

    public  Task<Void> Remove (String key) {
        return databaseReference.child(key).removeValue();
    }

    public Query get() {
        return databaseReference.orderByKey();
    }

}
