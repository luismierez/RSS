package com.example.android.rss.rsssoundssimple.Adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.rss.rsssoundssimple.Content.EntryContent;
import com.example.android.rss.rsssoundssimple.R;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

/**
 * Created by Luis Mierez on 6/11/2014.
 */
public class EntryAdapter extends ArrayAdapter<EntryContent> {
    private final Context context;
    private final List<EntryContent> values;

    public EntryAdapter(Context context, List<EntryContent> values) {
        super(context, R.layout.list_entry_layout, values);
        this.context = context;
        this.values = values;
    }

    private static class ViewHolder {
        TextView name;
        TextView price;
        ImageView image;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;
        ViewHolder holder;
        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.list_entry_layout, null);
            holder = new ViewHolder();
            holder.name = (TextView) v.findViewById(R.id.appName);
            holder.price = (TextView) v.findViewById(R.id.appPrice);
            holder.image = (ImageView) v.findViewById(R.id.appImage);
            v.setTag(holder);

        } else {
            holder = (ViewHolder)v.getTag();
        }

        EntryContent entry = values.get(position);
        if (entry != null) {
            holder.name.setText(entry.getName());
            holder.price.setText(entry.getPrice("amount"));
            holder.image.setImageBitmap(entry.getImage(100));

        }

        return v;
    }
}
