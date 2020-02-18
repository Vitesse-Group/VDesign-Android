package com.vitesse.group.vghomedecor.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.vitesse.group.vghomedecor.R;
import com.vitesse.group.vghomedecor.ui.models.ResponseObject;
import com.vitesse.group.vghomedecor.ui.network.ApiUtils;
import com.vitesse.group.vghomedecor.ui.network.RetrofitService;
import com.vitesse.group.vghomedecor.utils.ErrorConstants;
import com.vitesse.group.vghomedecor.utils.InputValidation;
import com.vitesse.group.vghomedecor.utils.Utility;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegistrationActivity extends AppCompatActivity {

    private EditText mEmailEditText;
    private EditText mMobileNoEditText;
    private EditText mPasswordEditText;
    private LinearLayout mLoginParentLayout;
    private Button mRegister;
    private Button mSignInImageView;
    private RelativeLayout mErrorLayout;
    private TextView mErrorTextView;
    private RetrofitService retrofitService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        retrofitService = ApiUtils.getUserService();

        mEmailEditText = (EditText)findViewById(R.id.edittext_email);
        mMobileNoEditText = (EditText)findViewById(R.id.edittext_mobile);
        mPasswordEditText = (EditText)findViewById(R.id.edittext_password);
        mRegister = (Button)findViewById(R.id.register);
        mLoginParentLayout = findViewById(R.id.login_parent);
        mSignInImageView = (Button)findViewById(R.id.register_btn);

        mEmailEditText.requestFocus();

        Utility.showSoftKeyboard(mEmailEditText);
        mErrorLayout = findViewById(R.id.error_layout);
        mErrorTextView = findViewById(R.id.error_text_view);
        mSignInImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent decoratioHomeIntent = new Intent(RegistrationActivity.this, LogInActivity.class);
                startActivity(decoratioHomeIntent);
                overridePendingTransition(R.anim.push_in_from_left_right, R.anim.push_out_from_right_left);
            }
        });
        mRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utility.hideSoftKeyboard(RegistrationActivity.this);
                if (validate()) {
                    Utility.showProgress(RegistrationActivity.this,"Please wait....");

                    doRegister(mEmailEditText.getText().toString().trim(),mMobileNoEditText.getText().toString().trim(),
                            mPasswordEditText.getText().toString().trim());
                    /*Intent decoratioHomeIntent = new Intent(RegistrationActivity.this, LogInActivity.class);
                    startActivity(decoratioHomeIntent);
                    overridePendingTransition(R.anim.push_in_from_left_right, R.anim.push_out_from_right_left);*/
                }

            }
        });
    }

    private boolean validate() {
            if (InputValidation.isValidEmail(mEmailEditText.getText().toString().trim())) {
                if (InputValidation.isValidPhoneNumber(mMobileNoEditText.getText().toString().trim())) {
                    if(mPasswordEditText.getText().toString().trim().length() > 0 && mPasswordEditText.getText().toString().trim().length() >=6) {
                        hideerrorLayout();
                        return true;
                    }else{
                        showerrorLayout("Please enter  Password more than 6 charecters");
                    }

                } else {
                    showerrorLayout("Please enter valid mobile number");
                }
            } else {
                showerrorLayout("Please enter valid email address");
            }
        return false;
    }

    private void showerrorLayout(String errorMsg){
        mErrorLayout.setVisibility(View.VISIBLE);
        mErrorTextView.setText(errorMsg);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mErrorLayout.setVisibility(View.GONE);
            }
        },2500);

    }
    private void hideerrorLayout(){
        mErrorLayout.setVisibility(View.GONE);

    }

    private void doRegister(final String emailid,final String mobile,final String password){
        Call<ResponseObject> call = retrofitService.register(emailid,mobile,password);
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                Log.d("baji","response==>"+response.toString());
                if(response.isSuccessful()){
                    ResponseObject resObj = (ResponseObject) response.body();
                    if(resObj.getCode()== ErrorConstants.VALID_CODE_200){
                        //login start main activity
                        Utility.dismissProgress();
                        Intent decoratioHomeIntent = new Intent(RegistrationActivity.this, LogInActivity.class);
                        startActivity(decoratioHomeIntent);
                        overridePendingTransition(R.anim.push_in_from_left_right, R.anim.push_out_from_right_left);

                    } else {
                        Toast.makeText(RegistrationActivity.this, "User already exist", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Utility.dismissProgress();
                    Toast.makeText(RegistrationActivity.this, "Error! Please try again!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Utility.dismissProgress();
                Toast.makeText(RegistrationActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
