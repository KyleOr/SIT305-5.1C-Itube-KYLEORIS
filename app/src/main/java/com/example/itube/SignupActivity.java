package com.example.itube;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class SignupActivity extends AppCompatActivity {

    EditText fullName, username, password, confirmPassword;
    Button createAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        fullName = findViewById(R.id.editTextFullName);
        username = findViewById(R.id.editTextUsername);
        password = findViewById(R.id.editTextPassword);
        confirmPassword = findViewById(R.id.editTextConfirmPassword);
        createAccount = findViewById(R.id.buttonCreateAccount);

        createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = fullName.getText().toString();
                String user = username.getText().toString();
                String pass = password.getText().toString();
                String confirm = confirmPassword.getText().toString();

                if (name.isEmpty() || user.isEmpty() || pass.isEmpty() || confirm.isEmpty()) {
                    Toast.makeText(SignupActivity.this, "Please fill out all fields", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!pass.equals(confirm)) {
                    Toast.makeText(SignupActivity.this, "Passwords do not match!", Toast.LENGTH_SHORT).show();
                } else {
                    // Save user info in SharedPreferences
                    SharedPreferences sharedPref = getSharedPreferences("UserPrefs", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putString("fullName", name);
                    editor.putString("username", user);
                    editor.putString("password", pass);
                    editor.apply();

                    Toast.makeText(SignupActivity.this, "Account created for: " + user, Toast.LENGTH_SHORT).show();
                    finish(); // go back to login
                }
            }
        });
    }
}
