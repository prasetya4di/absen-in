package id.ac.stiki.doleno.absenin.data.entity;

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
import java.util.HashMap;
import java.util.Map;

import id.ac.stiki.doleno.absenin.data.database.table.Column;

@Entity
public class Event implements Serializable, Parcelable {
    public static final Creator<Event> CREATOR = new Creator<Event>() {
        @Override
        public Event createFromParcel(Parcel in) {
            return new Event(in);
        }

        @Override
        public Event[] newArray(int size) {
            return new Event[size];
        }
    };
    @PrimaryKey(autoGenerate = true)
    public int uid;
    @SerializedName("event_title")
    @ColumnInfo(name = "event_title")
    public String eventTitle;
    @SerializedName("event_description")
    @ColumnInfo(name = "event_description")
    public String eventDescription;
    @SerializedName("event_organizer")
    @ColumnInfo(name = "event_organizer")
    public String eventOrganizer;
    @SerializedName("event_date")
    @ColumnInfo(name = "event_date")
    public Date eventDate;
    public LatLng location;
    @SerializedName("location_name")
    @ColumnInfo(name = "location_name")
    public String locationName;

    public Event() {
    }

    public Event(int uid, String eventTitle, String eventDescription, String eventOrganizer, Date eventDate, LatLng location, String locationName) {
        this.uid = uid;
        this.eventTitle = eventTitle;
        this.eventDescription = eventDescription;
        this.eventOrganizer = eventOrganizer;
        this.eventDate = eventDate;
        this.location = location;
        this.locationName = locationName;
    }

    public Event(String eventTitle, String eventDescription, String eventOrganizer, Date eventDate, LatLng location, String locationName) {
        this.eventTitle = eventTitle;
        this.eventDescription = eventDescription;
        this.eventOrganizer = eventOrganizer;
        this.eventDate = eventDate;
        this.location = location;
        this.locationName = locationName;
    }

    public Event(Map<String, Object> data) {
        this.uid = (int) data.get(Column.Event.UID.getColumnName());
        this.eventTitle = (String) data.get(Column.Event.TITLE.getColumnName());
        this.eventDescription = (String) data.get(Column.Event.DESCRIPTION.getColumnName());
        this.eventOrganizer = (String) data.get(Column.Event.ORGANIZER.getColumnName());
        this.eventDate = (Date) data.get(Column.Event.DATE.getColumnName());
        this.location = (LatLng) data.get(Column.Event.LOCATION.getColumnName());
        this.locationName = (String) data.get(Column.Event.LOCATION_NAME.getColumnName());
    }

    protected Event(Parcel in) {
        uid = in.readInt();
        eventTitle = in.readString();
        eventDescription = in.readString();
        eventOrganizer = in.readString();
        location = in.readParcelable(LatLng.class.getClassLoader());
        locationName = in.readString();
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
        dest.writeString(eventOrganizer);
        dest.writeParcelable(location, flags);
        dest.writeString(locationName);
    }

    public String getDocumentPath() {
        return "event-" + uid;
    }

    public Map<String, Object> toMap() {
        Map<String, Object> event = new HashMap<>();
        event.put(Column.Event.UID.getColumnName(), uid);
        event.put(Column.Event.TITLE.getColumnName(), eventTitle);
        event.put(Column.Event.DESCRIPTION.getColumnName(), eventDescription);
        event.put(Column.Event.ORGANIZER.getColumnName(), eventOrganizer);
        event.put(Column.Event.DATE.getColumnName(), eventDate);
        event.put(Column.Event.LOCATION.getColumnName(), location);
        event.put(Column.Event.LOCATION_NAME.getColumnName(), locationName);
        return event;
    }
}
