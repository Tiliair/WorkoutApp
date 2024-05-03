package com.example.myapplication;
import android.content.Context;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DatabaseHelper {

    private DatabaseReference databaseReference;

    public DatabaseHelper() {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("accounts");
    }

    public boolean insertAccount(String username, String password) {
        try {
            Account account = new Account(username, password);
            databaseReference.child(username).setValue(account);
            return true; // Return true if insertion is successful
        } catch (Exception e) {
            e.printStackTrace();
            return false; // Return false if an exception occurs during insertion
        }
    }

    public boolean insertAccountInfo(String username, int height, int weight, int age, String sex, int goal) {
        try {
            AccountInfo accountInfo = new AccountInfo(height, weight, age, sex, goal);
            databaseReference.child(username).child("info").setValue(accountInfo);
            return true; // Return true if insertion is successful
        } catch (Exception e) {
            e.printStackTrace();
            return false; // Return false if an exception occurs during insertion
        }
    }

    public void checkPassword(String username, String password, final PasswordCheckListener listener) {
        DatabaseReference userRef = databaseReference.child(username);
        userRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    Account account = dataSnapshot.getValue(Account.class);
                    if (account != null && account.getPassword().equals(password)) {
                        listener.onPasswordCheck(true);
                    } else {
                        listener.onPasswordCheck(false);
                    }
                } else {
                    listener.onPasswordCheck(false);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                listener.onPasswordCheck(false);
            }
        });
    }

    public void checkAccountExists(String username, final AccountExistsListener listener) {
        DatabaseReference userRef = databaseReference.child(username);
        userRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                listener.onAccountCheck(dataSnapshot.exists());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                listener.onAccountCheck(false);
            }
        });
    }

    // Define Account and AccountInfo classes
    private static class Account {
        private String username;
        private String password;

        public Account() {
            // Default constructor required for Firebase
        }

        public Account(String username, String password) {
            this.username = username;
            this.password = password;
        }

        public String getUsername() {
            return username;
        }

        public String getPassword() {
            return password;
        }
    }

    private static class AccountInfo {
        private int height;
        private int weight;
        private int age;
        private String sex;
        private int goal;

        public AccountInfo() {
            // Default constructor required for Firebase
        }

        public AccountInfo(int height, int weight, int age, String sex, int goal) {
            this.height = height;
            this.weight = weight;
            this.age = age;
            this.sex = sex;
            this.goal = goal;
        }

        // Define getters for the fields
    }

    // Listener interfaces for checking password and account existence
    public interface PasswordCheckListener {
        void onPasswordCheck(boolean passwordCorrect);
    }

    public interface AccountExistsListener {
        void onAccountCheck(boolean accountExists);
    }
}
