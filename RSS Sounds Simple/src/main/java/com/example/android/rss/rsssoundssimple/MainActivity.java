package com.example.android.rss.rsssoundssimple;

import android.app.FragmentManager;
import android.content.Intent;
import android.app.Fragment;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.android.rss.rsssoundssimple.Content.EntryContent;

public class MainActivity extends ActionBarActivity implements AppListFragment.Communicator {

    AppListFragment listFragment;
    AppDetailFragment detailFragment;
    FragmentManager manager;
    DrawerLayout drawerLayout;
    ListView drawerList;
    String[] testArray = {"All", "Favorites"};
    CharSequence mTitle;
    CharSequence mDrawerTitle;

    ActionBarDrawerToggle drawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTitle = mDrawerTitle = getTitle();
        manager = getFragmentManager();
        listFragment = new AppListFragment();

        //listFragment.setCommunicator(this);
        manager.beginTransaction().replace(R.id.listFragment_container, listFragment)
                .addToBackStack(null).commit();


        getActionBar().setDisplayHomeAsUpEnabled(true);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerList = (ListView) findViewById(R.id.drawer_list);
        drawerList.setAdapter(new ArrayAdapter<String>(this, R.layout.drawer_list_layout, testArray));

        drawerList.setOnItemClickListener(new DrawerItemClickListener());

        drawerToggle = new ActionBarDrawerToggle(
                this,
                drawerLayout,
                R.drawable.ic_navigation_drawer,
                R.string.drawer_open,
                R.string.drawer_close) {
            public void onDrawerClosed(View view) {
                getActionBar().setTitle(mTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            public void onDrawerOpened(View drawerView) {
                getActionBar().setTitle(mDrawerTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

        };

        drawerLayout.setDrawerListener(drawerToggle);
        if (savedInstanceState == null) {
            selectItem(0);
        }

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
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void respond(EntryContent entry) {
        Log.d("respond", "here");
        //detailFragment = (AppDetailFragment) manager.findFragmentById(R.id.detailFragment);

        if (detailFragment!=null && detailFragment.isVisible()) {
            // Landscape orientation
            detailFragment.changeData(entry);
        } else {
            // Portrait orientation
            Intent intent = new Intent(this, DetailActivity.class);
            intent.putExtra("entry", entry);
            Log.d("MainActivity", "here");
            startActivity(intent);

        }
    }

    private class DrawerItemClickListener implements ListView.OnItemClickListener {


        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            selectItem(i);
        }
    }

        /** Swaps fragments in the main view **/
        private void selectItem(int position) {
            Fragment fragment = new AppListFragment();
            Bundle args = new Bundle();

            args.putInt("key", position);
            fragment.setArguments(args);

            manager = getFragmentManager();
            manager.beginTransaction().replace(R.id.listFragment_container, fragment).commit();

            drawerList.setItemChecked(position, true);
            setTitle(testArray[position]);
            drawerLayout.closeDrawer(drawerList);

        }

    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        getActionBar().setTitle(title);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

}