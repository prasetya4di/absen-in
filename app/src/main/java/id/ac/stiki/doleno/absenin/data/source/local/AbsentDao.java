package id.ac.stiki.doleno.absenin.data.source.local;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import id.ac.stiki.doleno.absenin.data.entity.Absent;

@Dao
public interface AbsentDao {
    @Query("SELECT * FROM absent")
    LiveData<List<Absent>> getAll();

    @Query("SELECT * FROM absent WHERE uid = :absentId")
    Absent getById(int absentId);

    @Query("SELECT * FROM absent WHERE status in (:absentStatus)")
    LiveData<List<Absent>> getByStatus(List<String> absentStatus);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Absent absent);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<Absent> absent);

    @Query("DELETE FROM absent")
    void delete();
}
