package com.example.samsungfinalproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;


public class LoginActivity extends AppCompatActivity {

    private EditText editTextUsername, editTextPassword;
    private Button buttonLogin;
    private UserDatabaseHelper userDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editTextUsername = findViewById(R.id.edit_text_username);
        editTextPassword = findViewById(R.id.edit_text_password);
        buttonLogin = findViewById(R.id.button_login);

        userDb = new UserDatabaseHelper(this);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUser();
            }
        });
    }

    private void loginUser() {
        String username = editTextUsername.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        long id = userDb.checkUser(username, password); // Получаем идентификатор пользователя из базы данных

        if (id != -1) {
            // Пользователь существует, переход к MainActivity
            UserManager.setCurrentUserId((int) id); // Устанавливаем идентификатор текущего пользователя
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
            finish(); // закрываем активность авторизации
        } else {
            // Пользователь не существует
            Toast.makeText(LoginActivity.this, "Login failed. User does not exist.", Toast.LENGTH_SHORT).show();
        }
    }

}
