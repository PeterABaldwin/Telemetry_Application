package com.group_project_1_x;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.content.Intent;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import java.util.ArrayList;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Send extends Fragment {

    Global g = com.group_project_1_x.Global.getInstance();
    String MESSAGES = "messagess send: ";
    EditText mobileno, message;
    Button sendsms;
    public int sent = 0;
    // String array to store messages temporarily
    ArrayList <String> all_messages = new ArrayList<>();
    ListView messagesList;
    ArrayAdapter<String> messagesListAdapter;

    ArrayList<StoredMessages> arrayOMessages = new ArrayList<StoredMessages>();
    MessagesArrayAdapter messagesArrayAdapter;

    Button sendButton;
    EditText editText;
    LinearLayout linearLayout;
    Received r = new Received();

    private ViewGroup root;

    int id;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        root = (ViewGroup) inflater.inflate(R.layout.fragment_send, container, false);

        messagesArrayAdapter= new MessagesArrayAdapter(getActivity(), arrayOMessages);

        linearLayout = (LinearLayout) root.findViewById(R.id.sent_message_view);

        sendButton = (Button) root.findViewById(R.id.sendButton);
        editText = (EditText) root.findViewById(R.id.sendEditText);
        // Ad adapter to format the messages list
        id = R.id.sent_message_text;

        messagesListAdapter = new ArrayAdapter<String>(getContext(), R.layout.sent_message_item, id, all_messages);

        //messagesListAdapter = new ArrayAdapter<String>(getActivity(), onAddField(0, "test2"), id, all_messages);

        messagesList = (ListView) root.findViewById(R.id.messages_list);

        try {
        if(g.getAllMessages().size() != 0){
            //display the messages, adding them one by one untill size is reached
            //possibly use id for counting

                for(int i = 0; i<g.getAllMessages().size(); i++){
                    JSONArray ja = new JSONArray("[" + g.getAllMessages().get(i) + "]");
                    JSONObject jo = ja.getJSONObject(0);
                    if (jo.optString("from").matches("IVA")) {//right side
                        StoredMessages storedMessages = new StoredMessages(jo.optString("message"), null);
                        messagesArrayAdapter.add(storedMessages);
                        messagesList.setAdapter(messagesArrayAdapter);
                        editText.setText("");
                    } else if (jo.optString("from").matches("EVA")) {//left side
                        StoredMessages storedMessages = new StoredMessages(null, jo.optString("message"));
                        messagesArrayAdapter.add(storedMessages);
                        messagesList.setAdapter(messagesArrayAdapter);
                        editText.setText("");
                    } else {

                    }
                }
            }
        }
        catch(Exception e){
            Log.i(MESSAGES, e.getMessage());
        }
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Incrementing the count of sent messages
                sent +=1;
                // Adding the message to the string array
                StoredMessages storedMessages = new StoredMessages(editText.getText().toString(), null);
                messagesArrayAdapter.add(storedMessages);
                messagesList.setAdapter(messagesArrayAdapter);
                editText.setText("");

            }
        });
        messagesList.setAdapter(messagesArrayAdapter);
        return root;
    }

    TextView onAddField(int i, String s) {
        TextView tv = new TextView(getContext());
        tv.setText(s);
        tv.setId(i);
        tv.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        tv.setTextSize(17);
        Drawable d = getResources().getDrawable(R.drawable.sent_text_bubble);
        tv.setBackground(d);
        tv.setTextColor(Color.parseColor("#ffffff"));
        Log.i("messages", ""+i+" : "+(i%2));
        if((i%2)==1) {
            tv.setGravity(Gravity.END);
        }else{
            tv.setGravity(Gravity.START);
        }

        return tv;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    public void sendData (String sendText){
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, sendText);
        sendIntent.setType("text/plain");//change to "text/json"

        Intent shareIntent = Intent.createChooser(sendIntent, null);
        startActivity(shareIntent);
    }

    public class MessageListAdapter{
        boolean sent;
        boolean received;

        MessageListAdapter(){
            sent = false;
            received = false;
        }

        public void appendToList(boolean sent, boolean received, String text){
            if(received){
                // Append to Left side of adapter

            }
            if (sent){
                // Append to right side of adapter
            }
        }
    }

}
