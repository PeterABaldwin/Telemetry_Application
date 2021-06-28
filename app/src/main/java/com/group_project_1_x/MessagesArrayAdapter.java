package com.group_project_1_x;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class MessagesArrayAdapter extends ArrayAdapter<StoredMessages> {

    public MessagesArrayAdapter(Context context, ArrayList<StoredMessages> users) {

        super(context, 0, users);

    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // Get the data item for this position

        StoredMessages storedMessages = getItem(position);

        // Check if an existing view is being reused, otherwise inflate the view
        convertView = LayoutInflater.from(getContext()).inflate(R.layout.sent_message_item, parent, false);

        if (convertView == null) {

            convertView = LayoutInflater.from(getContext()).inflate(R.layout.sent_message_item, parent, false);

        }

        // Lookup view for data population

        TextView sentTextView = (TextView) convertView.findViewById(R.id.sent_message_text);
        TextView receivedTextView = (TextView) convertView.findViewById(R.id.received_message_text);

        // Populate the data into the template view using the data object
        if(storedMessages.sentText!= null){
            sentTextView.setText(storedMessages.sentText);
            receivedTextView.setVisibility(View.INVISIBLE);
        }
        if (storedMessages.receivedText != null){
            receivedTextView.setText(storedMessages.receivedText);
            sentTextView.setVisibility(View.INVISIBLE);
        }
        // Return the completed view to render on screen

        return convertView;

    }

}