package edu.gatech.CS2340.suchwow.Persistence;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.GregorianCalendar;

import edu.gatech.CS2340.suchwow.Domain.Account;
import edu.gatech.CS2340.suchwow.Domain.Transaction;
import edu.gatech.CS2340.suchwow.Domain.User;
import edu.gatech.CS2340.suchwow.Security.EncryptionHandler;

/**
 * An interfacer between the domain and the SQLite database that
 * handles as SQLite queries.
 */
public class SQLiteHandler extends SQLiteOpenHelper {
    /**
     * Current version of the database.
     */
    private static final int DATABASE_VERSION = 4;
    /**
     * The name of the database.
     */
    private static final String DATABASE_NAME = "FinancialDatabase";

    // tables and their fields go here in format: TABLE_($NAME), ($NAME)_FIELD
    /**
     * Name of the Users table.
     */
    private static final String TABLE_USERS = "users";
    /**
     * Column name of the name column in the Users table.
     */
    private static final String USERS_NAME = "name";
    /**
     * Column name of the password column in the Users table.
     */
    private static final String USERS_PASSWORD = "password";

    /**
     * Column name of the salt column in the Users table
     */
    private static final String USERS_SALT = "salt";
    /**
     * Name of the Accounts table.
     */
    private static final String TABLE_ACCOUNTS = "accounts";
    /**
     * Column name of the user column in the Accounts table.
     */
    private static final String ACCOUNTS_USER = "user";
    /**
     * Column name of the account name column in the Accounts table.
     */
    private static final String ACCOUNTS_NAME = "name";
    /**
     * Column name of the account number column in the Accounts table.
     */
    private static final String ACCOUNTS_NUMBER = "number";
    /**
     * Column name of the display name column in the Accounts table.
     */
    private static final String ACCOUNTS_DISPLAY = "display";
    /**
     * Column name of the account balance column in the Accounts table.
     */
    private static final String ACCOUNTS_BALANCE = "balance";
    /**
     * Column name of the account interest rate column in the Accounts table.
     */
    private static final String ACCOUNTS_INTEREST = "interest";

    /**
     * Name of the Transactions table.
     */
    private static final String TABLE_TRANSACTIONS = "transactions";
    /**
     * Column name of the transaction category column in the Transactions table.
     */
    private static final String TRANS_CAT = "category";
    /**
     * Column name of the user column in the Transactions table.
     */
    private static final String TRANS_USER = "user";
    /**
     * Column name of the account name column in the Transactions table.
     */
    private static final String TRANS_ACCNAME = "account_name";
    /**
     * Column name of the account number column in the Transactions table.
     */
    private static final String TRANS_ACCNUM = "account_number";
    /**
     * Column name of the transaction name column in the Transactions table.
     */
    private static final String TRANS_NAME = "name";
    /**
     * Column name of the transaction amount column in the Transactions table.
     */
    private static final String TRANS_AMOUNT = "amount";
    /**
     * Column name of the is deposit column in the Transactions table.
     */
    private static final String TRANS_ISDEPOSIT = "is_deposit";
    /**
     * Column name of the user defined transaction time column in the Transactions table.
     */
    private static final String TRANS_USERTIME = "user_time";
    /**
     * Column number of the system transaction time column in the Transactions table.
     */
    private static final String TRANS_SYSTIME = "system_time";

    /**
     * Constructor for the handler.
     * @param context The context of the activity which calls the constructor.
     */
    public SQLiteHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // On database creation

