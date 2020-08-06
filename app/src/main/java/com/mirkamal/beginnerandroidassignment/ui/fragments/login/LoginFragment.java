package com.mirkamal.beginnerandroidassignment.ui.fragments.login;

import android.os.Bundle;
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

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginFragment extends Fragment {

    private EditText editTextEmail, editTextPassword;
    private Button buttonLogin;
    private TextView textViewSignUp;
    private View arrowView;

    private NavController navController;

    private UsersDao usersDao;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        navController = NavHostFragment.findNavController(this);

        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        editTextEmail = view.findViewById(R.id.edit_text_email);
        editTextPassword = view.findViewById(R.id.edit_text_password);
        buttonLogin = view.findViewById(R.id.button_login);
        textViewSignUp = view.findViewById(R.id.text_view_sign_up);
        arrowView = view.findViewById(R.id.arrow_back);


        setClickListeners();
    }

    private void setClickListeners() {

        textViewSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navController.navigate(LoginFragmentDirections.actionLoginFragmentToRegisterFirstFragment());
            }
        });

        arrowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navController.popBackStack();
            }
        });

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DataBase dataBase = DataBase.getInstance(getContext());
                usersDao = dataBase.getUsersDao();

                if (isEmailValid()) {
                    if (!editTextPassword.getText().toString().isEmpty()) {
                        String attemptedPassword = usersDao.getPassword(editTextEmail.getText().toString());
                        if (attemptedPassword != null) {
                            if (editTextPassword.getText().toString().equals(attemptedPassword)) {
                                DataBase.LOGGED_IN_USER_NAME = usersDao.getUserName(editTextEmail.getText().toString());

                                Toast.makeText(getContext(), "Welcome!", Toast.LENGTH_SHORT).show();
                                navController.navigate(LoginFragmentDirections.actionLoginFragmentToContainerFragment());
                            } else {
                                Toast.makeText(getContext(), "Incorrect password!", Toast.LENGTH_SHORT).show();
                            }
                        }else {
                            Toast.makeText(getContext(), "No account found with this email!", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(getContext(), "Password field is empty!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getContext(), "Email is invalid!", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private boolean isEmailValid() {
        Pattern pattern = Pattern.compile("[^@ \\t\\r\\n]+@[^@ \\t\\r\\n]+\\.[^@ \\t\\r\\n]+");
        Matcher matcher = pattern.matcher(editTextEmail.getText().toString());
        return matcher.find();
    }

}
