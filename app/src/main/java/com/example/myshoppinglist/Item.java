package com.example.myshoppinglist;


import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class Item {

    private String mTitle;
    public String getTitle() {
        return mTitle;
    }
    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }
    private static final String JSON_TITLE = "title";
    public Boolean isChecked = false;


    public Item(JSONObject jo) throws JSONException {
        mTitle = jo.getString(JSON_TITLE);
    }

    public Item(){}

    public JSONObject convertToJSON() throws JSONException {

        JSONObject jo = new JSONObject();

        jo.put(JSON_TITLE, mTitle);

        return jo;
    }

}
