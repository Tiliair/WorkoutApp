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

public class LoginActivity extends AppCompatActivity {

    private EditText usernameEditText;
    private EditText passwordEditText;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        // Instantiate DatabaseHelper
        databaseHelper = new DatabaseHelper();

        // Find views
        Button loginButton = findViewById(R.id.login_submit);
        Button backButton = findViewById(R.id.backBtn);
        usernameEditText = findViewById(R.id.username);
        passwordEditText = findViewById(R.id.password);

        // Set click listener for login button
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get username and password from EditText fields
                String username = usernameEditText.getText().toString().trim();
                String password = passwordEditText.getText().toString().trim();

                // Check if username or password is empty
                if (username.isEmpty() || password.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Please enter both username and password", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Check password
                databaseHelper.checkPassword(username, password, new DatabaseHelper.PasswordCheckListener() {
                    @Override
                    public void onPasswordCheck(boolean passwordCorrect) {
                        if (passwordCorrect) {
                            // Password is correct, login successful
                            Toast.makeText(LoginActivity.this, "Login successful", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(LoginActivity.this, MainMenuActivity.class));
                        } else {
                            // Password is incorrect
                            Toast.makeText(LoginActivity.this, "Invalid username or password", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        // Set click listener for back button
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, StartupActivity.class));
            }
        });
    }
}
