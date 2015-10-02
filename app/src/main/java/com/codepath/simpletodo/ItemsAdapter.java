package com.codepath.simpletodo;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by truongmm on 9/26/15.
 */

public class ItemsAdapter extends ArrayAdapter<ToDoItem> {
    private final Context context;

    public ItemsAdapter(Context context, ArrayList<ToDoItem> items) {
        super(context, 0, items);
        this.context = context;
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
        TextView tvTitle = (TextView) convertView.findViewById(R.id.tvTitle);
        TextView tvPriority = (TextView) convertView.findViewById(R.id.tvPriority);
        TextView tvDueDate = (TextView) convertView.findViewById(R.id.tvDueDate);
        final CheckBox checkbox = (CheckBox) convertView.findViewById(R.id.checkbox);
        checkbox.setTag(position);
        checkbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = (int) checkbox.getTag();
                getItem(position).toggleCompletion();
                MainActivity mainActivity = (MainActivity) context;
                mainActivity.writeItems();
            }
        });

        // Populate the data into the template view using the data object
        tvTitle.setText(item.getTitle());
        tvPriority.setText(item.getPriority());
        tvPriority.setTextColor(Color.parseColor(item.getPriorityColor()));
        checkbox.setChecked(item.isCompleted());
        tvDueDate.setText(item.getDueDate());

        // Return the completed view to render on screen
        return convertView;
    }

}
