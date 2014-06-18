package com.example.android.rss.rsssoundssimple.Adapter;

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

    private class ViewHolder() {

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.list_entry_layout, parent, false);
        TextView name = (TextView) rowView.findViewById(R.id.appName);
        TextView price = (TextView) rowView.findViewById(R.id.appPrice);
        ImageView image = (ImageView) rowView.findViewById(R.id.appImage);

        name.setText(values.get(position).getName()); // Set the name of the app
        Log.d("APP Price", String.valueOf(values.get(position).getPrice("amount")==null));
        price.setText(values.get(position).getPrice("amount") + " " + values.get(position).getPrice("currency")); // Set the price of the app

        image.setImageBitmap(values.get(position).getImage(53)); // Set the image of the app

        return rowView;
    }
}
