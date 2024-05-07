package com.example.myapplication;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DatabaseHelper {

    private DatabaseReference databaseReference;

    public DatabaseHelper() {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("users");
    }

    public boolean insertAccount(String username, String password) {
        try {
            databaseReference.child(username).child("password").setValue(password);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean insertAccountInfo(String username, int height, int weight, int age, String sex, int goal) {
        try {
            DatabaseReference userInfoRef = databaseReference.child(username).child("info");
            userInfoRef.child("height").setValue(height);
            userInfoRef.child("weight").setValue(weight);
            userInfoRef.child("age").setValue(age);
            userInfoRef.child("sex").setValue(sex);
            userInfoRef.child("goal").setValue(goal);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean insertUser(String username, String password, int height, int weight, int age, String sex, int goal) {
        try {
            databaseReference.child(username).child("password").setValue(password);
            databaseReference.child(username).child("info").child("height").setValue(height);
            databaseReference.child(username).child("info").child("weight").setValue(weight);
            databaseReference.child(username).child("info").child("age").setValue(age);
            databaseReference.child(username).child("info").child("sex").setValue(sex);
            databaseReference.child(username).child("info").child("goal").setValue(goal);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public void checkPassword(String username, String password, final PasswordCheckListener listener) {
        DatabaseReference userRef = databaseReference.child(username).child("password");
        userRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    String storedPassword = dataSnapshot.getValue(String.class);
                    boolean passwordCorrect = storedPassword.equals(password);
                    listener.onPasswordCheck(passwordCorrect);
                } else {
                    // Username does not exist
                    listener.onPasswordCheck(false);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Handle error
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
