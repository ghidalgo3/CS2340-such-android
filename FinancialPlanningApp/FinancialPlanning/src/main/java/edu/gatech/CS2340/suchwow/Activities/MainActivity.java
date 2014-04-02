package edu.gatech.CS2340.suchwow.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

import edu.gatech.CS2340.suchwow.R;

    /**
    * The MainActivity takes users to LoginActivity or RegisterActivity
    */
public class MainActivity extends Activity {

    /**
     * Sets up content view and creates bundle
     * @param savedInstanceState Just passed to the superclass
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

        /**
         * Takes user to LoginActivity when login is pressed.
         * @param view Not used
         */
    public void loginPressed(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

        /**
         * Takes user to RegisterActivity when register is pressed.
         * @param view not used
         */
    public void registerPressed(View view) {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

        /**
         * Sets up action bar
         * @param menu Passed to inflate
         * @return Always success
         */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

}
