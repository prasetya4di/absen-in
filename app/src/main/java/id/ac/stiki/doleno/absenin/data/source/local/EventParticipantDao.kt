package id.ac.stiki.doleno.absenin.data.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import id.ac.stiki.doleno.absenin.data.entity.EventParticipant

@Dao
interface EventParticipantDao {
    @Query("SELECT * FROM event_participant where event_id = :eventId")
    fun getAll(eventId: Long?): List<EventParticipant>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(eventParticipant: EventParticipant)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(eventParticipant: List<EventParticipant>)
}