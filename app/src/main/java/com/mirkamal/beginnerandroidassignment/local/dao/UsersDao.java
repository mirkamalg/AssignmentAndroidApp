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
    String getPassword(String email);

    @Query("select name from users where email = :email")
    String getUserName(String email);

    @Query("select email from users where name = :name")
    String getEmail(String name);
}
