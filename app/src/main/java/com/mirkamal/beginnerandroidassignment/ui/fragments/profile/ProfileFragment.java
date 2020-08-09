package com.mirkamal.beginnerandroidassignment.ui.fragments.profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.mirkamal.beginnerandroidassignment.R;
import com.mirkamal.beginnerandroidassignment.local.DataBase;
import com.mirkamal.beginnerandroidassignment.local.dao.UsersDao;

public class ProfileFragment extends Fragment {

    private TextView textViewEmail, textViewUserName;
    private ImageView imageViewProfile;

    private UsersDao usersDao;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        usersDao = DataBase.getInstance(getContext()).getUsersDao();

        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        textViewEmail = view.findViewById(R.id.text_view_email);
        textViewUserName = view.findViewById(R.id.text_view_secondary_username);
        imageViewProfile = view.findViewById(R.id.image_view_profile);

        setTexts();
        setProfilePicture();
    }

    private void setTexts() {
        textViewEmail.setText(usersDao.getEmailByID(DataBase.LOGGED_IN_USER_ID));

        UsersDao usersDao = DataBase.getInstance(textViewUserName.getContext()).getUsersDao();
        textViewUserName.setText(usersDao.getUserNameByID(DataBase.LOGGED_IN_USER_ID));
    }

    private void setProfilePicture() {

        Glide.with(this).load("https://upleap.com/blog/wp-content/uploads/2018/10/how-to-create-the-perfect-instagram-profile-picture.jpg").circleCrop().placeholder(R.drawable.sample_profile_picture).into(imageViewProfile);

    }
}
