package id.ac.stiki.doleno.absenin.util.converters;

import androidx.room.TypeConverter;

import java.util.Date;

public class DateConverter {
    @TypeConverter
    public Date fromTimestamp(long value) {
        return new Date(value);
    }

    @TypeConverter
    public long dateToTimestamp(Date date) {
        return date.getTime();
    }
}
