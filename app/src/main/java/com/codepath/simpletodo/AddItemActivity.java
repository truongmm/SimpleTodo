package com.codepath.simpletodo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

public class AddItemActivity extends AppCompatActivity {

    // Declare variables for views
    private EditText etTitle;
    private Spinner dropdown;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        // Initialize variables for views
        etTitle = (EditText) findViewById(R.id.etTitle);
        dropdown = (Spinner) findViewById(R.id.spinnerPriority);
        String[] priorities = new String[]{"LOW", "MED", "HIGH"};
        ArrayAdapter<String> dropdownAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, priorities);
        dropdown.setAdapter(dropdownAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_item, menu);
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

    public void onSaveNewItem(View view) {
        // Get data for item
        String title = etTitle.getText().toString();
        String priority = dropdown.getSelectedItem().toString();

        // Prepare data intent
        Intent data = new Intent();

        // Pass item info back as a result
        data.putExtra("title", title);
        data.putExtra("priority", priority);

        // Return the data
        setResult(RESULT_OK, data);

        // Take back to previous activity
        finish();
    }
}
