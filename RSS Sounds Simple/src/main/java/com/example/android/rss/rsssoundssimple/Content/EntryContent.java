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
    private String id;
    private String artist;
    private String category;
    private String releaseDate;
    private Bitmap image_small;
    private Bitmap image_medium;
    private Bitmap image_large;


    public EntryContent(JSONObject object) {
        try {
            name = object.getJSONObject("im:name").getString("label");

            JSONArray _image = object.getJSONArray("im:image");

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

    public void setSmallImage(Bitmap in) {
        image_small = in;
    }

    public void setMediumImage(Bitmap in) {
        image_medium = in;
    }

    public void setLargeImage(Bitmap in) {
        image_large = in;
    }

    public String getName() {
        return name;
    }

    public Bitmap getImage(int height) {
        switch(height) {
            case 53:
                return image_small;
            case 75:
                return image_medium;
            case 100:
                return image_large;
            default:
                return null;
        }
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
        image_small.writeToParcel(parcel, i);
        image_medium.writeToParcel(parcel, i);
        image_large.writeToParcel(parcel, i);
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
        //source.readStringList(image)
        image_small = Bitmap.CREATOR.createFromParcel(source);
        image_medium = Bitmap.CREATOR.createFromParcel(source);
        image_large = Bitmap.CREATOR.createFromParcel(source);

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
