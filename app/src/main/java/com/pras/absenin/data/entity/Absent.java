package com.pras.absenin.data.entity;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.android.gms.maps.model.LatLng;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;

@Entity
public class Absent implements Serializable, Parcelable {
    @PrimaryKey(autoGenerate = true)
    public int uid;

    @SerializedName("event_title")
    @ColumnInfo(name = "event_title")
    public String eventTitle;

    @SerializedName("event_description")
    @ColumnInfo(name = "event_description")
    public String eventDescription;

    @SerializedName("event_date")
    @ColumnInfo(name = "event_date")
    public Date eventDate;

    public LatLng location;

    public static final Creator<Absent> CREATOR = new Creator<Absent>() {
        @Override
        public Absent createFromParcel(Parcel in) {
            return new Absent(in);
        }

        @Override
        public Absent[] newArray(int size) {
            return new Absent[size];
        }
    };

    public Absent() {
    }

    public Absent(int uid, String eventTitle, String eventDescription, Date eventDate, LatLng location) {
        this.uid = uid;
        this.eventTitle = eventTitle;
        this.eventDescription = eventDescription;
        this.eventDate = eventDate;
        this.location = location;
    }

    protected Absent(Parcel in) {
        uid = in.readInt();
        eventTitle = in.readString();
        eventDescription = in.readString();
        location = in.readParcelable(LatLng.class.getClassLoader());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeInt(uid);
        dest.writeString(eventTitle);
        dest.writeString(eventDescription);
        dest.writeParcelable(location, flags);
    }
}
