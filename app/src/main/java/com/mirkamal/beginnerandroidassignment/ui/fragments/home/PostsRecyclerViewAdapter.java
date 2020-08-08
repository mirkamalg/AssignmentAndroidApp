package com.mirkamal.beginnerandroidassignment.ui.fragments.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mirkamal.beginnerandroidassignment.R;
import com.mirkamal.beginnerandroidassignment.model.entity.Post;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PostsRecyclerViewAdapter extends RecyclerView.Adapter<ItemPostViewHolder> {

    private List<Post> postList = new ArrayList<>();
    private File filesDir;

    public PostsRecyclerViewAdapter(File filesDir) {
        this.filesDir = filesDir;
    }

    @NonNull
    @Override
    public ItemPostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_posts, parent, false);

        return new ItemPostViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemPostViewHolder holder, int position) {

        holder.bindItem(postList.get(position), filesDir);

    }

    @Override
    public int getItemCount() {
        return postList.size();
    }

    public void submitList(List<Post> posts) {
        postList.clear();
        postList.addAll(posts);
        Collections.reverse(postList);
        notifyDataSetChanged();
    }
}
