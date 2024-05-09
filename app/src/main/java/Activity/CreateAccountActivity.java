// CreateAccountActivity.java

package Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.example.myapplication.DatabaseHelper;
import com.example.myapplication.R;
import com.google.firebase.FirebaseApp;

public class CreateAccountActivity extends AppCompatActivity {

    private EditText username;
    private EditText password;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_account);
        FirebaseApp.initializeApp(this);

        // Initialize DatabaseHelper
        databaseHelper = new DatabaseHelper(this);

        // Find views
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        Button createAccountButton = findViewById(R.id.createAccount_submit);
        Button backButton = findViewById(R.id.backBtn);

        // Set click listener for create account button
        createAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createAccount();
            }
        });

        // Set click listener for back button
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
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
                        Intent intent = new Intent(CreateAccountActivity.this, CreateAccountQuizActivity.class);
                        intent.putExtra("USERNAME", usernameText); // Pass username to next activity
                        startActivity(intent);

                    } else {
                        Toast.makeText(CreateAccountActivity.this, "Failed to create account. Please try again.", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}
