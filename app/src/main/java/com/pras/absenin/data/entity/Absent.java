package com.pras.absenin.data.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.android.gms.maps.model.LatLng;

import java.util.Date;

@Entity
public class Absent {
    @PrimaryKey
    public int uid;

    @ColumnInfo(name = "event_title")
    public String eventTitle;

    @ColumnInfo(name = "event_description")
    public String eventDescription;

    @ColumnInfo(name = "event_date")
    public Date eventDate;

    public LatLng location;
}
