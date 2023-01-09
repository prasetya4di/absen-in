package id.ac.stiki.doleno.absenin.data.source.local;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import id.ac.stiki.doleno.absenin.data.entity.Event;

@Dao
public interface EventDao {
    @Query("SELECT * FROM event")
    LiveData<List<Event>> getAll();

    @Query("SELECT * FROM event WHERE uid = :eventId")
    Event getById(int eventId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Event event);

    @Insert(entity = Event.class, onConflict = OnConflictStrategy.REPLACE)
    void insert(List<Event> events);

    @Query("DELETE FROM event")
    void delete();
}
