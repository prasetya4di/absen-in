package id.ac.stiki.doleno.absenin.data.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import id.ac.stiki.doleno.absenin.data.database.table.Column;
import id.ac.stiki.doleno.absenin.util.enums.AbsentStatus;

@Entity(tableName = "event_participant")
public class EventParticipant implements Serializable {
    @PrimaryKey
    public long uid;
    @SerializedName("event_id")
    @ColumnInfo(name = "event_id")
    public long eventId;
    @SerializedName("event_title")
    @ColumnInfo(name = "event_title")
    public String eventTitle;
    public String email;
    public String name;
    @SerializedName("absent_status")
    @ColumnInfo(name = "absent_status")
    public AbsentStatus absentStatus;

    public EventParticipant() {
    }

    public EventParticipant(long uid, long eventId, String eventTitle, String email, String name, AbsentStatus absentStatus) {
        this.uid = uid;
        this.eventId = eventId;
        this.eventTitle = eventTitle;
        this.email = email;
        this.name = name;
        this.absentStatus = absentStatus;
    }

    public EventParticipant(long eventId, String eventTitle, String email, String name, AbsentStatus absentStatus) {
        this.eventId = eventId;
        this.eventTitle = eventTitle;
        this.email = email;
        this.name = name;
        this.absentStatus = absentStatus;
    }

    public EventParticipant(Event event, User user) {
        this.eventId = event.uid;
        this.eventTitle = event.eventTitle;
        this.email = user.email;
        this.name = user.name;
        this.absentStatus = AbsentStatus.REGISTERED;
    }

    public EventParticipant(Map<String, Object> data) {
        this.uid = (long) data.get(Column.EventParticipant.UID.getColumnName());
        this.eventId = (long) data.get(Column.EventParticipant.EVENT_ID.getColumnName());
        this.eventTitle = (String) data.get(Column.EventParticipant.TITLE.getColumnName());
        this.email = (String) data.get(Column.EventParticipant.EMAIL.getColumnName());
        this.name = (String) data.get(Column.EventParticipant.NAME.getColumnName());
        this.absentStatus = AbsentStatus.fromString((String) data.get(Column.EventParticipant.STATUS.getColumnName()));
    }

    public String getDocumentPath() {
        return "event-participant-" + uid;
    }

    public Map<String, Object> toMap() {
        Map<String, Object> event = new HashMap<>();
        event.put(Column.EventParticipant.UID.getColumnName(), uid);
        event.put(Column.EventParticipant.EVENT_ID.getColumnName(), eventId);
        event.put(Column.EventParticipant.TITLE.getColumnName(), eventTitle);
        event.put(Column.EventParticipant.EMAIL.getColumnName(), email);
        event.put(Column.EventParticipant.NAME.getColumnName(), name);
        event.put(Column.EventParticipant.STATUS.getColumnName(), absentStatus.getText());
        return event;
    }
}
