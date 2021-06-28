package com.group_project_1_x;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class StoredMessages {

    // Constructor to convert JSON object into a Java class instance
    public String sentText;
    public String receivedText;

    public StoredMessages(String sent, String received){

        this.sentText = sent;
        this.receivedText = received;


//        try {
//
//            this.sentText = object.getString("sent");
//
//            this.receivedText = object.getString("received");
//
//        } catch (JSONException e) {
//
//            e.printStackTrace();
//
//        }

    }



    // Factory method to convert an array of JSON objects into a list of objects

    // User.fromJson(jsonArray);

//    public static ArrayList<StoredMessages> fromJson(JSONArray jsonObjects) {
//
//        ArrayList<StoredMessages> storedMessages = new ArrayList<StoredMessages>();
//
//        for (int i = 0; i < jsonObjects.length(); i++) {
//
//            try {
//
//                storedMessages.add(new StoredMessages(jsonObjects.getJSONObject(i)));
//
//            } catch (JSONException e) {
//
//                e.printStackTrace();
//
//            }
//
//        }
//
//        return storedMessages;
//
//    }

}