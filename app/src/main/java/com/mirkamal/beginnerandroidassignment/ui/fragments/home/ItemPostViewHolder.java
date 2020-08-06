package com.mirkamal.beginnerandroidassignment.ui.fragments.home;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mirkamal.beginnerandroidassignment.R;
import com.mirkamal.beginnerandroidassignment.local.DataBase;
import com.mirkamal.beginnerandroidassignment.model.entity.Post;

import java.io.File;
import java.io.FileInputStream;

public class ItemPostViewHolder extends RecyclerView.ViewHolder {

    private TextView textViewUserName;
    private ImageView imageViewPost;

    public ItemPostViewHolder(@NonNull View itemView) {
        super(itemView);

        textViewUserName = itemView.findViewById(R.id.text_view_username);
        imageViewPost = itemView.findViewById(R.id.image_view_post);
    }

    public void bindItem(Post post, File filesDir) {
        textViewUserName.setText(DataBase.LOGGED_IN_USER_NAME);

        try {
            File readFile = new File(filesDir, post.getId() + ".png");
            Bitmap bitmap = BitmapFactory.decodeStream(new FileInputStream(readFile));
            imageViewPost.setImageBitmap(bitmap);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
