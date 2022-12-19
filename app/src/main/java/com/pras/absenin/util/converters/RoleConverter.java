package com.pras.absenin.util.converters;

import androidx.room.TypeConverter;

import com.pras.absenin.util.Role;

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
