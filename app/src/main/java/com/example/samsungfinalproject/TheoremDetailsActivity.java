package com.example.samsungfinalproject;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class TheoremDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theorem_details);

        Intent intent = getIntent();
        String title = intent.getStringExtra("theorem_title");
        String content = intent.getStringExtra("theorem_content");

        TextView titleTextView = findViewById(R.id.title_text_view);
        TextView contentTextView = findViewById(R.id.content_text_view);

        titleTextView.setText(title);
        contentTextView.setText(content);
    }
}
