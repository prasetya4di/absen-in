package id.ac.stiki.doleno.absenin.data.source.local;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import id.ac.stiki.doleno.absenin.data.entity.EventParticipant;

@Dao
public interface EventParticipantDao {
    @Query("SELECT * FROM event_participant where event_id = :eventId")
    LiveData<List<EventParticipant>> getAll(int eventId);

    @Insert
    void insert(EventParticipant eventParticipant);

    @Insert
    void insert(List<EventParticipant> eventParticipant);
}
