package com.mirkamal.beginnerandroidassignment.ui.fragments.home;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.mirkamal.beginnerandroidassignment.R;
import com.mirkamal.beginnerandroidassignment.local.DataBase;
import com.mirkamal.beginnerandroidassignment.local.dao.UsersDao;
import com.mirkamal.beginnerandroidassignment.model.entity.Post;

import java.io.File;

public class ItemPostViewHolder extends RecyclerView.ViewHolder {

    private TextView textViewUserName;
    private ImageView imageViewPost, imageViewProfile;

    public ItemPostViewHolder(@NonNull View itemView) {
        super(itemView);

        textViewUserName = itemView.findViewById(R.id.text_view_username);
        imageViewPost = itemView.findViewById(R.id.image_view_post);
        imageViewProfile = itemView.findViewById(R.id.image_view_profile);

        setProfilePicture();
    }

    private void setProfilePicture() {

        Glide.with(imageViewProfile.getContext()).load("https://upleap.com/blog/wp-content/uploads/2018/10/how-to-create-the-perfect-instagram-profile-picture.jpg").placeholder(R.drawable.sample_profile_picture).into(imageViewProfile);

    }

    public void bindItem(Post post, File filesDir) {

        UsersDao usersDao = DataBase.getInstance(imageViewPost.getContext()).getUsersDao();
        textViewUserName.setText(usersDao.getUserNameByID(post.getOwnerID()));

        Glide.with(imageViewPost.getContext()).load(new File(filesDir, post.getId() + ".png")).into(imageViewPost);
    }
}
