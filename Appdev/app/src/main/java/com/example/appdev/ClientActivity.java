package com.example.appdev;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class ClientActivity extends AppCompatActivity {

    Button logout, settings, viewCatal, viewBorB, showDef;
    TextView username, definition;
    Bundle bundle;
    String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client);
        logout = findViewById(R.id.button3);
        settings = findViewById(R.id.button4);
        viewCatal = findViewById(R.id.button7);
        viewBorB = findViewById(R.id.button6);
        bundle = getIntent().getExtras();

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });

//        settings.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(getApplicationContext(), SettingsClient.class).putExtra("username", username.getText().toString().substring(7,
//                        username.getText().toString().length() - 1)));
//            }
//        });
//        viewBorB.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//
//            }
//        });
        viewCatal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), BooksView.class));
            }
        });
//        showDef.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                sendRequestOnClick(definition);
//                Toast.makeText(getApplicationContext(), "Definition retrieved", Toast.LENGTH_SHORT).show();
//            }
//        });
//
    }
//
//    private String dictionaryEntries() {
//        final String language = "en-gb";
//        final String word = "Library";
//        final String fields = "definitions";
//        final String strictMatch = "false";
//        final String word_id = word.toLowerCase();
//        return "https://od-api.oxforddictionaries.com:443/api/v2/entries/" + language + "/" + word_id + "?" + "fields=" + fields + "&strictMatch=" + strictMatch;
//    }

//    public void sendRequestOnClick(View view) {
//        WordRequest wR = new WordRequest();
//        url = dictionaryEntries();
//        wR.execute(url);
//    }
}