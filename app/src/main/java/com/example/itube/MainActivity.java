package com.example.itube;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText editTextUsername, editTextPassword;
    Button buttonLogin, buttonSignup;

    private static final String TAG = "MainActivity"; // For Logcat filtering

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextUsername = findViewById(R.id.editTextUsername);
        editTextPassword = findViewById(R.id.editTextPassword);
        buttonLogin = findViewById(R.id.buttonLogin);
        buttonSignup = findViewById(R.id.buttonSignup);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputUsername = editTextUsername.getText().toString().trim();
                String inputPassword = editTextPassword.getText().toString().trim();

                Log.d(TAG, "Login attempt: username=" + inputUsername + ", password=" + inputPassword);

                if (inputUsername.isEmpty() || inputPassword.isEmpty()) {
                    Log.d(TAG, "Fields are empty");
                    return; // Skip login attempt if fields are empty
                }

                SharedPreferences sharedPref = getSharedPreferences("UserPrefs", MODE_PRIVATE);
                String savedUsername = sharedPref.getString("username", null);
                String savedPassword = sharedPref.getString("password", null);

                Log.d(TAG, "Stored credentials: username=" + savedUsername + ", password=" + savedPassword);

                if (inputUsername.equals(savedUsername) && inputPassword.equals(savedPassword)) {
                    Log.d(TAG, "Login successful");

                    // Navigate to HomeActivity (placeholder for home/video screen)
                    Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                    startActivity(intent);
                    finish(); // Close the login screen
                } else {
                    Log.d(TAG, "Login failed - invalid credentials");
                    // You can still log the error here if needed
                }
            }
        });

        buttonSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "Signup button clicked");
                Intent intent = new Intent(MainActivity.this, SignupActivity.class);
                startActivity(intent);
            }
        });
    }
}
