package com.example.android.rss.rsssoundssimple;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v7.widget.ShareActionProvider;

import com.example.android.rss.rsssoundssimple.Content.EntryContent;

/**
 * Created by Luis Mierez on 6/12/2014.
 */
public class DetailActivity extends ActionBarActivity {

    private ShareActionProvider mShareActionProvider;
    SharedPreferences sharedPreferences;
    EntryContent entry;
    SharedPreferences.Editor shareEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_activity_layout);

        ActionBar actionBar = getSupportActionBar();
        Intent intent=getIntent();
        entry = intent.getParcelableExtra("entry");

        actionBar.setTitle(entry.getName());
        AppDetailFragment detailFragment = (AppDetailFragment) getFragmentManager().findFragmentById(R.id.detailFragment);
        detailFragment.changeData(entry);

        sharedPreferences = getSharedPreferences("favorites", Context.MODE_PRIVATE);
        shareEdit = sharedPreferences.edit();



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.detail_activity_menu, menu);
        MenuItem shareItem = menu.findItem(R.id.action_share);
        mShareActionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(shareItem);
        mShareActionProvider.setShareIntent(getDefaultIntent());
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        Log.d("save", String.valueOf(sharedPreferences.contains(entry.getName())));
        MenuItem favorite = menu.findItem(R.id.favorites);
        MenuItem not_favorite = menu.findItem(R.id.not_favorite);
        if (sharedPreferences.getBoolean(entry.getName(), false)) {
            favorite.setEnabled(true).setVisible(true);
            not_favorite.setEnabled(false).setVisible(false);
        } else {
            not_favorite.setEnabled(true).setVisible(true);
            favorite.setEnabled(false).setVisible(false);
        }
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        SharedPreferences.Editor shareEdit = sharedPreferences.edit();
        switch(item.getItemId()) {
            case R.id.not_favorite:
                shareEdit.putBoolean(entry.getName(), true).commit();
                invalidateOptionsMenu();
                return true;
            case R.id.favorites:
                //shareEdit.putBoolean(entry.getName(), false).commit();
                shareEdit.remove(entry.getName()).commit();
                invalidateOptionsMenu();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private Intent getDefaultIntent() {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_SUBJECT, entry.getName());
        intent.putExtra(Intent.EXTRA_TEXT, entry.getLink());
        return intent;
    }

}
