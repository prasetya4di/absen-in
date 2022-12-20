package id.ac.stiki.doleno.absenin.data.entity;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

import id.ac.stiki.doleno.absenin.util.enums.Role;

@Entity
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
    public String name;
    public String email;
    public Role role;
    @PrimaryKey
    @NonNull
    public String username;

    public User() {
        username = "";
    }

    protected User(Parcel in) {
        username = in.readString();
        name = in.readString();
        email = in.readString();
        role = Role.fromString(in.readString());
    }

    public User(String username, String name, String email, Role role) {
        this.username = username;
        this.name = name;
        this.email = email;
        this.role = role;
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
