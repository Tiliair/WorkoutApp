package Activity;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.example.myapplication.R;
import com.google.firebase.FirebaseApp;


public class StartupActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.startup);

        // Initialize Firebase
        FirebaseApp.initializeApp(this);


        Button CREATE_ACCOUNT = (Button) findViewById(R.id.createAccount_btn);
        Button LOGIN = (Button) findViewById(R.id.login_btn);

        CREATE_ACCOUNT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(StartupActivity.this, CreateAccountActivity.class));
            }
        });
        LOGIN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(StartupActivity.this, LoginActivity.class));
            }
        });
    }
}

