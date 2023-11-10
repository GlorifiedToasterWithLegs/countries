package com.example.varosok;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText CountryInput;
    private Button BtnSearch, BtnAdd;
    private Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

        BtnSearch.setOnClickListener(view -> {
            String input = CountryInput.getText().toString();
            if (!input.trim().equals("")) {
                intent = new Intent(MainActivity.this, SearchResultActivity.class);
                intent.putExtra("search", input);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(MainActivity.this, "Üres mező!", Toast.LENGTH_SHORT).show();
            }
        });

        BtnAdd.setOnClickListener(view -> {
            intent = new Intent(MainActivity.this, InsertActivity.class);
            startActivity(intent);
            finish();
        });
    }
    private void init() {
        CountryInput = findViewById(R.id.CountryInput);
        BtnSearch = findViewById(R.id.BtnSearch);
        BtnAdd = findViewById(R.id.BtnAdd);
        DBHelper dbHelper = new DBHelper(this);
    }
}