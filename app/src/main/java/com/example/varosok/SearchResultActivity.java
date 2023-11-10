package com.example.varosok;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SearchResultActivity extends AppCompatActivity {

    private TextView SearchResult;
    private Button BtnSearchBack;

    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);
        init();

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String value = extras.getString("search");
            SearchResult.setText(dbHelper.searchResultList(value));
        }

        BtnSearchBack.setOnClickListener(view -> {
            Intent intent = new Intent(SearchResultActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        });
    }

    private void init() {
        SearchResult = findViewById(R.id.SearchResult);
        BtnSearchBack = findViewById(R.id.BtnSearchBack);
        dbHelper = new DBHelper(this);
    }
}