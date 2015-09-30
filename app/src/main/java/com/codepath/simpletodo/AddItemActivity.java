package com.codepath.simpletodo;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class AddItemActivity extends AppCompatActivity {

    // Declare variables for views
    private EditText etTitle;
    private Spinner dropdown;
    private EditText etDueDate;
    final Calendar calendar = Calendar.getInstance();
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("EEE, MM/dd/yyyy", java.util.Locale.getDefault());

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
        setCurrentDate();
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

    public void setCurrentDate() {
        etDueDate = (EditText) findViewById(R.id.etDueDate);
        etDueDate.setFocusable(false);
        etDueDate.setText(dateFormat.format(calendar.getTime()));

        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, monthOfYear);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                etDueDate.setText(dateFormat.format(calendar.getTime()));
            }
        };
        etDueDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                new DatePickerDialog(AddItemActivity.this, date, year, month, day).show();
            }
        });
    }

    public void onSaveNewItem(View view) {
        // Get data for item
        String title = etTitle.getText().toString();
        String priority = dropdown.getSelectedItem().toString();
        String dueDate = etDueDate.getText().toString();

        // Prepare data intent
        Intent data = new Intent();

        // Pass item info back as a result
        data.putExtra("title", title);
        data.putExtra("priority", priority);
        data.putExtra("dueDate", dueDate);

        // Return the data
        setResult(RESULT_OK, data);

        // Take back to previous activity
        finish();
    }
}
