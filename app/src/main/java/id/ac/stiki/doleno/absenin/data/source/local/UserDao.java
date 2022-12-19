package id.ac.stiki.doleno.absenin.data.source.local;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import id.ac.stiki.doleno.absenin.data.entity.User;

@Dao
public interface UserDao {
    @Query("SELECT * FROM user LIMIT 1")
    User getUser();

    @Update
    void updateUser(User user);

    @Insert
    void insert(User user);
}
