package com.mirkamal.beginnerandroidassignment.ui.fragments.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.mirkamal.beginnerandroidassignment.R;
import com.mirkamal.beginnerandroidassignment.local.DataBase;
import com.mirkamal.beginnerandroidassignment.local.dao.PostsDao;

public class HomeFragment extends Fragment {

    private RecyclerView recyclerViewPosts;

    private PostsDao postsDao;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        postsDao = DataBase.getInstance(getContext()).getPostsDao();

        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerViewPosts = view.findViewById(R.id.recycler_view_posts);

        configureRecyclerView();
    }

    private void configureRecyclerView() {
        PostsRecyclerViewAdapter adapter = new PostsRecyclerViewAdapter(requireActivity().getFilesDir());
        recyclerViewPosts.setAdapter(adapter);
        adapter.submitList(postsDao.getAllPosts());
    }
}
