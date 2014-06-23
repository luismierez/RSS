package com.example.android.rss.rsssoundssimple;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.android.rss.rsssoundssimple.Adapter.EntryAdapter;
import com.example.android.rss.rsssoundssimple.Content.AuthorContent;
import com.example.android.rss.rsssoundssimple.Content.EntryContent;
import com.example.android.rss.rsssoundssimple.JSONParser.JSONParser;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Luis Mierez on 6/12/2014.
 */
public class AppListFragment extends Fragment implements AdapterView.OnItemClickListener {

    //ListView list;
    AbsListView list;
    JSONParser jsonParser = new JSONParser();
    List<EntryContent> apps = new ArrayList<EntryContent>();
    Communicator communicator;
    int listId = 0;
    private SharedPreferences sharedPreferences;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            communicator = (Communicator) activity;
            sharedPreferences = getActivity().getSharedPreferences("favorites", Context.MODE_PRIVATE);
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement OnArticleSelectedListener");
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        listId = getArguments().getInt("key");
        new GetRss().execute();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.app_list_fragment_layout, container, false);
        list = (AbsListView) view.findViewById(R.id.appList);
        //list = (ListView) view.findViewById(R.id.appList);
        TextView emptyText = (TextView) view.findViewById(android.R.id.empty);
        list.setEmptyView(emptyText);
        list.setOnItemClickListener(this);
        return view;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        if (getActivity()!=null) {
            communicator.respond(apps.get(i));
            list.setItemChecked(i, true);
        }
    }

    public interface Communicator {
        public void respond(EntryContent entry);
    }

    private class GetRss extends AsyncTask<String, Integer, String> {

        @Override
        protected void onPreExecute() {

        }

        @Override
        protected String doInBackground(String... strings) {
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            if (listId == 0) {
                // get All items
                JSONObject jsonObject = jsonParser.makeHttpRequest("http://ax.itunes.apple.com/WebObjects/MZStoreServices.woa/ws/RSS/topgrossingapplications/sf=143441/limit=25/json", "GET", params);

                try {
                    JSONObject feed = jsonObject.getJSONObject("feed");
                    AuthorContent author = new AuthorContent(feed.getJSONObject("author"));
                    JSONArray entries = feed.getJSONArray("entry");
                    for (int i = 0; i < entries.length(); i++) {
                        // Retrieve JSON Object and create an entry with it
                        EntryContent entry = new EntryContent(entries.getJSONObject(i));

                        apps.add(entry);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                return null;
            } else {
                // get favorite items
                Map<String, ?> keys = sharedPreferences.getAll();
                JSONObject jsonObject = jsonParser.makeHttpRequest("http://ax.itunes.apple.com/WebObjects/MZStoreServices.woa/ws/RSS/topgrossingapplications/sf=143441/limit=25/json", "GET", params);

                for (Map.Entry<String, ?> key : keys.entrySet()) {

                    try {
                        JSONObject feed = jsonObject.getJSONObject("feed");
                        AuthorContent author = new AuthorContent(feed.getJSONObject("author"));
                        JSONArray entries = feed.getJSONArray("entry");
                        for (int i = 0; i < entries.length(); i++) {
                            // Retrieve JSON Object and create an entry with it
                            EntryContent entry = new EntryContent(entries.getJSONObject(i));
                            if (key.getKey().equals(entry.getName()) && key.getValue().toString().equals("true")) {
                                apps.add(entry);
                                break;
                            }
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                }

                return null;
            }
        }

        @Override
        protected void onPostExecute(String url) {

            if (getActivity()!=null) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        EntryAdapter adapter = new EntryAdapter(getActivity(), apps);
                        list.setAdapter(adapter);
                    }
                });
            }
        }
    }
}
