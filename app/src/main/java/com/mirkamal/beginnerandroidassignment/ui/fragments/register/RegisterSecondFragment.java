package com.mirkamal.beginnerandroidassignment.ui.fragments.register;

import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.RelativeSizeSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.mirkamal.beginnerandroidassignment.R;
import com.mirkamal.beginnerandroidassignment.local.DataBase;
import com.mirkamal.beginnerandroidassignment.local.dao.UsersDao;
import com.mirkamal.beginnerandroidassignment.model.entity.User;

import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterSecondFragment extends Fragment {

    private EditText editTextPassword, editTextPasswordConfirm;
    private Button buttonSignUp;
    private View arrowView;
    private TextView textViewStep;

    private NavController navController;
    private UsersDao usersDao;

    private String[] nameAndEmail;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        navController = NavHostFragment.findNavController(this);

        return inflater.inflate(R.layout.fragment_register_second, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        editTextPassword = view.findViewById(R.id.edit_text_password);
        editTextPasswordConfirm = view.findViewById(R.id.edit_text_password_confirm);
        buttonSignUp = view.findViewById(R.id.button_sign_up);
        arrowView = view.findViewById(R.id.arrow_back);
        textViewStep = view.findViewById(R.id.text_view_step);

        assert getArguments() != null;
        nameAndEmail = RegisterSecondFragmentArgs.fromBundle(getArguments()).getNameAndEmail();

        configureListeners();
        configureTextViewStep();
    }

    private void configureListeners() {

        arrowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navController.popBackStack();
            }
        });

        buttonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isUserInputValid()) {

                    DataBase dataBase = DataBase.getInstance(getContext());
                    usersDao = dataBase.getUsersDao();
                    usersDao.insert(new User(UUID.randomUUID().toString(), nameAndEmail[0], nameAndEmail[1], editTextPassword.getText().toString()));

                    DataBase.LOGGED_IN_USER_NAME = nameAndEmail[0];

                    Toast.makeText(getContext(), "Welcome!", Toast.LENGTH_SHORT).show();
                    navController.navigate(RegisterSecondFragmentDirections.actionRegisterSecondFragmentToContainerFragment());
                }
            }
        });

    }

    private boolean isUserInputValid() {
        if (!editTextPassword.getText().toString().isEmpty()) {
            if (!editTextPasswordConfirm.getText().toString().isEmpty()) {
                if (editTextPassword.getText().toString().equals(editTextPasswordConfirm.getText().toString())) {
                    Pattern pattern = Pattern.compile("^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$ %^&*-]).{8,}$");
                    Matcher matcher = pattern.matcher(editTextPassword.getText().toString());
                    if (matcher.find()) {
                        return true;
                    } else {
                        Toast.makeText(getContext(), "Password is not valid!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getContext(), "Passwords don't match!", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(getContext(), "Confirm password field is empty!", Toast.LENGTH_SHORT).show();
            }
        }else {
            Toast.makeText(getContext(), "Password field is empty!", Toast.LENGTH_SHORT).show();
        }
        return false;
    }

    private void configureTextViewStep() {
        SpannableString string = new SpannableString("2 / 2");
        string.setSpan(new RelativeSizeSpan(1.5f), 0,1, 0);

        textViewStep.setText(string);
    }
}
