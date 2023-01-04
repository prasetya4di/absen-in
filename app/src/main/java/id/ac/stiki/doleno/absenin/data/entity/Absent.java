package id.ac.stiki.doleno.absenin.data.entity;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.Timestamp;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import id.ac.stiki.doleno.absenin.data.database.table.Column;

@Entity
public class Absent implements Serializable, Parcelable {
    @PrimaryKey(autoGenerate = true)
    public long uid;

    @SerializedName("absent_title")
    @ColumnInfo(name = "absent_title")
    public String absentTitle;

    @SerializedName("absent_description")
    @ColumnInfo(name = "absent_description")
    public String absentDescription;

    @SerializedName("absent_organizer")
    @ColumnInfo(name = "absent_organizer")
    public String absentOrganizer;

    @SerializedName("absent_organizer_mail")
    @ColumnInfo(name = "absent_organizer_mail")
    public String absentOrganizerMail;

    @SerializedName("absent_date")
    @ColumnInfo(name = "absent_date")
    public Date absentDate;

    public LatLng location;
    @SerializedName("location_name")
    @ColumnInfo(name = "location_name")
    public String locationName;

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

    public Absent(int uid, String absentTitle, String absentDescription, String absentOrganizer, String absentOrganizerMail, Date absentDate, LatLng location, String locationName) {
        this.uid = uid;
        this.absentTitle = absentTitle;
        this.absentDescription = absentDescription;
        this.absentOrganizer = absentOrganizer;
        this.absentOrganizerMail = absentOrganizerMail;
        this.absentDate = absentDate;
        this.location = location;
        this.locationName = locationName;
    }

    public Absent(Map<String, Object> data) {
        this.uid = (long) data.get(Column.Absent.UID.getColumnName());
        this.absentTitle = (String) data.get(Column.Absent.TITLE.getColumnName());
        this.absentDescription = (String) data.get(Column.Absent.DESCRIPTION.getColumnName());
        this.absentOrganizer = (String) data.get(Column.Absent.ORGANIZER.getColumnName());
        this.absentOrganizerMail = (String) data.get(Column.Absent.ORGANIZER_MAIL.getColumnName());
        Timestamp absentDate = (Timestamp) data.get(Column.Absent.DATE.getColumnName());
        this.absentDate = absentDate.toDate();
        HashMap location = (HashMap) data.get(Column.Absent.LOCATION.getColumnName());
        this.location = new LatLng((Double) location.get("latitude"), (double) location.get("longitude"));
        this.locationName = (String) data.get(Column.Absent.LOCATION_NAME.getColumnName());
    }

    protected Absent(Parcel in) {
        uid = in.readInt();
        absentTitle = in.readString();
        absentDescription = in.readString();
        absentOrganizer = in.readString();
        absentOrganizerMail = in.readString();
        location = in.readParcelable(LatLng.class.getClassLoader());
        locationName = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeLong(uid);
        dest.writeString(absentTitle);
        dest.writeString(absentDescription);
        dest.writeString(absentOrganizer);
        dest.writeString(absentOrganizerMail);
        dest.writeParcelable(location, flags);
        dest.writeString(locationName);
    }

    public String getDocumentPath() {
        return "absent-" + uid;
    }

    public Map<String, Object> toMap() {
        Map<String, Object> Absent = new HashMap<>();
        Absent.put(Column.Absent.UID.getColumnName(), uid);
        Absent.put(Column.Absent.TITLE.getColumnName(), absentTitle);
        Absent.put(Column.Absent.DESCRIPTION.getColumnName(), absentDescription);
        Absent.put(Column.Absent.ORGANIZER.getColumnName(), absentOrganizer);
        Absent.put(Column.Absent.ORGANIZER_MAIL.getColumnName(), absentOrganizerMail);
        Absent.put(Column.Absent.DATE.getColumnName(), absentDate);
        Absent.put(Column.Absent.LOCATION.getColumnName(), location);
        Absent.put(Column.Absent.LOCATION_NAME.getColumnName(), locationName);
        return Absent;
    }
}
