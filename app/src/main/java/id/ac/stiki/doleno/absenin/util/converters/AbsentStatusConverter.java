package id.ac.stiki.doleno.absenin.util.converters;

import androidx.room.TypeConverter;

import id.ac.stiki.doleno.absenin.util.enums.AbsentStatus;

public class AbsentStatusConverter {
    @TypeConverter
    public AbsentStatus fromString(String value) {
        return AbsentStatus.fromString(value);
    }

    @TypeConverter
    public String absentStatusToString(AbsentStatus absentStatus) {
        return absentStatus.getText();
    }
}
