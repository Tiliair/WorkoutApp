package Activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.DatabaseHelper;
import com.example.myapplication.R;
import com.google.android.material.button.MaterialButton;
import android.widget.EditText;

public class CreateAccountActivity extends AppCompatActivity {

    private EditText username;
    private EditText password;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_account);

        // Initialize DatabaseHelper
        databaseHelper = new DatabaseHelper(); // No need to pass context here

        // Initialize views
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);

        // Handle Create Account button click
        MaterialButton createAccountButton = findViewById(R.id.createAccount_submit);
        createAccountButton.setOnClickListener(v -> createAccount());

        // Handle Back button click
        MaterialButton backButton = findViewById(R.id.backBtn);
        backButton.setOnClickListener(v -> onBackPressed());
    }

    private void createAccount() {
        String usernameText = username.getText().toString().trim();
        String passwordText = password.getText().toString().trim();

        // Perform validation
        if (usernameText.isEmpty() || passwordText.isEmpty()) {
            Toast.makeText(this, "Please enter both username and password", Toast.LENGTH_SHORT).show();
            return;
        }

        // Check if the account already exists
        databaseHelper.checkAccountExists(usernameText, new DatabaseHelper.AccountExistsListener() {
            @Override
            public void onAccountCheck(boolean accountExists) {
                if (accountExists) {
                    // Username already exists
                    Toast.makeText(CreateAccountActivity.this, "Username already exists. Please choose a different one.", Toast.LENGTH_SHORT).show();
                } else {
                    // Save the username and password
                    boolean success = databaseHelper.insertAccount(usernameText, passwordText);
                    if (success) {
                        Toast.makeText(CreateAccountActivity.this, "Account created successfully", Toast.LENGTH_SHORT).show();
                        // Proceed to next activity
                        startActivity(new Intent(CreateAccountActivity.this, CreateAccountActivity.class));
                    } else {
                        Toast.makeText(CreateAccountActivity.this, "Failed to create account. Please try again.", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}
