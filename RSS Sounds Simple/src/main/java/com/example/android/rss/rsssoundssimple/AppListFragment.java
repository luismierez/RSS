package com.example.android.rss.rsssoundssimple;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

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

/**
 * Created by Luis Mierez on 6/12/2014.
 */
public class AppListFragment extends Fragment implements AdapterView.OnItemClickListener {

    ListView list;
    JSONParser jsonParser = new JSONParser();
    List<EntryContent> apps = new ArrayList<EntryContent>();
    Communicator communicator;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        new GetRss().execute();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.app_list_fragment_layout, container, false);

        list = (ListView) view.findViewById(R.id.appList);
        list.setOnItemClickListener(this);
        return view;
    }

    public void setCommunicator(Communicator communicator) {
        this.communicator = communicator;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        communicator.respond(apps.get(i));
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

            JSONObject jsonObject = jsonParser.makeHttpRequest("http://ax.itunes.apple.com/WebObjects/MZStoreServices.woa/ws/RSS/topgrossingapplications/sf=143441/limit=25/json", "GET", params);

            try {
                JSONObject feed = jsonObject.getJSONObject("feed");
                AuthorContent author = new AuthorContent(feed.getJSONObject("author"));
                JSONArray entries = feed.getJSONArray("entry");
                for(int i = 0; i < entries.length(); i++) {
                    // Retrieve JSON Object and create an entry with it
                    EntryContent entry = new EntryContent(entries.getJSONObject(i));
                    apps.add(entry);
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String url) {

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
