package com.mirkamal.beginnerandroidassignment.local.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.mirkamal.beginnerandroidassignment.model.entity.User;

@Dao
public interface UsersDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(User user);

    @Query("select password from users where email = :email")
    String getPasswordByEmail(String email);

    @Query("select name from users where id = :id")
    String getUserNameByID(String id);

    @Query("select id from users where email = :email")
    String getUserIDByEmail(String email);

    @Query("select email from users where name = :name")
    String getEmailByID(String name);

    @Query("select email from users where email = :email")
    String getEmail(String email);
}
