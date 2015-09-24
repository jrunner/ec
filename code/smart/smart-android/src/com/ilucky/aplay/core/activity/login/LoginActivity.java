package com.ilucky.aplay.core.activity.login;

import java.util.Map;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.alibaba.fastjson.JSON;
import com.ilucky.aplay.R;
import com.ilucky.aplay.core.activity.MainActivity;
import com.ilucky.aplay.core.activity.common.Common;
import com.ilucky.aplay.core.consts.Consts;
import com.ilucky.aplay.core.http.HttpCondition;
import com.ilucky.aplay.util.http.HttpCallbackListener;
import com.ilucky.aplay.util.http.HttpUtil;
import com.ilucky.aplay.util.android.LogUtil;
import com.ilucky.aplay.util.android.ToastUtil;

/**
 * @author IluckySi
 * @since 20150728
 */
public class LoginActivity extends Activity implements OnClickListener {

	private String TAG = "LoginActivity";
	private Button backButton;
	private EditText phoneEditText;
	private EditText passwordEditText;
	private Button loginButton;
	private Button registerButton;
	private ProgressDialog loginProgressDialog;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		initViews();
	}
	
	private void initViews() {
		backButton = (Button)findViewById(R.id.activity_login_bn_back);
		backButton.setOnClickListener(this);
		phoneEditText = (EditText)findViewById(R.id.activity_login_et_phone);
		passwordEditText = (EditText)findViewById(R.id.activity_login_et_password);
		loginButton = (Button)findViewById(R.id.activity_login_bn_login);
		loginButton.setOnClickListener(this);
		registerButton = (Button)findViewById(R.id.activity_login_bn_register);
		registerButton.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch(v.getId()) {
		case R.id.activity_login_bn_back:
			finish();
			break;
		case R.id.activity_login_bn_login:
			login();
			break;	
		case R.id.activity_login_bn_register:
			register();
			break;
		default:
			break;
		}
	}
	
	
	private void login() {
		String phone = phoneEditText.getText().toString();
		String password = passwordEditText.getText().toString();
		if(TextUtils.isEmpty(phone) || TextUtils.isEmpty(password)) {
			ToastUtil.toast(Consts.LOGIN_ACTIVITY_EMPTY_PHONE_PASSWORD);
		} else {
			loginProgressDialog = ProgressDialog.show(LoginActivity.this, null, Consts.LOGIN_ACTIVITY_PROMPT, true);
			HttpUtil.post(Consts.LOGIN, new HttpCondition().getHttpCondition("phone", phone, "password", password), new HttpCallbackListener() {
				
				@Override
				public void onSuccess(String response) {
					LogUtil.d(TAG, response);
					@SuppressWarnings("unchecked")
					final Map<Object, Object> responseMap = (Map<Object, Object>)JSON.parse(response);
					if(Consts.SUCCESS.equals(responseMap.get(Consts.RESULT))) {
						if(Common.saveUserData(responseMap)) {
							Intent mainIntent = new Intent(LoginActivity.this, MainActivity.class);
							startActivity(mainIntent);
							finish();
						} 
					} 
					loginProgressDialog.dismiss();
					runOnUiThread(new Runnable() {               
		                public void run() {  
		                	ToastUtil.toast(responseMap.get(Consts.MESSAGE).toString());
		                }  
					});
				}

				@Override
				public void onFailure(final String response) {
					LogUtil.e(TAG, response);
					loginProgressDialog.dismiss();
					runOnUiThread(new Runnable() {               
		                public void run() {  
		                	ToastUtil.toast(Consts.SERVER);
		                }  
					});
				}
			});
		}
	}
	
	private void register() {
		Intent registerIntent = new Intent(this, RegisterActivity.class);
		startActivity(registerIntent);
		finish();
	}
}

