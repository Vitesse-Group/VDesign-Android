package com.vitesse.group.vghomedecor.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
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
import com.vitesse.group.vghomedecor.utils.PreferenceUtil;
import com.vitesse.group.vghomedecor.utils.Utility;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LogInActivity extends AppCompatActivity {

  private EditText mEmailEditText;
  private EditText mMobileNoEditText;
  private LinearLayout mLoginParentLayout;
  private Button mSignIn;
  private Button mRegister;
  private RelativeLayout mErrorLayout;
  private TextView mErrorTextView;
  private CheckBox mRememberCheckBox;
  private String mOTP = "123456";
    private RetrofitService retrofitService;

    @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_sign_in);
        retrofitService = ApiUtils.getUserService();

    mEmailEditText = (EditText)findViewById(R.id.edittext_email);
    mMobileNoEditText = (EditText)findViewById(R.id.edittext_mobile_number);
    mSignIn = (Button)findViewById(R.id.signInBtn);
    mLoginParentLayout = findViewById(R.id.login_parent);
     mRememberCheckBox = findViewById(R.id.remember_me_checkbox);
      mRegister = findViewById(R.id.register_btn);
    mEmailEditText.requestFocus();
    mRememberCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if(isChecked){
                PreferenceUtil.getInstance(LogInActivity.this).saveBooleanParam(PreferenceUtil.IS_LOGIN_REMEMBER_ME, true);
            }else{
                PreferenceUtil.getInstance(LogInActivity.this).saveBooleanParam(PreferenceUtil.IS_LOGIN_REMEMBER_ME, false);
            }
        }
    });
    Utility.showSoftKeyboard(mEmailEditText);
      mErrorLayout = findViewById(R.id.error_layout);
      mErrorTextView = findViewById(R.id.error_text_view);
      mRegister.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              Intent decoratioHomeIntent = new Intent(LogInActivity.this, RegistrationActivity.class);
              startActivity(decoratioHomeIntent);
              overridePendingTransition(R.anim.push_in_from_left_right, R.anim.push_out_from_right_left);
          }
      });
      mSignIn.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              //if(mReqVerifyOtp.getText().equals(getResources().getString(R.string.sign_in_verify_otp_button))){
              Utility.hideSoftKeyboard(LogInActivity.this);
              if (validate()) {
                  Utility.showProgress(LogInActivity.this,"Please wait....");
                  doLogin(mEmailEditText.getText().toString(),mMobileNoEditText.getText().toString());
                  /*Intent decoratioHomeIntent = new Intent(LogInActivity.this, HomeActivity.class);
                  startActivity(decoratioHomeIntent);
                  overridePendingTransition(R.anim.push_in_from_left_right, R.anim.push_out_from_right_left);*/
              }

          }
      });
  }

    private boolean validate() {
      if(InputValidation.isValidEmail(mEmailEditText.getText().toString().trim())){
          if(mMobileNoEditText.getText().toString().trim().length() > 0 && mMobileNoEditText.getText().toString().trim().length() >=6){
              hideerrorLayout();
              return true;
          }else{
              showerrorLayout("Please enter valid password");
          }
      }else{
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
    private void doLogin(final String username,final String password){
        Call<ResponseObject> call = retrofitService.login(username,"a",password);
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                Log.d("baji","response==>"+response.toString());
                if(response.isSuccessful()){
                    ResponseObject resObj = (ResponseObject) response.body();
                    if(resObj.getCode()== ErrorConstants.VALID_CODE_200){
                        //login start main activity

                        Intent decoratioHomeIntent = new Intent(LogInActivity.this, HomeActivity.class);
                        startActivity(decoratioHomeIntent);
                        overridePendingTransition(R.anim.push_in_from_left_right, R.anim.push_out_from_right_left);
                        Utility.dismissProgress();
                    } else {
                        Utility.dismissProgress();
                        Toast.makeText(LogInActivity.this, "The username or password is incorrect", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Utility.dismissProgress();
                    Toast.makeText(LogInActivity.this, "Error! Please try again!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Utility.dismissProgress();
                Toast.makeText(LogInActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}


