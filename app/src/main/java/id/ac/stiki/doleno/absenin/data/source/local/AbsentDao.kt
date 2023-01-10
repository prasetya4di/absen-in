package id.ac.stiki.doleno.absenin.data.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import id.ac.stiki.doleno.absenin.data.entity.Absent

@Dao
interface AbsentDao {
    @get:Query("SELECT * FROM absent")
    val all: List<Absent>

    @Query("SELECT * FROM absent WHERE uid = :absentId")
    fun getById(absentId: Int): Absent

    @Query("SELECT * FROM absent WHERE status in (:absentStatus)")
    fun getByStatus(absentStatus: List<String>): List<Absent>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(absent: Absent)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(absent: List<Absent>)

    @Query("DELETE FROM absent")
    fun delete()
}