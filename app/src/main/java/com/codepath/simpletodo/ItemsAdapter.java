package com.codepath.simpletodo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by truongmm on 9/26/15.
 */

public class ItemsAdapter extends ArrayAdapter<ToDoItem> {
    public ItemsAdapter(Context context, ArrayList<ToDoItem> items) {
        super(context, 0, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        ToDoItem item = getItem(position);

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.to_do_item, parent, false);
        }
        // Lookup view for data population
        TextView tvTitle = (TextView) convertView.findViewById(R.id.tvTitleLabel);
        TextView tvPriority = (TextView) convertView.findViewById(R.id.tvPriority);

        // Populate the data into the template view using the data object
        tvTitle.setText(item.title);
        tvPriority.setText(item.priority);

        // Return the completed view to render on screen
        return convertView;
    }

}
