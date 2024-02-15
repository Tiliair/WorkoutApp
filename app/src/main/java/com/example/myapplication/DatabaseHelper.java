package com.example.myapplication;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Accounts.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "accounts";
    private static final String COLUMN_USERNAME = "username";
    private static final String COLUMN_PASSWORD = "password";
    private static final String COLUMN_HEIGHT = "height";
    private static final String COLUMN_WEIGHT = "weight";
    private static final String COLUMN_AGE = "age";
    private static final String COLUMN_SEX = "sex";
    private static final String COLUMN_GOAL = "goal";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableQuery = "CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_USERNAME + " TEXT PRIMARY KEY, " +
                COLUMN_PASSWORD + " TEXT, " +
                COLUMN_HEIGHT + " REAL, " +
                COLUMN_WEIGHT + " REAL, " +
                COLUMN_AGE + " REAL, " +
                COLUMN_SEX + " TEXT, " +
                COLUMN_GOAL + " REAL)";
        db.execSQL(createTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertAccount(String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_USERNAME, username);
        contentValues.put(COLUMN_PASSWORD, password);
        long result = db.insert(TABLE_NAME, null, contentValues);
        return result != -1;
    }

    public boolean checkPassword(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT " + COLUMN_PASSWORD + " FROM " + TABLE_NAME + " WHERE " + COLUMN_USERNAME + "=?", new String[]{username});

        if (cursor != null && cursor.moveToFirst()) {
            int passwordIndex = cursor.getColumnIndex(COLUMN_PASSWORD);
            if (passwordIndex != -1) {
                String storedPassword = cursor.getString(passwordIndex);
                cursor.close();
                return password.equals(storedPassword);
            } else {
                // Handle case where COLUMN_PASSWORD is not found
                cursor.close();
                return false;
            }
        } else {
            // Handle case where username is not found
            if (cursor != null) {
                cursor.close();
            }
            return false;
        }
    }


    public boolean insertAccountInfo(float height, float weight, float age, String sex, String goal) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_HEIGHT, height);
        contentValues.put(COLUMN_WEIGHT, weight);
        contentValues.put(COLUMN_AGE, age);
        contentValues.put(COLUMN_SEX, sex);
        contentValues.put(COLUMN_GOAL, goal);
        long result = db.insert(TABLE_NAME, null, contentValues);
        return result != -1;
    }

    public boolean checkAccountExists(String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_USERNAME + "=?", new String[]{username});
        boolean exists = cursor.getCount() > 0;
        cursor.close();
        return exists;
    }
}
