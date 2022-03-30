package com.example.duanmotnhom12.FromDangNhap;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;


import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import com.example.duanmotnhom12.FromBanGiay.FromBanGiay;
import com.example.duanmotnhom12.R;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;

import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.shobhitpuri.custombuttons.GoogleSignInButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

public class FromDangNhap extends AppCompatActivity {
    GoogleSignInButton sign;
    GoogleSignInClient mGoogleSignInClient;
    int RC_SIGN_IN = 0;

    //LogInfb
    CallbackManager callbackManager;
    LoginButton loginButton;
    Button btn_log, button, button_gg;

    String id, name, email, first_name;

    TextView tv_dangKy ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_from_dang_nhap);


        FacebookSdk.sdkInitialize(this);
        callbackManager = CallbackManager.Factory.create();

        btn_log = findViewById(R.id.btn_dangNhap);
        button = findViewById(R.id.fb);
        button_gg =findViewById(R.id.gg);
        sign = findViewById(R.id.sigin_btn);
        loginButton = findViewById(R.id.login_button);
        tv_dangKy =findViewById(R.id.tv_dangKy);

        loginButton.setReadPermissions(Arrays.asList("public_profile", "email"));

        editBtnFb();
        DangNhapGoogle();
        DangNhaplogApi();
        DangKyTaiKhoan();
    }
    public void DangNhapGoogle() {
        button_gg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v == button_gg) {
                    sign.performClick();
                    switch (v.getId()) {
                        case R.id.gg:
                            signIn();
                            break;
                    }
                }
            }
        });
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
    }

    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);

            // Signed in successfully, show authenticated UI.
            Intent intent = new Intent(FromDangNhap.this, FromBanGiay.class);
            startActivity(intent);
        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w("Error", "signInResult:failed code=" + e.getStatusCode());
        }
    }

    public void editBtnFb() {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v == button) {
                    loginButton.performClick();
                    loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
                        @Override
                        public void onSuccess(LoginResult loginResult) {
                            layThongTinve();
                        }

                        @Override
                        public void onCancel() {

                        }

                        @Override
                        public void onError(FacebookException error) {

                        }
                    });
                }
            }
        });
    }

    private void layThongTinve() {
        GraphRequest graphRequest = GraphRequest.newMeRequest(AccessToken.getCurrentAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
            @Override
            public void onCompleted(JSONObject object, GraphResponse response) {
                Log.d("jsonn", response.getJSONObject().toString());
                try {
                    id = object.getString("id");
                    name = object.getString("name");
                    first_name = object.getString("first_name");
                    email = object.getString("email");
                    Log.d("name", id);
                    Log.d("name", name);
                    Log.d("name", first_name + "");
                    Log.d("name", email + "");
                    Intent intent = new Intent(FromDangNhap.this, FromBanGiay.class);
                    startActivity(intent);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        Bundle bundle = new Bundle();
        bundle.putString("fields", "name,email,first_name");
        graphRequest.setParameters(bundle);
        graphRequest.executeAsync();
    }
    public void DangNhaplogApi(){
        btn_log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FromDangNhap.this,FromBanGiay.class));
                finish();
            }
        });
    }
    public void DangKyTaiKhoan(){
        tv_dangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FromDangNhap.this,FromDangKy.class));
                //finish();
            }
        });
    }
}