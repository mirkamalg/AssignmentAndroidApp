package com.mirkamal.beginnerandroidassignment.local;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.mirkamal.beginnerandroidassignment.local.dao.PostsDao;
import com.mirkamal.beginnerandroidassignment.local.dao.UsersDao;
import com.mirkamal.beginnerandroidassignment.model.entity.Post;
import com.mirkamal.beginnerandroidassignment.model.entity.User;

@Database(entities = {Post.class, User.class}, version = 1, exportSchema = false)
public abstract class DataBase extends RoomDatabase {

    public static String LOGGED_IN_USER_ID;

    public static final String DATABASE_NAME = "AppDatabase";
    private static DataBase INSTANCE;

    public static DataBase getInstance(Context context) {

        synchronized (DataBase.class) {

            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context, DataBase.class, DATABASE_NAME)
                        .allowMainThreadQueries()
                        .fallbackToDestructiveMigration()
                        .build();
            }

            return INSTANCE;
        }

    }

    public abstract PostsDao getPostsDao();

    public abstract UsersDao getUsersDao();

}
