package com.codepath.simpletodo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class EditItemActivity extends AppCompatActivity {
    // Declare variables for item
    private String item;
    private int position;

    // Declare variables for views
    private EditText etEditItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);

        // Initialize variables for views
        etEditItem = (EditText) findViewById(R.id.etEditItem);

        // Initialize variables for item
        item = getIntent().getStringExtra("item");
        position = getIntent().getIntExtra("position", 0);
        etEditItem.setText(item);
        etEditItem.setSelection(item.length());
        etEditItem.requestFocus();
    }

    public void onSaveItem(View view) {
        // Get new value for item
        String itemValue = etEditItem.getText().toString();

        // Prepare data intent
        Intent data = new Intent();

        // Pass item info back as a result
        data.putExtra("position", position);
        data.putExtra("itemValue", itemValue);

        // Return the data
        setResult(RESULT_OK, data);

        // Take back to previous activity
        finish();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_edit_item, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
