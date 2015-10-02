package com.codepath.simpletodo;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

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
        setActivityBackgroundColor();

        // Initialize variables for views
        lvItems = (ListView) findViewById(R.id.lvItems);

        // Initialize variables for items list
        items = new ArrayList<>();
        readItems();
        itemsAdapter = new ItemsAdapter(this, items);
        lvItems.setAdapter(itemsAdapter);

        setupListeners();
    }

    public void setActivityBackgroundColor() {
        View view = this.getWindow().getDecorView();
        view.setBackgroundColor(Color.parseColor("#DDDDDD"));
    }

    private void setupListeners() {
        // Remove item from items list on item long lick
        lvItems.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                items.remove(position);
                updateData();
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
                i.putExtra("dueDate", item.getDueDate());
                startActivityForResult(i, EDIT_REQUEST_CODE);
            }
        });
    }

    public void addNewItem() {
        Intent i = new Intent(MainActivity.this, AddItemActivity.class);
        startActivityForResult(i, ADD_REQUEST_CODE);
    }

    public void trashSelectedItems() {
        Iterator<ToDoItem> itemsList = items.iterator();
        while (itemsList.hasNext())
        {
            ToDoItem item = itemsList.next();
            if (item.isCompleted())
                itemsList.remove();
        }
        updateData();
    }

    public void checkAllItems() {
        for (int i=0; i<items.size(); i++) {
            items.get(i).markSelected();
        }
        updateData();
    }

    public void updateData() {
        itemsAdapter.notifyDataSetChanged();
        writeItems();
    }

    public void readItems() {
        File filesDir = getFilesDir();
        File todoFile = new File(filesDir, "todo.txt");
        try {
            items = new ArrayList<ToDoItem>();
            ArrayList<String> data = new ArrayList<String>(FileUtils.readLines(todoFile));
            for (String item : data) {
                String[] itemInfo = item.split(";");
                boolean isCompleted = Boolean.valueOf(itemInfo[0]);
                String title = itemInfo[1];
                String priority = itemInfo[2];
                String dueDate = itemInfo[3];
                items.add(new ToDoItem(title, priority, dueDate, isCompleted));

            }
        } catch (IOException e) {
            items = new ArrayList<ToDoItem>();
        }
    }

    public void writeItems() {
        File filesDir = getFilesDir();
        File todoFile = new File(filesDir, "todo.txt");
        try {
            ArrayList<String> itemsInfo = new ArrayList<String>();
            for (ToDoItem item : items) {
                itemsInfo.add(item.getDataInfo());
            }
            FileUtils.writeLines(todoFile, itemsInfo);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK && requestCode == ADD_REQUEST_CODE) {
            // Retrieve item info and create new item
            String title = data.getExtras().getString("title");
            String priority = data.getExtras().getString("priority");
            String dueDate = data.getExtras().getString("dueDate");
            ToDoItem newItem = new ToDoItem(title, priority, dueDate, false);

            // Add new item
            items.add(newItem);
            updateData();
        }
        else if (resultCode == RESULT_OK && requestCode == EDIT_REQUEST_CODE) {
            // Retrieve item info and create new item
            String title = data.getExtras().getString("title");
            String priority = data.getExtras().getString("priority");
            String dueDate = data.getExtras().getString("dueDate");
            int position = data.getExtras().getInt("position");

            // Update item info
            ToDoItem item = items.get(position);
            item.setTitle(title);
            item.setPriority(priority);
            item.setDueDate(dueDate);
            updateData();
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
        switch (id) {
            case R.id.add:
                addNewItem();
                return true;
            case R.id.trash:
                trashSelectedItems();
                return true;
            case R.id.check:
                checkAllItems();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
