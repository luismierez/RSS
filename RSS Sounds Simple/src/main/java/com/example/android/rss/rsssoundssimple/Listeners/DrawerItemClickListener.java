package com.example.android.rss.rsssoundssimple.Listeners;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

/**
 * Created by luismierez on 6/20/14.
 */
public class DrawerItemClickListener implements ListView.OnItemClickListener {


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        selectItem(i);
    }

    /** Swaps fragments in the main view **/
    private void selectItem(int position) {

    }
}
