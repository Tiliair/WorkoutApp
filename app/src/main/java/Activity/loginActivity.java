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

public class loginActivity extends AppCompatActivity {

    EditText usernameEditText;
    EditText passwordEditText;

    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        databaseHelper = new DatabaseHelper(this);

        Button loginButton = findViewById(R.id.login_submit);
        Button backButton = findViewById(R.id.backBtn);
        usernameEditText = findViewById(R.id.username);
        passwordEditText = findViewById(R.id.password);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = usernameEditText.getText().toString().trim();
                String password = passwordEditText.getText().toString().trim();

                if (username.isEmpty() || password.isEmpty()) {
                    Toast.makeText(loginActivity.this, "Please enter both username and password", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (databaseHelper.checkAccountExists(username)) {
                    // Account exists, check password
                    if (databaseHelper.checkPassword(username, password)) {
                        // Password is correct, login successful
                        Toast.makeText(loginActivity.this, "Login successful", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(loginActivity.this, MainMenuActivity.class));
                    } else {
                        // Password is incorrect
                        Toast.makeText(loginActivity.this, "Invalid password", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    // Account does not exist
                    Toast.makeText(loginActivity.this, "Account does not exist", Toast.LENGTH_SHORT).show();
                }
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(loginActivity.this, StartupActivity.class));
            }
        });
    }
}
