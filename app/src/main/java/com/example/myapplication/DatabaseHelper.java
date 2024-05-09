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
        databaseReference = firebaseDatabase.getReference("accounts");
    }

    // Method to insert an account
    public boolean insertAccount(String username, String password) {
        try {
            databaseReference.child(username).setValue(new Account(username, password));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Method to insert account info
    public boolean insertAccountInfo(String username, int height, int weight, int age, String sex, int goal) {
        try {
            databaseReference.child(username).child("info").setValue(new AccountInfo(height, weight, age, sex, goal));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Method to retrieve the user's height
    public int getUserHeight(String username) {
        final int[] height = {0};
        DatabaseReference userInfoRef = databaseReference.child(username).child("info");
        userInfoRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                AccountInfo accountInfo = dataSnapshot.getValue(AccountInfo.class);
                if (accountInfo != null) {
                    height[0] = accountInfo.getHeight();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                height[0] = 0;
            }
        });
        return height[0];
    }

    // Method to retrieve the user's weight
    public int getUserWeight(String username) {
        final int[] weight = {0};
        DatabaseReference userInfoRef = databaseReference.child(username).child("info");
        userInfoRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                AccountInfo accountInfo = dataSnapshot.getValue(AccountInfo.class);
                if (accountInfo != null) {
                    weight[0] = accountInfo.getWeight();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                weight[0] = 0;
            }
        });
        return weight[0];
    }

    // Method to retrieve the user's age
    public int getUserAge(String username) {
        final int[] age = {0};
        DatabaseReference userInfoRef = databaseReference.child(username).child("info");
        userInfoRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                AccountInfo accountInfo = dataSnapshot.getValue(AccountInfo.class);
                if (accountInfo != null) {
                    age[0] = accountInfo.getAge();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                age[0] = 0;
            }
        });
        return age[0];
    }

    // Method to retrieve the user's sex
    public String getUserSex(String username) {
        final String[] sex = {""};
        DatabaseReference userInfoRef = databaseReference.child(username).child("info");
        userInfoRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                AccountInfo accountInfo = dataSnapshot.getValue(AccountInfo.class);
                if (accountInfo != null) {
                    sex[0] = accountInfo.getSex();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                sex[0] = "";
            }
        });
        return sex[0];
    }

    // Method to retrieve the user's goal
    public int getUserGoal(String username) {
        final int[] goal = {0};
        DatabaseReference userInfoRef = databaseReference.child(username).child("info");
        userInfoRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                AccountInfo accountInfo = dataSnapshot.getValue(AccountInfo.class);
                if (accountInfo != null) {
                    goal[0] = accountInfo.getGoal();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                goal[0] = 0;
            }
        });
        return goal[0];
    }

    // Inner classes to store account and account information
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

        public int getHeight() {
            return height;
        }

        public int getWeight() {
            return weight;
        }

        public int getAge() {
            return age;
        }

        public String getSex() {
            return sex;
        }

        public int getGoal() {
            return goal;
        }
    }
}