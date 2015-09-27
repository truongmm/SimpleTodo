package com.codepath.simpletodo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private final int ADD_REQUEST_CODE = 1;
    private final int EDIT_REQUEST_CODE = 2;

    // Declare variables for items list
    private ItemsAdapter itemsAdapter;
    private ArrayList<ToDoItem> items;

    // Declare variables for views
    private EditText etNewItem;
    private ListView lvItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize variables for views
        lvItems = (ListView) findViewById(R.id.lvItems);

        // Initialize variables for items list
        items = new ArrayList<>();
        readItems();
        itemsAdapter = new ItemsAdapter(this, items);
        lvItems.setAdapter(itemsAdapter);

        setupListeners();
    }

    private void setupListeners() {
        // Remove item from items list on item long lick
        lvItems.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                items.remove(position);
                itemsAdapter.notifyDataSetChanged();

                writeItems();
                return true;
            }
        });

        // Take to Edit Item Activity on item click
        lvItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(MainActivity.this, EditItemActivity.class);
                ToDoItem item = items.get(position);
                i.putExtra("title", item.getTitle());
                i.putExtra("priority", item.getPriority());
                i.putExtra("position", position);
                startActivityForResult(i, EDIT_REQUEST_CODE);
            }
        });
    }

    public void onAddNewItem(View view) {
        Intent i = new Intent(MainActivity.this, AddItemActivity.class);
        startActivityForResult(i, ADD_REQUEST_CODE);
    }

    public void readItems() {
        File filesDir = getFilesDir();
        File todoFile = new File(filesDir, "todo.txt");
        try {
            items = new ArrayList<ToDoItem>();
            ArrayList<String> data = new ArrayList<String>(FileUtils.readLines(todoFile));
            for (String item : data) {
                String[] itemInfo = item.split(";");
                String title = itemInfo[0];
                String priority = itemInfo[1];
                items.add(new ToDoItem(title, priority));
            }
        } catch (IOException e) {
            items = new ArrayList<ToDoItem>();
        }
    }

    public void writeItems() {
        File filesDir = getFilesDir();
        File todoFile = new File(filesDir, "todo.txt");
        try {
            ArrayList<String> titles = new ArrayList<String>();
            for (ToDoItem item : items) {
                titles.add(item.getDataInfo());
            }
            FileUtils.writeLines(todoFile, titles);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK && requestCode == ADD_REQUEST_CODE) {
            // Retrieve item info and create new item
            String title = data.getExtras().getString("title");
            String priority = data.getExtras().getString("priority");
            ToDoItem newItem = new ToDoItem(title, priority);

            // Add new item
            items.add(newItem);

            itemsAdapter.notifyDataSetChanged();
            writeItems();
        }
        else if (resultCode == RESULT_OK && requestCode == EDIT_REQUEST_CODE) {
            // Retrieve item info and create new item
            String title = data.getExtras().getString("title");
            String priority = data.getExtras().getString("priority");
            int position = data.getExtras().getInt("position");

            // Update item info
            ToDoItem item = items.get(position);
            item.setTitle(title);
            item.setPriority(priority);

            itemsAdapter.notifyDataSetChanged();
            writeItems();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
