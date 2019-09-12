package com.example.dell.wasalny_last;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.iid.FirebaseInstanceId;

public class Login extends Fragment {

    private View mMainView;
    private Toolbar mToolbar;
    Temp temp;
    EditText email, password;
    TextView register_txt;

    private Button mLogin_btn;
    LinearLayout mlogin_lay;

    private ProgressDialog mLoginProgress;

    private FirebaseAuth mAuth;

    private DatabaseReference mUserDatabase;


    public Login() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mMainView = inflater.inflate(R.layout.activity_login, container, false);
        register_txt = (TextView) mMainView.findViewById(R.id.Register_login);
        mAuth = FirebaseAuth.getInstance();

        temp = new Temp();
        mLoginProgress = new ProgressDialog(getContext());
        email = (EditText) mMainView.findViewById(R.id.mail_login);
        password = (EditText) mMainView.findViewById(R.id.password_login);
        mlogin_lay = (LinearLayout) mMainView.findViewById(R.id.login_lay);
        mlogin_lay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String emailtxt = email.getText().toString();
                String passwordtxt = password.getText().toString();
                if (!TextUtils.isEmpty(emailtxt) || !TextUtils.isEmpty(emailtxt)) {

                    mLoginProgress.setTitle("Logging In");
                    mLoginProgress.setMessage("Please wait while we check your credentials.");
                    mLoginProgress.setCanceledOnTouchOutside(false);
                    mLoginProgress.show();
                    loginUser(emailtxt, passwordtxt);
                    Toast.makeText(getContext(), "succes : ", Toast.LENGTH_LONG).show();
                } else {
                    password.setError("required");
                    email.setError("required");
                    //  Toast.makeText(getContext(), "can", Toast.LENGTH_LONG).show();

                }
            }

        });

        register_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Reg details = new Reg();
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


    private void loginUser(final String email, final String password) {


        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()) {

                    mLoginProgress.dismiss();
                    Toast.makeText(getContext(), "succcc", Toast.LENGTH_LONG).show();

                    String current_user_id = mAuth.getCurrentUser().getUid();
                    String deviceToken = FirebaseInstanceId.getInstance().getToken();
                    mUserDatabase.child(current_user_id).child("device_token").setValue(deviceToken).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                  if(temp.getProf_mark()==1)
                  {
                      Daily_booking details = new Daily_booking();
                      getFragmentManager()
                              .beginTransaction()
                              .replace(R.id.content, details)
                              .commit();

                  }
                  else
                  {


                            Monthly_booking_fragment details = new Monthly_booking_fragment();
                            getFragmentManager()
                                    .beginTransaction()
                                    .replace(R.id.content, details)
                                    .commit();
                  }
                        }
                    });


                } else {
                    mLoginProgress.hide();
                    String task_result = task.getException().getMessage().toString();
                    Log.d("map2222", task_result);
                    Toast.makeText(getContext(), "you do not have an acoount--!", Toast.LENGTH_LONG).show();


                }

            }
        });


    }


}
