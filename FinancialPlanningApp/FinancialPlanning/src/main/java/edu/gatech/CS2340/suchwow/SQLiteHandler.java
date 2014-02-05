package edu.gatech.CS2340.suchwow;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Wayne on 2/5/14.
 */
public class SQLiteHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "FinancialDatabase";

    private static final String TABLE_USERS = "users";
    private static final String USERS_NAME = "name";
    private static final String USERS_PASSWORD = "password";

    // child constructor
    public SQLiteHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_USERS + "("
                + USERS_NAME + " TEXT PRIMARY KEY," + USERS_PASSWORD + " TEXT NOT NULL)";
        db.execSQL(CREATE_CONTACTS_TABLE);

        addUser(new User("admin", "pass123"));
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);

        // Create tables again
        onCreate(db);
    }

    // SQL operations
    void addUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(USERS_NAME, user.getName()); // User name
        values.put(USERS_PASSWORD, user.getPassword()); // User password

        // Inserting Row
        db.insert(TABLE_USERS, null, values);
        db.close(); // Closing database connection
    }

    User getUser(String username, String password) throws InvalidUserException, InvalidPasswordException {
        SQLiteDatabase db = this.getReadableDatabase();

        String whereClause = String.format("%s=?", USERS_NAME);
        Cursor cursor = db.query(TABLE_USERS, new String[]{USERS_NAME, USERS_PASSWORD}, whereClause, new String[]{username}, null, null, null);

        User user = null;
        // no matching username
        if (cursor.getCount() == 0) {
            throw new InvalidUserException("Username not found in database");
        }
        // matching username
        else {
            // matching password
            if (cursor.getString(1).equals(password))
                user = new User(cursor.getString(0), cursor.getString(1));
            // mismatched password
            else
                throw new InvalidPasswordException("Password is incorrect");
        }

        cursor.close();
        db.close();
        return user;
    }

    public class InvalidUserException extends Exception {
        public InvalidUserException() {
            super();
        }

        public InvalidUserException(String message) {
            super(message);
        }
    }

    public class InvalidPasswordException extends Exception {
        public InvalidPasswordException() {
            super();
        }

        public InvalidPasswordException(String message) {
            super(message);
        }
    }
}
