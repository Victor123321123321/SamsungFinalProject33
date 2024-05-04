package com.example.samsungfinalproject;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddTheoremActivity extends AppCompatActivity {

    private Button buttonSave;
    private EditText editTextTitle;
    private EditText editTextContent;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_theorem);

        databaseHelper = new DatabaseHelper(this);

        editTextTitle = findViewById(R.id.title_edit_text);
        editTextContent = findViewById(R.id.content_edit_text);
        buttonSave = findViewById(R.id.save_button);

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveTheorem();
            }
        });
    }

    private void saveTheorem() {
        String title = editTextTitle.getText().toString().trim();
        String content = editTextContent.getText().toString().trim();

        if (title.isEmpty() || content.isEmpty()) {
            Toast.makeText(this, "Please enter both title and content", Toast.LENGTH_SHORT).show();
            return;
        }
        long result = databaseHelper.addTheorem(title, content);

        if (result != -1) {
            Toast.makeText(this, "Theorem saved successfully", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            Toast.makeText(this, "Failed to save theorem", Toast.LENGTH_SHORT).show();
        }
    }
}
