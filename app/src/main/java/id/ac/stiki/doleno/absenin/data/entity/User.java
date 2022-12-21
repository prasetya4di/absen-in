package id.ac.stiki.doleno.absenin.data.entity;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

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
    @PrimaryKey
    @NonNull
    public String email;
    public Role role;

    public User() {
        email = "";
    }

    protected User(Parcel in) {
        name = in.readString();
        email = in.readString();
        role = Role.fromString(in.readString());
    }

    public User(String name, @NonNull String email, Role role) {
        this.name = name;
        this.email = email;
        this.role = role;
    }

    public User(Map<String, String> data) {
        this.email = data.get("email");
        this.name = data.get("name");
        this.role = Role.fromString(data.get("role"));
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(email);
        dest.writeString(role.getText());
    }

    public String getDocumentPath() {
        return "user-" + email;
    }

    public Map<String, String> toMap() {
        Map<String, String> mapUser = new HashMap<>();
        mapUser.put("email", email);
        mapUser.put("name", name);
        mapUser.put("role", role.getText());
        return mapUser;
    }
}
