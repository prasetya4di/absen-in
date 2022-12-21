package id.ac.stiki.doleno.absenin.data.source.local;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import id.ac.stiki.doleno.absenin.data.entity.Event;

@Dao
public interface EventDao {
    @Query("SELECT * FROM event")
    List<Event> getAll();

    @Query("SELECT * FROM event WHERE uid = :EventId")
    Event getById(int EventId);

    @Insert
    void insert(Event Event);

    @Query("DELETE FROM event")
    void delete();
}
