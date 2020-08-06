package com.mirkamal.beginnerandroidassignment.ui.fragments.container;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.mirkamal.beginnerandroidassignment.R;

import java.util.Objects;

public class ContainerFragment extends Fragment {

    private BottomNavigationView bottomNavigationView;
    private NavController navController;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_container, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        navController = NavHostFragment.findNavController(Objects.requireNonNull(getChildFragmentManager().findFragmentById(R.id.nav_host_fragment)));

        bottomNavigationView = view.findViewById(R.id.bottom_bar);

        NavigationUI.setupWithNavController(bottomNavigationView, navController);
    }
}
