package id.ac.stiki.doleno.absenin.data.source.local;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import id.ac.stiki.doleno.absenin.data.entity.Event;

@Dao
public interface EventDao {
    @Query("SELECT * FROM Event")
    List<Event> getAll();

    @Query("SELECT * FROM Event WHERE uid = :EventId")
    Event getById(int EventId);

    @Insert
    void insert(Event Event);
}