package com.example.android.rss.rsssoundssimple;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.rss.rsssoundssimple.Content.EntryContent;

/**
 * Created by Luis Mierez on 6/12/2014.
 */
public class AppDetailFragment extends Fragment {

    TextView text;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.app_detail_fragment_layout, container, false);
        text = (TextView) view.findViewById(R.id.detailName);

        return view;
    }

    public void changeData(EntryContent entry) {
        text.setText(entry.getName());
    }
}
