package com.example.android.rss.rsssoundssimple;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;

import com.example.android.rss.rsssoundssimple.Content.EntryContent;

/**
 * Created by Luis Mierez on 6/12/2014.
 */
public class DetailActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_activity_layout);
        Intent intent=getIntent();
        EntryContent entry = intent.getParcelableExtra("entry");
        Log.d("DetailActivity", "here");
        AppDetailFragment detailFragment = (AppDetailFragment) getFragmentManager().findFragmentById(R.id.detailFragment);
        detailFragment.changeData(entry);
    }
}
