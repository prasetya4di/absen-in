package id.ac.stiki.doleno.absenin.util.converters;

import androidx.room.TypeConverter;

import id.ac.stiki.doleno.absenin.util.enums.Role;

public class RoleConverter {
    @TypeConverter
    public Role fromString(String value) {
        return Role.fromString(value);
    }

    @TypeConverter
    public String roleToString(Role role) {
        return role.getText();
    }
}
