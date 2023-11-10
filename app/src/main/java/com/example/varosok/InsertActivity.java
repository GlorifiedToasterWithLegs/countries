package com.example.varosok;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class InsertActivity extends AppCompatActivity {

    private EditText InputName, InputCountry, InputPopulation;
    private Button BtnInputAdd, BtnInputBack;
    private Intent intent;
    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);
        init();

        BtnInputAdd.setOnClickListener(view -> {
            String inputName = InputName.getText().toString();
            String inputCountry = InputCountry.getText().toString();
            String inputPopulation = InputPopulation.getText().toString();
            if (!inputName.trim().equals("")&&!inputCountry.trim().equals("")&&!inputPopulation.trim().equals("")) {
                if (dbHelper.addToTable(inputName, inputCountry, Integer.parseInt(InputPopulation.getText().toString()))) {
                    Toast.makeText(InsertActivity.this, "Sikeresen hozzáadva!", Toast.LENGTH_SHORT).show();
                } else { Toast.makeText(InsertActivity.this, "Sikertelen hozzáadás!", Toast.LENGTH_SHORT).show(); }
            } else {
                Toast.makeText(InsertActivity.this, "Ne hagyjon üres mezőt!", Toast.LENGTH_SHORT).show();
            }
        });

        BtnInputBack.setOnClickListener(view -> {
            intent = new Intent(InsertActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        });
    }

    private void init() {
        InputName = findViewById(R.id.InputName);
        InputCountry = findViewById(R.id.InputCountry);
        InputPopulation = findViewById(R.id.InputPopulation);
        BtnInputAdd = findViewById(R.id.BtnInputAdd);
        BtnInputBack = findViewById(R.id.BtnInputBack);
        dbHelper = new DBHelper(this);
    }
}