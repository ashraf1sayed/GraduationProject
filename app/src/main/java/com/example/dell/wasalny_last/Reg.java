package com.example.dell.wasalny_last;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;

import java.util.HashMap;

public class Reg extends Fragment {
    private View mMainView;
    Context con;
    Temp temp;
    EditText username, phone, password, email;
    LinearLayout reg;
    private DatabaseReference mDatabase;
    private DatabaseReference mUsersDatabase;
    TextView txlogin;
    private ProgressDialog mProgressDialog;

    //ProgressDialog
    private ProgressDialog mRegProgress;

    //Firebase Auth
    private FirebaseAuth mAuth;

    public Reg() {
        // Required empty public constructor
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mMainView = inflater.inflate(R.layout.activity_reg, container, false);
        username = (EditText) mMainView.findViewById(R.id.reg_name);
        phone = (EditText) mMainView.findViewById(R.id.reg_phone);
        password = (EditText) mMainView.findViewById(R.id.reg_password);
        email = (EditText) mMainView.findViewById(R.id.reg_email);
        reg = (LinearLayout) mMainView.findViewById(R.id.register_btn);
        txlogin = mMainView.findViewById(R.id.logintxt);
        temp = new Temp();

        mAuth = FirebaseAuth.getInstance();
        mRegProgress = new ProgressDialog(getContext());
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String display_name = username.getText().toString();
                String email_display = email.getText().toString();
                String password_display = password.getText().toString();
                String phonet = phone.getText().toString();

                if (!TextUtils.isEmpty(display_name) || !TextUtils.isEmpty(email_display) || !TextUtils.isEmpty(password_display)) {

                    mRegProgress.setTitle("جاري التسجيل....");
                    mRegProgress.setMessage("من فضلك انتظر!!");
                    mRegProgress.setCanceledOnTouchOutside(false);
                    mRegProgress.show();

                    register_user(display_name, email_display, phonet, password_display);
                }

            }
        });
        txlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Login details = new Login();
                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.content, details)
                        .commit();

            }
        });


        return mMainView;

    }

    @Override
    public void onStart() {
        super.onStart();
    }

    private void register_user(final String display_name, final String email, final String phone, final String password) {

        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()) {
//                              mRegProgress.dismiss();
//
//                    Intent mainIntent = new Intent(RegisterActivity.this, MainActivity.class);
//                                mainIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                                startActivity(mainIntent);
//                                finish();


                    FirebaseUser current_user = FirebaseAuth.getInstance().getCurrentUser();
                    String uid = current_user.getUid();

                    mDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child(uid);

                    String device_token = FirebaseInstanceId.getInstance().getToken();

                    HashMap<String, String> userMap = new HashMap<>();
                    userMap.put("email", email);
                    userMap.put("name", display_name);
                    userMap.put("password", password);
                    userMap.put("phone", phone);
                    //  userMap.put("device_token", device_token);

                    mDatabase.setValue(userMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                mRegProgress.dismiss();
                                if (temp.getProf_mark() == 1) {
                                    Daily_booking details = new Daily_booking();
                                    getFragmentManager()
                                            .beginTransaction()
                                            .replace(R.id.content, details)
                                            .commit();

                                } else {


                                    Monthly_booking_fragment details = new Monthly_booking_fragment();
                                    getFragmentManager()
                                            .beginTransaction()
                                            .replace(R.id.content, details)
                                            .commit();
                                }


                            }

                        }
                    });


                } else {
                    mRegProgress.hide();

//                    String s = "Sign up Failed" + task.getException();
                    Log.d("reg", "onComplete: Failed=" + task.getException().getMessage()); //ADD THIS
//
//                    Toast.makeText(con, s,
//                            Toast.LENGTH_SHORT).show();
                    Toast.makeText(getContext(), "من فضلك ادخل البيانات صحيحة", Toast.LENGTH_LONG).show();

                }

            }
        });


    }
}
