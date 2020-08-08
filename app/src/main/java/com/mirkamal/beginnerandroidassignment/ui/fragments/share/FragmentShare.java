package com.mirkamal.beginnerandroidassignment.ui.fragments.share;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.google.android.material.textfield.TextInputLayout;
import com.mirkamal.beginnerandroidassignment.R;
import com.mirkamal.beginnerandroidassignment.local.DataBase;
import com.mirkamal.beginnerandroidassignment.local.dao.PostsDao;
import com.mirkamal.beginnerandroidassignment.local.dao.UsersDao;
import com.mirkamal.beginnerandroidassignment.model.entity.Post;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Objects;
import java.util.UUID;

import static android.app.Activity.RESULT_OK;

public class FragmentShare extends Fragment {

    private ImageView imageView, imageViewProfile;
    private TextView textViewUserName;
    private Switch switchComments;
    private TextInputLayout textInputLayoutDescription;
    private Button buttonShare;

    private PostsDao postsDao;

    private boolean isImageChosen = false;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_share, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        imageView = view.findViewById(R.id.cropper);
        imageViewProfile = view.findViewById(R.id.image_view_profile);
        textViewUserName = view.findViewById(R.id.text_view_username);
        switchComments = view.findViewById(R.id.switch_allow_comments);
        textInputLayoutDescription = view.findViewById(R.id.edit_text_description);
        buttonShare = view.findViewById(R.id.button_share);

        UsersDao usersDao = DataBase.getInstance(textViewUserName.getContext()).getUsersDao();
        textViewUserName.setText(usersDao.getUserNameByID(DataBase.LOGGED_IN_USER_ID));

        postsDao = DataBase.getInstance(getContext()).getPostsDao();

        configureListeners();
        loadProfilePicture();
    }

    private void configureListeners() {

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                CropImage.activity()
                        .setGuidelines(CropImageView.Guidelines.ON)
                        .start(requireContext(), FragmentShare.this);
            }
        });

        buttonShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isImageChosen) {
                    String id = UUID.randomUUID().toString();

                    postsDao.insert(new Post(id, Objects.requireNonNull(textInputLayoutDescription.getEditText()).getText().toString(), DataBase.LOGGED_IN_USER_ID, switchComments.isSelected()));

                    writeImageToInternalStorage(((BitmapDrawable)imageView.getDrawable()).getBitmap(), id);

                    Toast.makeText(getContext(), "Post shared!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), "Please choose a valid picture!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);

            if (resultCode == RESULT_OK) {
                assert result != null;
                Uri resultUri = result.getUri();
                imageView.setImageURI(resultUri);
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);

                isImageChosen = true;
            }
        }
    }

    private void writeImageToInternalStorage(Bitmap bitmap, String id) {

        File file = new File(requireActivity().getFilesDir(),id + ".png");

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);

            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                assert fos != null;
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    private void loadProfilePicture() {

        Glide.with(this).load("https://upleap.com/blog/wp-content/uploads/2018/10/how-to-create-the-perfect-instagram-profile-picture.jpg").circleCrop().placeholder(R.drawable.sample_profile_picture).into(imageViewProfile);

    }
}
