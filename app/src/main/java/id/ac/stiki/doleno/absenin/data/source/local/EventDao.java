package id.ac.stiki.doleno.absenin.data.source.local;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import id.ac.stiki.doleno.absenin.data.entity.Event;

@Dao
public interface EventDao {
    @Query("SELECT * FROM event")
    LiveData<List<Event>> getAll();

    @Query("SELECT * FROM event WHERE uid = :EventId")
    Event getById(int EventId);

    @Insert
    void insert(Event Event);

    @Insert
    void insert(List<Event> Event);

    @Query("DELETE FROM event")
    void delete();
}
