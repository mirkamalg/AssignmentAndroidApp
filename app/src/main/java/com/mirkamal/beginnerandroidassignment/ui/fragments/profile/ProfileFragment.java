package com.mirkamal.beginnerandroidassignment.ui.fragments.profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.mirkamal.beginnerandroidassignment.R;
import com.mirkamal.beginnerandroidassignment.local.DataBase;
import com.mirkamal.beginnerandroidassignment.local.dao.UsersDao;

public class ProfileFragment extends Fragment {

    private TextView textViewEmail, textViewUserName;

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

        setTexts();
    }

    private void setTexts() {
        textViewEmail.setText(usersDao.getEmail(DataBase.LOGGED_IN_USER_NAME));
        textViewUserName.setText(DataBase.LOGGED_IN_USER_NAME);
    }
}
