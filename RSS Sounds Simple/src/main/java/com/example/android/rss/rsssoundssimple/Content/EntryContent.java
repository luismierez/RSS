package com.example.android.rss.rsssoundssimple.Content;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Luis Mierez on 6/11/2014.
 */
public class EntryContent implements Parcelable {


    private String name;
    private String summary;
    private String price_label;
    private String price_amount;
    private String price_currency;
    private String content_term;
    private String content_label;
    private String rights;
    private String title;
    private String link;
    private int id;
    private String artist;
    private String category;
    private String releaseDate;
    private String image_url;


    public EntryContent(JSONObject object) {
        try {
            name = object.getJSONObject("im:name").getString("label");

            JSONArray _image = object.getJSONArray("im:image");
            image_url = _image.getJSONObject(0).getString("label");

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

            JSONObject artObject = object.getJSONObject("im:artist");

            artist = artObject.getString("label");

            id = object.getJSONObject("id").getJSONObject("attributes").getInt("im:id");
        } catch (JSONException e) {
            Log.d("EntryContent", "Error in constructor");
            e.printStackTrace();
        }
    }

    public String getImageUrl() {
        return image_url;
    }

    public String getName() {
        return name;
    }

    public String getSummary() {
        return summary;
    }

    public String getPrice(String what) {
        if (what.equals("label")) {
            return price_label;
        } else if (what.equals("amount")) {
            return price_amount;
        } else if (what.equals("currency")) {
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

    public String getArtist() {
        return artist;
    }

    public int getID() {
        return id;
    }

    public EntryContent(Parcel in) {
        readFromParcel(in);
    }



    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(image_url);
        parcel.writeString(summary);
        parcel.writeString(price_label);
        parcel.writeString(price_amount);
        parcel.writeString(price_currency);
        parcel.writeString(content_term);
        parcel.writeString(content_label);
        parcel.writeString(rights);
        parcel.writeString(title);
        parcel.writeString(link);
        parcel.writeInt(id);
    }

    public void readFromParcel(Parcel source) {
        name = source.readString();
        image_url = source.readString();
        summary = source.readString();
        price_label = source.readString();
        price_amount = source.readString();
        price_currency = source.readString();
        content_term = source.readString();
        content_label = source.readString();
        rights = source.readString();
        title = source.readString();
        link = source.readString();
        id = source.readInt();
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