    /**
     * Run on the creation of the application database. Creates tables and
     * populates initial admin user.
     * @param db The created database
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        // create tables
        String createUsersTable = String.format("CREATE TABLE %s (%s TEXT PRIMARY KEY, %s TEXT NOT NULL, %s TEXT NOT NULL)",
                TABLE_USERS, USERS_NAME, USERS_PASSWORD, USERS_SALT);
        db.execSQL(createUsersTable);
        String createAccountsTable = String.format("CREATE TABLE %s "
                                       + "(%s TEXT, %s TEXT, %s TEXT, %s TEXT, %s REAL, %s REAL, "
                                       + "PRIMARY KEY (%s, %s, %s), "
                                       + "FOREIGN KEY (%s) REFERENCES %s(%s))", TABLE_ACCOUNTS, ACCOUNTS_USER,
                                       ACCOUNTS_NAME,
                                       ACCOUNTS_NUMBER, ACCOUNTS_DISPLAY, ACCOUNTS_BALANCE, ACCOUNTS_INTEREST,
                                       ACCOUNTS_USER, ACCOUNTS_NAME, ACCOUNTS_NUMBER, ACCOUNTS_USER, TABLE_USERS,
                                       USERS_NAME);
        db.execSQL(createAccountsTable);
        String createTransactionsTable = String.format("CREATE TABLE %s"
                + "(%s TEXT, %s TEXT, %s TEXT, %s TEXT, %s TEXT, %s REAL, %s INTEGER, %s INTEGER, %s INTEGER, "
                + "FOREIGN KEY (%s, %s, %s) REFERENCES %s(%s, %s, %s))",
                TABLE_TRANSACTIONS, TRANS_CAT, TRANS_USER, TRANS_ACCNAME, TRANS_ACCNUM, TRANS_NAME,
                TRANS_AMOUNT, TRANS_ISDEPOSIT, TRANS_USERTIME, TRANS_SYSTIME,
                TRANS_USER, TRANS_ACCNAME, TRANS_ACCNUM,
                TABLE_ACCOUNTS, ACCOUNTS_USER, ACCOUNTS_NAME, ACCOUNTS_NUMBER);
        db.execSQL(createTransactionsTable);
        // add default user
        ContentValues values = new ContentValues();
        values.put(USERS_NAME, "admin"); // User name
        values.put(USERS_PASSWORD, "WM9HFAmLtdYwX4duPRLFWg=="); // User password
        values.put(USERS_SALT, "wowsuchsalt");
        // insert username and password into table
        db.insert(TABLE_USERS, null, values);
    }

    /**
     * Run when database is upgraded. Drops all data and recreates the tables.
     * @param db The database being upgraded
     * @param oldVersion The version number of the old database
     * @param newVersion The version number of the upgraded database
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older tables if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ACCOUNTS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TRANSACTIONS);
        // Create tables again
        onCreate(db);
    }

    // SQL operations

    /**
     * Adds the User instance to the database.
     * @param user The User instance being inserted
     * @throws InvalidUserException Thrown if the user already exists in the database
     */
    public void addUser(User user) throws InvalidUserException {
        // get database reference
        SQLiteDatabase db = this.getWritableDatabase();
        // read in username and password
        ContentValues values = new ContentValues();
        values.put(USERS_NAME, user.getName()); // User name
        values.put(USERS_PASSWORD, user.getPasswordHash()); // User password
        values.put(USERS_SALT, user.getPasswordSalt());
        // insert username and password into table
        try {
            db.insertOrThrow(TABLE_USERS, null, values);
        } catch (SQLException ex) {
            throw new InvalidUserException("User already exists in database");
        } finally {
            db.close(); // Closing database connection
        }
    }

    /**
     * Adds the Account instance to the database.
     * @param user The User which owns the account
     * @param account The account being inserted
     * @throws InvalidAccountException Thrown if identical account exists
     */
    public void addAccount(User user,
                           Account account) throws InvalidAccountException {
        // get database reference
        SQLiteDatabase db = this.getWritableDatabase();
        // read in values
        ContentValues values = new ContentValues();
        values.put(ACCOUNTS_USER, user.getName()); // User name
        values.put(ACCOUNTS_NAME, account.getName());
        values.put(ACCOUNTS_NUMBER, account.getAccountNumber());
        values.put(ACCOUNTS_DISPLAY, account.getDisplayName());
        values.put(ACCOUNTS_BALANCE, account.getBalance());
        values.put(ACCOUNTS_INTEREST, account.getInterestRate());
        // insert username and password into table
        try {
            db.insertOrThrow(TABLE_ACCOUNTS, null, values);
        } catch (SQLException ex) {
            throw new InvalidAccountException("Identical account already exists for user");
        } finally {
            db.close(); // Closing database connection
        }
    }

