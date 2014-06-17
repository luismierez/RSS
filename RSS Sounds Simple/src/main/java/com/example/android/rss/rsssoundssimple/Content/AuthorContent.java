package com.example.android.rss.rsssoundssimple.Content;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Luis Mierez on 6/11/2014.
 */
public class AuthorContent {
    //private JSONObject author;
    private JSONObject name;
    private JSONObject uri;

    public AuthorContent(JSONObject object) {
        try {
            //author = object.getJSONObject("author");
            name = object.getJSONObject("name");
            uri = object.getJSONObject("uri");

        } catch (JSONException e) {
            Log.d("AuthorContent", "There was an error on AuthorContent constructor");
            e.printStackTrace();
        }
    }

    public String getName() {
        String _name = null;
        try {
            _name = name.getString("label");

        } catch (JSONException e) {
            Log.d("AuthorContent", "Error in getName");
            e.printStackTrace();
        }
        return _name;
    }

    public String getURI() {
        String _uri = null;
        try {
            _uri = uri.getString("uri");
        } catch (JSONException e) {
            Log.d("AuthorContent", "Error in getURI");
            e.printStackTrace();
        }
        return _uri;
    }

}
