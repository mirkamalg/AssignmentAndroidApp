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

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterFirstFragment extends Fragment {

    private EditText editTextName, editTextEmail;
    private Button buttonNext;
    private TextView textViewLogin;
    private View arrowView;
    private TextView textViewStep;

    private NavController navController;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        navController = NavHostFragment.findNavController(this);

        return inflater.inflate(R.layout.fragment_register_first, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        editTextName = view.findViewById(R.id.edit_text_name);
        editTextEmail = view.findViewById(R.id.edit_text_email);
        buttonNext = view.findViewById(R.id.button_next);
        textViewLogin = view.findViewById(R.id.text_view_login);
        arrowView = view.findViewById(R.id.arrow_back);
        textViewStep = view.findViewById(R.id.text_view_step);

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

        textViewLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navController.popBackStack(R.id.registerFirstFragment, true);
            }
        });

        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isUserInputValid()) {
                    navController.navigate(RegisterFirstFragmentDirections.actionRegisterFirstFragmentToRegisterSecondFragment(
                            new String[]{editTextName.getText().toString()
                                    , editTextEmail.getText().toString()}));
                }
            }
        });


    }

    private boolean isUserInputValid() {

        if (!editTextName.getText().toString().isEmpty()) {
            Pattern pattern = Pattern.compile("[^@ \\t\\r\\n]+@[^@ \\t\\r\\n]+\\.[^@ \\t\\r\\n]+");
            Matcher matcher = pattern.matcher(editTextEmail.getText().toString());
            if (matcher.find()) {
                return true;
            } else {
                Toast.makeText(getContext(), "Email is not valid!", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(getContext(), "Name field is empty!", Toast.LENGTH_SHORT).show();
        }

        return false;
    }

    private void configureTextViewStep() {
        SpannableString string = new SpannableString("1 / 2");
        string.setSpan(new RelativeSizeSpan(1.5f), 0,1, 0);

        textViewStep.setText(string);
    }

}
