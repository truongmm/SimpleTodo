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

public class EditItemActivity extends AppCompatActivity {
    // Declare variables for item
    private String title;
    private String priority;
    private int position;

    // Declare variables for views
    private EditText etEditTitle;
    private Spinner dropdown;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);

        // Initialize variables for views
        etEditTitle = (EditText) findViewById(R.id.etEditTitle);
        dropdown = (Spinner) findViewById(R.id.spinnerPriority);
        String[] priorities = new String[]{"LOW", "MED", "HIGH"};
        ArrayAdapter<String> dropdownAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, priorities);
        dropdown.setAdapter(dropdownAdapter);

        // Initialize variables for item
        title = getIntent().getStringExtra("title");
        priority = getIntent().getStringExtra("priority");
        position = getIntent().getIntExtra("position", 0);
        etEditTitle.setText(title);
        etEditTitle.setSelection(title.length());
        etEditTitle.requestFocus();
        dropdown.setSelection(dropdownAdapter.getPosition(priority));
    }

    public void onSaveItem(View view) {
        // Get new value for item
        String newTitle = etEditTitle.getText().toString();
        String newPriority = dropdown.getSelectedItem().toString();

        // Prepare data intent
        Intent data = new Intent();

        // Pass item info back as a result
        data.putExtra("title", newTitle);
        data.putExtra("priority", newPriority);
        data.putExtra("position", position);

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
