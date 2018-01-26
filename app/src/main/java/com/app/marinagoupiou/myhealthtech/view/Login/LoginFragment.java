package com.app.marinagoupiou.myhealthtech.view.Login;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.app.marinagoupiou.myhealthtech.BuildConfig;
import com.app.marinagoupiou.myhealthtech.MainActivity;
import com.app.marinagoupiou.myhealthtech.MyHealthTechApplication;
import com.app.marinagoupiou.myhealthtech.R;
import com.app.marinagoupiou.myhealthtech.controller.UserController;
import com.app.marinagoupiou.myhealthtech.view.Employee.EmployeeFragment;
import com.app.marinagoupiou.myhealthtech.view.Patient.PatientFragment;

import io.reactivex.Completable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


/**
 * Created by marinagoupiou on 26/01/2018.
 */

public class LoginFragment extends Fragment {

    private TextInputLayout email_wrapper;
    private TextView input_email;

    private TextInputLayout password_wrapper;
    private TextView input_password;

    private AppCompatButton login_button;

    private UserController userController;

    public static final String FRAGMENT_TAG =
            BuildConfig.APPLICATION_ID + ".DEBUG_LOGIN_FRAGMENT";


    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.layout_login, container, false);

        email_wrapper = rootView.findViewById(R.id.email_wrapper);
        input_email = rootView.findViewById(R.id.input_email);

        password_wrapper = rootView.findViewById(R.id.password_wrapper);
        input_password = rootView.findViewById(R.id.input_password);

        login_button = rootView.findViewById(R.id.btn_login);


        // Listening to account link
        login_button.setOnClickListener(v -> {
            String email = "";
            String password = "";

            final ProgressDialog progressDialog = new ProgressDialog(getActivity());
            progressDialog.setMessage("Please wait...");
            progressDialog.show();

            if(input_email.getText() != null)
                email = input_email.getText().toString();
            if(input_password.getText() != null)
                password = input_password.getText().toString();

            final String emailFinal = email;
            final String passwordFinal = password;
            final MyHealthTechApplication application = (MyHealthTechApplication)getActivity().getApplication();
            Completable action = Completable.create(completableSubscriber -> {
                userController = application.getUserController();
                userController.login(emailFinal, passwordFinal)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(user -> {
                            // Authentication successful
                            progressDialog.dismiss();
                            application.getProfileModel().setUser(user);
                            switch (user.type) {
                                case Patient:
                                    ((MainActivity) getActivity()).replaceFragment(android.R.id.content,
                                            new PatientFragment(),
                                            PatientFragment.FRAGMENT_TAG, null);
                                    break;
                                case Employee:
                                    ((MainActivity) getActivity()).replaceFragment(android.R.id.content,
                                            new EmployeeFragment(),
                                            PatientFragment.FRAGMENT_TAG, null);

                                    break;
                            }
                        }, throwable -> {
                            progressDialog.dismiss();
                            // Authentication failed
                            Toast.makeText(getActivity(), "Authentication Failed! Please try again", Toast.LENGTH_LONG).show();
                        });
            }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
            action.subscribe();

        });

        return rootView;
    }

    private void hideKeyboard() {
        View view = getActivity().getCurrentFocus();
        if (view != null) {
            ((InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE)).
                    hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

}
