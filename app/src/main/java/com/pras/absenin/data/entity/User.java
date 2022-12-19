package com.pras.absenin.data.entity;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.Dao;
import androidx.room.PrimaryKey;

import com.pras.absenin.util.Role;

import java.io.Serializable;

@Dao
public class User implements Serializable, Parcelable {
    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };
    @PrimaryKey
    public String username;
    public String name;
    public String email;
    public Role role;

    protected User(Parcel in) {
        username = in.readString();
        name = in.readString();
        email = in.readString();
        role = Role.fromString(in.readString());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(username);
        dest.writeString(name);
        dest.writeString(email);
        dest.writeString(role.getText());
    }
}
