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

    // tables and their fields go here in format: TABLE_($NAME), ($NAME)_FIELD
    private static final String TABLE_USERS = "users";
    private static final String USERS_NAME = "name";
    private static final String USERS_PASSWORD = "password";

    // child constructor
    public SQLiteHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // On database creation
    @Override
    public void onCreate(SQLiteDatabase db) {
        // create tables
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_USERS + "("
                + USERS_NAME + " TEXT PRIMARY KEY," + USERS_PASSWORD + " TEXT NOT NULL)";
        db.execSQL(CREATE_CONTACTS_TABLE);

        // add default user
        ContentValues values = new ContentValues();
        values.put(USERS_NAME, "admin"); // User name
        values.put(USERS_PASSWORD, "pass123"); // User password

        // insert username and password into table
        db.insert(TABLE_USERS, null, values);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older tables if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);

        // Create tables again
        onCreate(db);
    }

    // SQL operations
    public void addUser(User user) throws InvalidUserException {
        // get database reference
        SQLiteDatabase db = this.getWritableDatabase();

        // read in username and password
        ContentValues values = new ContentValues();
        values.put(USERS_NAME, user.getName()); // User name
        values.put(USERS_PASSWORD, user.getPassword()); // User password

        // insert username and password into table
        try {
            db.insertOrThrow(TABLE_USERS, null, values);
        }
        catch (Exception ex) {
            throw new InvalidUserException("User already exists in database");
        }
        finally {
            db.close(); // Closing database connection
        }
    }

    public User getUser(String username, String password) throws InvalidUserException, InvalidPasswordException {
        // get database
        SQLiteDatabase db = this.getReadableDatabase();

        // generate query and send it off
        String whereClause = String.format("%s=?", USERS_NAME);
        Cursor cursor = db.query(TABLE_USERS, new String[]{USERS_NAME, USERS_PASSWORD}, whereClause, new String[]{username}, null, null, null);
        cursor.moveToFirst();

        // check query results
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
        // close out and return
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
