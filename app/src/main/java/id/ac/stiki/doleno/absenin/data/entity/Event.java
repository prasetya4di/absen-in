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
    @SerializedName("event_date")
    @ColumnInfo(name = "event_date")
    public Date eventDate;
    public LatLng location;

    public Event() {
    }

    public Event(int uid, String eventTitle, String eventDescription, Date eventDate, LatLng location) {
        this.uid = uid;
        this.eventTitle = eventTitle;
        this.eventDescription = eventDescription;
        this.eventDate = eventDate;
        this.location = location;
    }

    protected Event(Parcel in) {
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
