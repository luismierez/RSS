package com.example.android.rss.rsssoundssimple.Content;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Luis Mierez on 6/11/2014.
 */
public class EntryContent implements Parcelable {


    private String name;
    private String image_53;
    private String image_75;
    private String image_100;
    private String summary;
    private String price_label;
    private String price_amount;
    private String price_currency;
    private String content_term;
    private String content_label;
    private String rights;
    private String title;
    private String link;
    private String id;
    private String artist;
    private String category;
    private String releaseDate;


    public EntryContent(JSONObject object) {
        try {
            name = object.getJSONObject("im:name").getString("label");

            JSONArray _image = object.getJSONArray("im:image");
            image_53 = _image.getJSONObject(0).getString("label");
            image_75 = _image.getJSONObject(1).getString("label");
            image_100 = _image.getJSONObject(2).getString("label");

            summary = object.getJSONObject("summary").getString("label");

            JSONObject price_attributes = object.getJSONObject("im:price").getJSONObject("attributes");
            price_label = object.getJSONObject("im:price").getString("label");
            price_amount = price_attributes.getString("amount");
            price_currency = price_attributes.getString("currency");

            JSONObject content_type = object.getJSONObject("im:contentType").getJSONObject("attributes");
            content_label = content_type.getString("label");
            content_term = content_type.getString("term");
            rights = object.getJSONObject("rights").getString("label");
            title = object.getJSONObject("title").getString("label");
            link = object.getJSONObject("link").getJSONObject("attributes").getString("href");
            //id = object.getJSONObject("id");
            //artist = object.getJSONObject("im:artist");
            //category = object.getJSONObject("category");
            //releaseDate = object.getJSONObject("im:releaseDate");
        } catch (JSONException e) {
            Log.d("EntryContent", "Error in constructor");
            e.printStackTrace();
        }
    }

    public String getName() {
        return name;
    }

    public Bitmap getImage(int height) {
        String urlImage = null;
        Bitmap image = null;
        switch (height) {
            case 53:
                urlImage = image_53;
                image = null;
                try {
                    InputStream in = new URL(urlImage).openStream();
                    image = BitmapFactory.decodeStream(in);
                } catch (Exception e) {
                    Log.d("Error 53", "here");
                    e.printStackTrace();
                }
                return image;
            case 75:
                urlImage = image_75;
                image = null;
                try {
                    InputStream in = new URL(urlImage).openStream();
                    image = BitmapFactory.decodeStream(in);
                } catch (Exception e) {
                    Log.d("Error 75", e.getMessage());
                    e.printStackTrace();
                }
                return image;
            case 100:
                urlImage = image_100;
                image = null;
                try {
                    InputStream in = new URL(urlImage).openStream();
                    image = BitmapFactory.decodeStream(in);
                } catch (Exception e) {
                    Log.d("Error 100", e.getMessage());
                    e.printStackTrace();
                }
                return image;
            default:
                return null;
        }
    }

    public String getSummary() {
        return summary;
    }

    public String getPrice(String what) {
        if (what.equals(price_label)) {
            return price_label;
        } else if (what.equals(price_amount)) {
            return price_amount;
        } else if (what.equals(price_currency)) {
            return price_currency;
        } else
            return null;
    }

    public String getContentType(String arg) {
        if (arg.equals(content_term)) {
            return content_term;
        } else if (arg.equals(content_label)) {
            return content_label;
        } else
            return null;
    }

    public String getRights() {
        return rights;
    }

    public String getTitle() {
        return title;
    }

    public String getLink() {
        return link;
    }

    public EntryContent(Parcel in) {
        readFromParcel(in);
    }

    //TODO: getId, getArtist, getCategory, and getReleaseDate

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        //parcel.writeList(image);
        parcel.writeString(image_53);
        parcel.writeString(image_75);
        parcel.writeString(image_100);
        parcel.writeString(summary);
        parcel.writeString(price_label);
        parcel.writeString(price_amount);
        parcel.writeString(price_currency);
        parcel.writeString(content_term);
        parcel.writeString(content_label);
        parcel.writeString(rights);
        parcel.writeString(title);
        parcel.writeString(link);
    }

    public void readFromParcel(Parcel source) {
        //Log.d("EntryContent", "0");
        name = source.readString();
        //Log.d("EntryContent", "1");
        //source.readStringList(image);
        image_53 = source.readString();
        image_75 = source.readString();
        image_100 = source.readString();
        //Log.d("EntryContent", "2");
        summary = source.readString();
        //Log.d("EntryContent", "3");
        price_label = source.readString();
        //Log.d("EntryContent", "4");
        price_amount = source.readString();
        //Log.d("EntryContent", "5");
        price_currency = source.readString();
        //Log.d("EntryContent", "6");
        content_term = source.readString();
        //Log.d("EntryContent", "7");
        content_label = source.readString();
        //Log.d("EntryContent", "8");
        rights = source.readString();
       //Log.d("EntryContent", "9");
        title = source.readString();
        //Log.d("EntryContent", "10");
        link = source.readString();
        //Log.d("EntryContent", "11");
    }

    public static final Parcelable.Creator<EntryContent> CREATOR = new Parcelable.Creator<EntryContent>() {

        @Override
        public EntryContent createFromParcel(Parcel parcel) {
            Log.d("EntryContent", "Parcelable");
            return new EntryContent(parcel);
        }

        @Override
        public EntryContent[] newArray(int i) {
            return new EntryContent[i];
        }
    };


}
