package id.ac.stiki.doleno.absenin.data.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import id.ac.stiki.doleno.absenin.data.entity.Event

@Dao
interface EventDao {
    @get:Query("SELECT * FROM event")
    val all: List<Event>

    @Query("SELECT * FROM event WHERE uid = :eventId")
    fun getById(eventId: Int): Event

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(event: Event)

    @Insert(entity = Event::class, onConflict = OnConflictStrategy.REPLACE)
    fun insert(events: List<Event>)

    @Query("DELETE FROM event")
    fun delete()
}