    /**
     * Adds the Transaction instance to the database. Assigns
     * the Transaction instance with a unique ID for referencing
     * within the database.
     * @param user The User instance which made the transaction
     * @param account The Account instance to which the transaction belongs
     * @param transaction The Transaction instance being inserted
     */
    public void addTransaction(User user, Account account, Transaction transaction) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TRANS_USER, user.getName());
        values.put(TRANS_ACCNAME, account.getName());
        values.put(TRANS_ACCNUM, account.getAccountNumber());
        values.put(TRANS_CAT, transaction.getCategory());
        values.put(TRANS_NAME, transaction.getName());
        values.put(TRANS_AMOUNT, transaction.getAmount());
        values.put(TRANS_ISDEPOSIT, transaction.isDeposit() ? 1 : 0);
        values.put(TRANS_USERTIME, transaction.getUserTimeStamp().getTimeInMillis());
        values.put(TRANS_SYSTIME, transaction.getSystemTimeStamp().getTimeInMillis());
        transaction.setID(db.insert(TABLE_TRANSACTIONS, null, values));

        // need to update stored account balance too
        ContentValues accountVals = new ContentValues();
        accountVals.put(ACCOUNTS_BALANCE, account.getBalance());
        String whereClause = String.format("%s=? AND %s=? AND %s=?", ACCOUNTS_USER, ACCOUNTS_NAME, ACCOUNTS_NUMBER);
        db.update(TABLE_ACCOUNTS, accountVals, whereClause, new String[] {user.getName(), account.getName(), account.getAccountNumber()});

        db.close();
    }

    /**
     * Retrieves a User instance for the provided credentials and populates all accounts
     * and transactions.
     * @param username The user's username
     * @return The User instance matching the credentials
     * @throws InvalidUserException Thrown if user does not exist
     * @throws InvalidPasswordException Thrown if password is incorrect
     */
    public User getUser(String username) throws InvalidUserException {
        // get database
        SQLiteDatabase db = this.getReadableDatabase();
        // generate query and send it off
        String whereClause = String.format("%s=?", USERS_NAME);
        Cursor cursor = db.query(TABLE_USERS, new String[] {USERS_NAME, USERS_PASSWORD, USERS_SALT},
                                 whereClause, new String[] {username}, null, null, null);
        cursor.moveToFirst();
        // check query results
        User user = null;
        // no matching username
        if (cursor.getCount() == 0) {
            throw new InvalidUserException("Username not found in database");
        }
        // matching username, recreate user object
        user = new User(cursor.getString(0), cursor.getString(1), cursor.getString(2));

        // close out and return
        cursor.close();
        db.close();
        user.setAccounts(getAccounts(user));
        return user;
    }

    /**
     * Retrieves the accounts of the User as a list of Account instances. Populates
     * Account instances with transactions stored in the database.
     * @param user The user which owns the accounts
     * @return The accounts belonging to the user
     */
    public ArrayList<Account> getAccounts(User user) {
        SQLiteDatabase db = this.getReadableDatabase();
        String whereClause = String.format("%s=?", ACCOUNTS_USER);
        String[] fields = {ACCOUNTS_NAME, ACCOUNTS_NUMBER, ACCOUNTS_DISPLAY, ACCOUNTS_BALANCE, ACCOUNTS_INTEREST};
        Cursor cursor = db.query(TABLE_ACCOUNTS, fields, whereClause, new String[]{user.getName()},
                null, null, null);
        cursor.moveToFirst();
        ArrayList<Account> accounts = new ArrayList<Account>();
        while (!cursor.isAfterLast()) {
            Account currentAccount = new Account(cursor.getFloat(3), cursor.getString(0));
            currentAccount.setAccountNumber(cursor.getString(1));
            currentAccount.setDisplayName(cursor.getString(2));
            currentAccount.setInterestRate(cursor.getFloat(4));
            currentAccount.setTransactions(getTransactions(user, currentAccount));
            accounts.add(currentAccount);
            cursor.moveToNext();
        }
        cursor.close();
        db.close();
        return accounts;
    }

    /**
     * Retrieves the transactions of the given account from the database.
     * @param user The owner of the account
     * @param account The account to which the transactions belong
     * @return The transactions belonging to the account
     */
    public ArrayList<Transaction> getTransactions(User user, Account account) {
        SQLiteDatabase db = this.getReadableDatabase();
        String whereClause = String.format("%s=? AND %s=? AND %s=?", TRANS_USER, TRANS_ACCNAME, TRANS_ACCNUM);
        String[] fields = {TRANS_NAME, TRANS_CAT, TRANS_AMOUNT, TRANS_ISDEPOSIT, TRANS_USERTIME, TRANS_SYSTIME, "rowid"};
        Cursor cursor = db.query(TABLE_TRANSACTIONS, fields, whereClause, new String[]{user.getName(), account.getName(), account.getAccountNumber()},
                null, null, null);
        cursor.moveToFirst();
        ArrayList<Transaction> transactions = new ArrayList<Transaction>();
        while (!cursor.isAfterLast()) {
            String name = cursor.getString(0);
            String category = cursor.getString(1);
            float amount = cursor.getFloat(2);
            boolean isDeposit = cursor.getInt(3) == 1;
            GregorianCalendar userTime = new GregorianCalendar();
            userTime.setTimeInMillis(cursor.getLong(4));
            GregorianCalendar sysTime = new GregorianCalendar();
            sysTime.setTimeInMillis(cursor.getLong(5));
            long id = cursor.getLong(6);
            Transaction newTransaction = new Transaction(name, amount, isDeposit, category, userTime, sysTime);
            newTransaction.setID(id);

            transactions.add(newTransaction);
            cursor.moveToNext();
        }
        cursor.close();
        db.close();
        return transactions;
    }

    /**
     * Indicates an issue with the provided User or username.
     */
    public static class InvalidUserException extends Exception {
        /**
         * Constructor.,
         */
        public InvalidUserException() {
            super();
        }

        /**
         * Constructor with message.
         * @param message The message of the exception
         */
        public InvalidUserException(String message) {
            super(message);
        }
    }

    /**
     * Indicates an issue with the provided password credential.
     */
    public static class InvalidPasswordException extends Exception {
        /**
         * Constructor.
         */
        public InvalidPasswordException() {
            super();
        }

        /**
         * Constructor with message.
         * @param message The message of the exception
         */
        public InvalidPasswordException(String message) {
            super(message);
        }
    }

    /**
     * Indicates an error with the provided Account instance.
     */
    public static class InvalidAccountException extends Exception {
        /**
         * Constructor.
         */
        public InvalidAccountException() {
            super();
        }

        /**
         * Constructor with message.
         * @param message The message of the exception
         */
        public InvalidAccountException(String message) {
            super(message);
        }
    }
}
