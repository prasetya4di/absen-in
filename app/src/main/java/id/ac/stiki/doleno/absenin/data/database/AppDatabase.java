package id.ac.stiki.doleno.absenin.data.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import id.ac.stiki.doleno.absenin.data.entity.Absent;
import id.ac.stiki.doleno.absenin.data.entity.Event;
import id.ac.stiki.doleno.absenin.data.entity.EventParticipant;
import id.ac.stiki.doleno.absenin.data.entity.User;
import id.ac.stiki.doleno.absenin.data.source.local.AbsentDao;
import id.ac.stiki.doleno.absenin.data.source.local.EventDao;
import id.ac.stiki.doleno.absenin.data.source.local.EventParticipantDao;
import id.ac.stiki.doleno.absenin.data.source.local.UserDao;
import id.ac.stiki.doleno.absenin.util.converters.AbsentStatusConverter;
import id.ac.stiki.doleno.absenin.util.converters.DateConverter;
import id.ac.stiki.doleno.absenin.util.converters.LatLngConverter;
import id.ac.stiki.doleno.absenin.util.converters.RoleConverter;

@Database(entities = {Absent.class, User.class, Event.class, EventParticipant.class}, version = 1)
@TypeConverters({DateConverter.class, LatLngConverter.class, RoleConverter.class, AbsentStatusConverter.class})
public abstract class AppDatabase extends RoomDatabase {
    public abstract AbsentDao absentDao();

    public abstract UserDao userDao();

    public abstract EventDao eventDao();

    public abstract EventParticipantDao eventParticipantDao();
}
