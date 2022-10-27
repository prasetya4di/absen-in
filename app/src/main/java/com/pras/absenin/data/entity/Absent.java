package com.pras.absenin.data.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.android.gms.maps.model.LatLng;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;

@Entity
public class Absent implements Serializable {
    @PrimaryKey
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
}
