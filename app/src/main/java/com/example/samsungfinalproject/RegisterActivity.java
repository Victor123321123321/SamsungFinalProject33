package com.example.samsungfinalproject;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
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

        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
            Toast.makeText(this, "All fields are required", Toast.LENGTH_SHORT).show();
            return;
        }

        // Хэшируем пароль перед сохранением
        String hashedPassword = PasswordUtils.hashPassword(password);

        long id = userDb.addUser(username, hashedPassword);

        if (id != -1) {
            Toast.makeText(this, "Registration successful", Toast.LENGTH_SHORT).show();
            UserManager.setCurrentUserId((int) id);
            startActivity(new Intent(RegisterActivity.this, MainActivity.class));
            finish();
        } else {
            Toast.makeText(this, "Registration failed", Toast.LENGTH_SHORT).show();
        }

    }
}
