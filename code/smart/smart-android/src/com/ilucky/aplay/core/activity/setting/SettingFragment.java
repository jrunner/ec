package com.ilucky.aplay.core.activity.setting;

import java.io.File;

import org.apache.http.util.TextUtils;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ilucky.aplay.R;
import com.ilucky.aplay.core.activity.MainActivity;
import com.ilucky.aplay.core.activity.login.LoginActivity;
import com.ilucky.aplay.core.consts.Consts;
import com.ilucky.aplay.core.http.HttpCondition;
import com.ilucky.aplay.util.android.FileUtil;
import com.ilucky.aplay.util.android.LogUtil;
import com.ilucky.aplay.util.android.SharedPreferenceUtil;
import com.ilucky.aplay.util.android.ToastUtil;
import com.ilucky.aplay.util.http.HttpCallbackListener;
import com.ilucky.aplay.util.http.HttpUtil;

/**
 * @author IluckySi
 * @since 20150729
 */
public class SettingFragment extends Fragment implements OnClickListener {
	
	private static final String TAG = "SettingFragment";
	private LinearLayout userLinearLayout;
	private ImageView photoImageView;
	private TextView nameTextView;
	private ImageView sexImageView;
	private TextView totalScoreTextView;
	private RelativeLayout helpRelativeLayout;
	private RelativeLayout adviceRelativeLayout;
	private RelativeLayout updateRelativeLayout;
	private LinearLayout loginLinearLayout;
	private TextView loginTextView;
	private String id;
	private static int USER_STATE = 1;
	private static final int LOGIN = 1;
	private static final int LOGOUT = 2;
	private static final int[] sexs = {R.drawable.fragment_rank_itme_iv_sex_man, R.drawable.fragment_rank_itme_iv_sex_women};
	private static final int USER = 0;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_setting, container,false);
		initViews(view);
		initData();
		return view;
	}
	
	private void initViews(View view) {
		userLinearLayout = (LinearLayout)view.findViewById(R.id.fragment_setting_ll_user);
		userLinearLayout.setOnClickListener(this);
		photoImageView = (ImageView)view.findViewById(R.id.fragment_setting_iv_photo);
		nameTextView = (TextView)view.findViewById(R.id.fragment_setting_tv_name);
		sexImageView = (ImageView)view.findViewById(R.id.fragment_setting_iv_sex);
		totalScoreTextView = (TextView)view.findViewById(R.id.fragment_setting_tv_total_score);
		helpRelativeLayout = (RelativeLayout)view.findViewById(R.id.fragment_setting_rl_help);
		helpRelativeLayout.setOnClickListener(this);
		adviceRelativeLayout = (RelativeLayout)view.findViewById(R.id.fragment_setting_rl_advice);
		adviceRelativeLayout.setOnClickListener(this);
		updateRelativeLayout = (RelativeLayout)view.findViewById(R.id.fragment_setting_rl_update);
		updateRelativeLayout.setOnClickListener(this);
		loginLinearLayout = (LinearLayout)view.findViewById(R.id.fragment_setting_ll_login);
		loginLinearLayout.setOnClickListener(this);
		loginTextView = (TextView)view.findViewById(R.id.fragment_setting_tv_login);
	}
	
	private void initData() {
		id = SharedPreferenceUtil.read("user", "id");
		if(!TextUtils.isEmpty(id)) {
			USER_STATE = LOGIN;
			loginTextView.setText(Consts.SETTING_FRAGMENT_LOGOUT);
			userLinearLayout.setVisibility(View.VISIBLE);
			File photoDir = FileUtil.getOtherDir("photo");
			String photoName = id + Consts.PNG;
			File photoFile = new File(photoDir.getPath() + File.separator + photoName);
			Bitmap bitmap = null;
		    if(!photoFile.exists()) {
		    	bitmap = BitmapFactory.decodeStream(FileUtil.getAssestInputStream("photo" + File.separator + Consts.DEFAULT_PHOTO));
		    } else {
		    	bitmap = BitmapFactory.decodeFile(photoFile.getPath());
		    }
		    photoImageView.setImageBitmap(bitmap);
			nameTextView.setText(SharedPreferenceUtil.read("user", "name"));
			sexImageView.setImageResource(Boolean.valueOf(SharedPreferenceUtil.read("user", "sex")) == true ? sexs[0] : sexs[1]);
			totalScoreTextView.setText(SharedPreferenceUtil.read("user",  "totalScore"));
		} else {
			USER_STATE = LOGOUT;
			loginTextView.setText(Consts.SETTING_FRAGMENT_LOGIN);
			userLinearLayout.setVisibility(View.GONE);
		}
	}

	@Override
	public void onClick(View v) {
		if(TextUtils.isEmpty(id)) {
			login();
		} else {
			switch(v.getId()) {
			case R.id.fragment_setting_ll_user:
				user();
				break;
			case R.id.fragment_setting_rl_help:
				help();
				break;
			case R.id.fragment_setting_rl_advice:
				advice();
				break;
			case R.id.fragment_setting_rl_update:
				update();
				break;
			case R.id.fragment_setting_ll_login:
				switch(USER_STATE) {
				case LOGIN:
					logout();
					break;
				case LOGOUT:
					login();
					break;
				default:
					break;
				}
				break;
			default:
				break;
			}
		}
	}
	
	private void login() {
		Intent loginIntent = new Intent(getActivity(), LoginActivity.class);
		startActivity(loginIntent);
	}
	
	private void logout() {
		new AlertDialog.Builder(getActivity()).setMessage(Consts.SETTING_FRAGMENT_LOGOUT_PROMPT)
		.setPositiveButton(Consts.OK, new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
				SharedPreferenceUtil.write("user", "id", "");
				Intent mainIntent = new Intent(getActivity(), MainActivity.class);
				startActivity(mainIntent);
				getActivity().finish();
				
			}
		}).setNegativeButton(Consts.CANCEL, new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
			}
		}).show();
	}
	
	private void user() {
		Intent userIntent = new Intent(getActivity(), UserActivity.class);
		startActivityForResult(userIntent, USER);
	}
	
	private void help() {
		Intent helpIntent = new Intent(getActivity(), HelpActivity.class);
		startActivity(helpIntent);
	}
	
	private void advice() {
		Intent adviceIntent = new Intent(getActivity(), AdviceActivity.class);
		startActivity(adviceIntent);
	}
	
	//TODO:1.1.0
	private void update(){
		HttpUtil.post(Consts.GET_LATEST_VERSION_INSTALLED_PACKAGE, new HttpCondition().getHttpCondition("id", id, "oldInstallPackageName", "aplay-android-1.0.0.txt"), new HttpCallbackListener() {
			
			@Override
			public void onSuccess(String response) {
				LogUtil.d(TAG, response);
				getActivity().runOnUiThread(new Runnable() {            
	                public void run() {  
	                	ToastUtil.toast(Consts.SETTING_FRAGMENT_PROMT);
	                }  
				});
			}

			@Override
			public void onFailure(final String response) {
				getActivity().runOnUiThread(new Runnable() {               
	                public void run() {  
	                	ToastUtil.toast(Consts.SERVER);
	                }  
				});
			}
		});
	}
	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		switch(requestCode) {
		case USER:
			initData();
			break;
		default:
			break;
		}
	}
}
