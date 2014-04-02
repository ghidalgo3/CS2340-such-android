package edu.gatech.CS2340.suchwow.Activities;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import edu.gatech.CS2340.suchwow.Domain.User;
import edu.gatech.CS2340.suchwow.R;
import edu.gatech.CS2340.suchwow.Persistence.SQLiteHandler;

/**
 * Activity which displays a login screen to the user, offering registration as
 * well.
 * @author Gustavo Hidalgo
 */
public class LoginActivity extends Activity {
    /**
     * A dummy authentication store containing known user names and passwords.
     * TODO: remove after connecting to a real authentication system.
     */
    //    private static final String[] DUMMY_CREDENTIALS = new String[]{
    //            "foo@example.com:hello",
    //            "bar@example.com:world"
    //    };

    /**
     * The default email to populate the email field with.
     */
    public static final String EXTRA_EMAIL =
        "com.example.android.authenticatordemo.extra.EMAIL";

    /**
     * Keep track of the login task to ensure we can cancel it if requested.
     */
    private UserLoginTask mAuthTask = null;

    /**
     *  Value for username at the time of the login attempt.
     */
    private String mUsername;

    /**
     * Value for password at the time of login attempt.
     */
    private String mPassword;

    // UI references.
    /**
     *  Displays user input in the Username section
     */
    private EditText mUsernameView;

    /**
     * Displays user input in the Password section
     */
    private EditText mPasswordView;

    /**
     * The view for the login
     */
    private View mLoginFormView;

    /**
     * The view showing login status
     */
    private View mLoginStatusView;

    /**
     * Displayed text of the login status
     */
    private TextView mLoginStatusMessageView;

    /**
     *
     * Set's up our variables with the fields we need.
     *
     * @param savedInstanceState This is passed to the superclass
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // Set up the login form.
        mUsername = getIntent().getStringExtra(EXTRA_EMAIL); //returns null, pretty sure
        mUsernameView = (EditText) findViewById(R.id.email);
        mUsernameView.setText(
            mUsername); //because mUsername returns null, change nothing
        mPasswordView = (EditText) findViewById(R.id.password);
        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.id.login
                        || id == EditorInfo.IME_NULL) { //if they pressed login, or enter
                    attemptLogin();
                    return true;
                }
                return false;
            }
        });
        mLoginFormView = findViewById(R.id.login_form);
        mLoginStatusView = findViewById(R.id.login_status);
        mLoginStatusMessageView = (TextView) findViewById(R.id.login_status_message);
        findViewById(R.id.sign_in_button).setOnClickListener(new
        View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });
    }

    /**
     *
     * Sets up action bar
     * @param menu passed to inflate
     * @return Always success
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.login, menu);
        return true;
    }

    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    public void attemptLogin() {
        if (mAuthTask != null) {
            return;
        }
        // Reset errors.
        mUsernameView.setError(null);
        mPasswordView.setError(null);
        // Store values at the time of the login attempt.
        mUsername = mUsernameView.getText().toString();
        mPassword = mPasswordView.getText().toString();
        boolean cancel = false;
        View focusView = null;
        // Check for a valid password.
        if (TextUtils.isEmpty(mPassword)) {
            mPasswordView.setError(getString(R.string.error_field_required));
            focusView = mPasswordView;
            cancel = true;
        } else if (mPassword.length() < 4) {
            mPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordView;
            cancel = true;
        }
        // Check for a valid email address.
        if (TextUtils.isEmpty(mUsername)) {
            mUsernameView.setError(getString(R.string.error_field_required));
            focusView = mUsernameView;
            cancel = true;
        }
        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            mLoginStatusMessageView.setText(R.string.login_progress_signing_in);
            showProgress(true);
            mAuthTask = new UserLoginTask();
            mAuthTask.execute((Void) null); //this line doe lol
        }
    }

    /**
     * Shows the progress UI and hides the login form.
     * @param show determines whether or not to show progress
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(
                                    android.R.integer.config_shortAnimTime);
            mLoginStatusView.setVisibility(View.VISIBLE);
            mLoginStatusView.animate()
            .setDuration(shortAnimTime)
            .alpha(show ? 1 : 0)
            .setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mLoginStatusView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
            mLoginFormView.setVisibility(View.VISIBLE);
            mLoginFormView.animate()
            .setDuration(shortAnimTime)
            .alpha(show ? 0 : 1)
            .setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mLoginStatusView.setVisibility(show ? View.VISIBLE : View.GONE);
            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }

    /**
     * Represents an asynchronous login/registration task used to authenticate
     * the user.
     */
    public class UserLoginTask extends AsyncTask<Void, Void, Integer> {

        /**
         * Verifies if user information entered is valid
         *
         * @param params information for logging in
         * @return Integer that describes result
         */
        @Override
        protected Integer doInBackground(Void... params) {
            // TODO: attempt authentication against a network service.
            /*
             * Operate based on return codes
             * 0: successful call
             * 1: invalid username
             * 2: invalid password
             * -1: unknown error
             */
            userlogintask try {
                // Simulate network access.
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                return -1;
            }
            SQLiteHandler handler = new SQLiteHandler(LoginActivity.this);
            User user;
            try {
                user = handler.getUser(mUsername, mPassword);
                User.setCurrentUser(user);
                return 0;
            } catch (SQLiteHandler.InvalidUserException ex) {
                return 1;
            } catch (SQLiteHandler.InvalidPasswordException ex) {
                return 2;
            }
            // TODO: register the new account here.
            //            return true;
        }

        /**
         *
         * Displays status regarding attempted login
         *
         * @param returnCode Result from doInBackground()
         */

        @Override
        protected void onPostExecute(final Integer returnCode) {
            mAuthTask = null;
            showProgress(false);
            if (returnCode == 0) {
                finish();
                Intent intent = new Intent(LoginActivity.this, AccountsActivity.class);
                startActivity(intent);
            } else if (returnCode == 1) {
                mUsernameView.setError(getString(R.string.error_incorrect_username));
                mUsernameView.requestFocus();
            } else if (returnCode == 2) {
                mPasswordView.setError(getString(R.string.error_incorrect_password));
                mPasswordView.requestFocus();
            } else if (returnCode == -1) {
                mUsernameView.setError(getString(R.string.error_unknown_cause));
                mUsernameView.requestFocus();
            }
        }

        /**
         *
         * Ends log in attempt and progress animation.
         *
         */
        @Override
        protected void onCancelled() {
            mAuthTask = null;
            showProgress(false);
        }
    }
}
