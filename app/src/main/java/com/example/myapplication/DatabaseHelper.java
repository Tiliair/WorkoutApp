package com.example.myapplication;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DatabaseHelper {

    private final DatabaseReference databaseReference;

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

    public interface PasswordCheckListener {
        void onPasswordCheck(boolean passwordCorrect);
    }

    public interface AccountExistsListener {
        void onAccountCheck(boolean accountExists);
    }
}
