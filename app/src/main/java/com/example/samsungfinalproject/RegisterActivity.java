package com.example.samsungfinalproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {

    private EditText editTextUsername, editTextPassword;
    private Button buttonSave;
    private UserDatabaseHelper userDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        editTextUsername = findViewById(R.id.edit_text_username);
        editTextPassword = findViewById(R.id.edit_text_password);
        buttonSave = findViewById(R.id.button_save);

        userDb = new UserDatabaseHelper(this);

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveUser();
            }
        });
    }

    private void saveUser() {
        String username = editTextUsername.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        long id = userDb.addUser(username, password);

        if (id != -1) {
            Toast.makeText(this, "Registration successful", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(RegisterActivity.this, MainActivity.class));
            finish(); // закрываем активность регистрации
        } else {
            Toast.makeText(this, "Registration failed", Toast.LENGTH_SHORT).show();
        }
    }
}
