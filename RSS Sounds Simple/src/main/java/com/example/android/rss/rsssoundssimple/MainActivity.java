package com.example.android.rss.rsssoundssimple;

import android.app.FragmentManager;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.android.rss.rsssoundssimple.Content.AuthorContent;
import com.example.android.rss.rsssoundssimple.Content.EntryContent;
import com.example.android.rss.rsssoundssimple.JSONParser.JSONParser;
import com.example.android.rss.rsssoundssimple.Listeners.DrawerItemClickListener;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class MainActivity extends ActionBarActivity implements AppListFragment.Communicator {

    AppListFragment listFragment;
    AppDetailFragment detailFragment;
    FragmentManager manager;
    DrawerLayout drawerLayout;
    ListView drawerList;
    String[] testArray = {"1", "2", "3", "4"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        manager = getFragmentManager();
        listFragment = (AppListFragment) manager.findFragmentById(R.id.listFragment);
        listFragment.setCommunicator(this);

        //drawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        //drawerList = (ListView) findViewById(R.id.drawerList);
        //drawerList.setAdapter(new ArrayAdapter<String>(this, R.layout.drawer_list_layout, testArray));

        //drawerList.setOnItemClickListener(new DrawerItemClickListener());




    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void respond(EntryContent entry) {
        detailFragment = (AppDetailFragment) manager.findFragmentById(R.id.detailFragment);

        if (detailFragment!=null && detailFragment.isVisible()) {
            // Landscape orientation
            detailFragment.changeData(entry);
        } else {
            // Portrait orientation
            Intent intent = new Intent(this, DetailActivity.class);
            intent.putExtra("entry", entry);
            startActivity(intent);
            Log.d("MainActivity", "here");
        }
    }
}


