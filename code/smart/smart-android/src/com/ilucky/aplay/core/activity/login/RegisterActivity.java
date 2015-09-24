package com.ilucky.aplay.core.activity.login;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Calendar;
import java.util.Map;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.DatePicker.OnDateChangedListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import com.alibaba.fastjson.JSON;
import com.ilucky.aplay.R;
import com.ilucky.aplay.core.activity.MainActivity;
import com.ilucky.aplay.core.activity.common.Common;
import com.ilucky.aplay.core.consts.Consts;
import com.ilucky.aplay.core.http.HttpCondition;
import com.ilucky.aplay.util.android.FileUtil;
import com.ilucky.aplay.util.android.LogUtil;
import com.ilucky.aplay.util.android.SdCardUtil;
import com.ilucky.aplay.util.android.ToastUtil;
import com.ilucky.aplay.util.http.HttpCallbackListener;
import com.ilucky.aplay.util.http.HttpUtil;

/**
 * @author IluckySi
 * @since 20150728
 */
public class RegisterActivity extends Activity implements OnClickListener  {

	private static final String TAG = "RegisterActivity";
	private Button backButton;
	private EditText phoneEditText;
	private EditText nameEditText;
	private EditText passwordEditText;
	private EditText passwordAgainEditText;
	private ImageView photoImageView;
	private Spinner sexSpinner;  
    private DatePicker birthdayDatePicker;
	private Button registerButton;
	private ProgressDialog registerProgressDialog;
	private String phone;
	private String name;
	private String password;
	private String passwordAgain;
	private File photoFile;
	private String sex;
	private String birthday;
	private File tempPhotoFile;
	private String[] photoChoices = new String[] {Consts.REGISTER_ACTIVITY_PHOTO_GALLERY, Consts.REGISTER_ACTIVITY_PHOTO_CAMERA};
	private ArrayAdapter<?> sexAdapter;
	private int birthdayYear;
	private int birthdayMonth;
	private int birthdayDay;
	private static final int GALLERY_REQUEST_CODE = 0;
	private static final int CAMERA_REQUEST_CODE = 1;
	private static final int RESULT_REQUEST_CODE = 2;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		initViews();
	}
	
	private void initViews() {
		backButton = (Button)findViewById(R.id.activity_register_bn_back);
		backButton.setOnClickListener(this);
		phoneEditText = (EditText)findViewById(R.id.activity_register_et_phone);
		nameEditText = (EditText)findViewById(R.id.activity_register_et_name);
		passwordEditText = (EditText)findViewById(R.id.activity_register_et_password);
		passwordAgainEditText = (EditText)findViewById(R.id.activity_register_et_password_again);
		photoImageView = (ImageView)findViewById(R.id.activity_register_iv_photo);
		photoImageView.setOnClickListener(this);
		sexSpinner = (Spinner)findViewById(R.id.activity_register_sr_sex);
		sexAdapter = ArrayAdapter.createFromResource(this, R.array.activity_register_sa_sex, android.R.layout.simple_spinner_item);  
		sexAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);  
		sexSpinner.setAdapter(sexAdapter);  
		sexSpinner.setOnItemSelectedListener(new SpinnerXMLSelectedListener());  
		birthdayDatePicker = (DatePicker)findViewById(R.id.activity_register_dp_birthday);
        initBirthday(true, birthdayYear, birthdayMonth, birthdayDay);
        birthdayDatePicker.init(birthdayYear, birthdayMonth, birthdayDay, new OnDateChangedListener(){
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            	 initBirthday(false, year, monthOfYear, dayOfMonth);
            }
        });
		registerButton = (Button)findViewById(R.id.activity_register_bn_register);
		registerButton.setOnClickListener(this);
	}
	
	@Override
	public void onClick(View v) {
		switch(v.getId()) {
		case R.id.activity_register_bn_back:
			finish();
			break;
		case R.id.activity_register_iv_photo:
			getPhoto();
			break;
		case R.id.activity_register_bn_register:
			register();
			break;	
		default:
			break;
		}
	}
	
	class SpinnerXMLSelectedListener implements OnItemSelectedListener{  
        public void onItemSelected(AdapterView<?> argo, View view, int position, long arg3) {  
        	sex = sexAdapter.getItem(position).toString();
        }

		@Override
		public void onNothingSelected(AdapterView<?> parent) {
		}  
    }  
	
	private void initBirthday(boolean flag, int year, int monthOfYear, int dayOfMonth) {
		if(flag) {
			Calendar calendar = Calendar.getInstance();
	        year = calendar.get(Calendar.YEAR);
	        monthOfYear = calendar.get(Calendar.MONTH);
	        dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
		}
	    birthdayYear = year;
    	birthdayMonth = monthOfYear;
    	birthdayDay = dayOfMonth;
	}

	private void getPhoto() {
		new AlertDialog.Builder(this).setTitle(Consts.REGISTER_ACTIVITY_PHOTO_SET).setItems(photoChoices, new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				File photoDir = FileUtil.getOtherDir("photo");
				photoFile = new File(photoDir.getPath() + File.separator + Consts.PHOTO);
				switch (which) {
				case GALLERY_REQUEST_CODE:
					Intent intentFromGallery = new Intent(Intent.ACTION_GET_CONTENT);
					intentFromGallery.setType("image/*");
					startActivityForResult(intentFromGallery, GALLERY_REQUEST_CODE);
					break;
				case CAMERA_REQUEST_CODE:
					if(SdCardUtil.hasSdcard()) {
						Intent intentFromCapture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
						tempPhotoFile = new File(Environment.getExternalStorageDirectory(), Consts.PHOTO);
						intentFromCapture.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(tempPhotoFile));
						startActivityForResult(intentFromCapture, CAMERA_REQUEST_CODE);
					}
				}
			}
		}).setNegativeButton(Consts.CANCEL, new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
			}
		}).show();
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		switch (requestCode) {
		case GALLERY_REQUEST_CODE:
			capturePhoto(data.getData());
			break;
		case CAMERA_REQUEST_CODE:
			capturePhoto(Uri.fromFile(tempPhotoFile));
			break;
		case RESULT_REQUEST_CODE:
			if (data != null) {
				setImageToView(data);
			}
			break;
		}
		super.onActivityResult(requestCode, resultCode, data);
	}

	private void capturePhoto(Uri uri) {
		Intent intent = new Intent("com.android.camera.action.CROP");
		intent.setDataAndType(uri, "image/*");
		intent.putExtra("crop", true);
		intent.putExtra("scale", true);
		intent.putExtra("aspectX", 1);
		intent.putExtra("aspectY", 1);
		intent.putExtra("outputX", 100);
		intent.putExtra("outputY", 100);
		intent.putExtra("return-data", true);
		startActivityForResult(intent, RESULT_REQUEST_CODE);
	}

	private void setImageToView(Intent data) {
		Bundle extras = data.getExtras();
		if (extras != null) {
			FileOutputStream fos = null;
			BufferedOutputStream bos = null;
			try {
				Bitmap photo = extras.getParcelable("data");
				fos = new FileOutputStream(photoFile.getPath(), false);
				bos = new BufferedOutputStream(fos);
				photo.compress(Bitmap.CompressFormat.PNG, 100, bos);  
				bos.flush();  
				photoImageView.setImageBitmap(photo);
			} catch (Exception e) {
				LogUtil.e(TAG, e.toString());
			} finally {
				try {
					if(bos != null) {
						bos.close();
					}
					if(fos != null) {
						fos.close();
					}
				} catch (Exception e) {
					LogUtil.e(TAG, e.toString());
				}
			}
		}
	}

	private void register() {
		phone = phoneEditText.getText().toString();
		name = nameEditText.getText().toString();
		password = passwordEditText.getText().toString();
		passwordAgain = passwordAgainEditText.getText().toString();
		if(TextUtils.isEmpty(phone) || TextUtils.isEmpty(name) || TextUtils.isEmpty(password) || TextUtils.isEmpty(passwordAgain)) {
			ToastUtil.toast(Consts.REGISTER_ACTIVITY_EMPTY_PHONE_NAME_PASSWORD);
		} else if(!password.equals(passwordAgain)) {
			ToastUtil.toast(Consts.REGISTER_ACTIVITY_NOT_SAME_PASSWORD);
		} else {
			sex = Consts.REGISTER_ACTIVITY_SEX_MAN.equals(sex) ? "true" : "false";
			birthday = birthdayYear + (birthdayMonth + 1 >= 10 ? (birthdayMonth + 1 + "") : ("0"+(birthdayMonth + 1))) +  (birthdayDay >= 10 ? (""+birthdayDay) : ("0"+birthdayDay));
			LogUtil.d(TAG, "phone="+phone+",name="+name+",password="+password+",sex="+sex+",birthday="+birthday+",photo="+ (photoFile == null ? null : photoFile.getName()) +",length="+(photoFile == null ? null : photoFile.length()));
			registerProgressDialog = ProgressDialog.show(RegisterActivity.this, null, Consts.REGISTER_ACTIVITY_PROMPT, true);
			HttpUtil.post2(Consts.REGISTER, new HttpCondition().getHttpCondition("phone", phone, "name", name, "password", password, "photo", photoFile, "sex", sex,  "birthday", birthday), new HttpCallbackListener() {
				@Override
				public void onSuccess(String response) {
					LogUtil.d(TAG, response);
					@SuppressWarnings("unchecked")
					final Map<Object, Object> responseMap = (Map<Object, Object>)JSON.parse(response);
					if(Consts.SUCCESS.equals(responseMap.get(Consts.RESULT))) {
						Common.renameUserPhoto(responseMap, photoFile);
						if(Common.saveUserData(responseMap)){
							Intent mainIntent = new Intent(RegisterActivity.this, MainActivity.class);
							startActivity(mainIntent);
							finish();
						}
					} 
					registerProgressDialog.dismiss();
					runOnUiThread(new Runnable() {               
		                public void run() {  
		                	ToastUtil.toast(responseMap.get(Consts.MESSAGE).toString());
		                }  
					});
				}

				@Override
				public void onFailure(final String response) {
					LogUtil.e(TAG, response);
					registerProgressDialog.dismiss();
					runOnUiThread(new Runnable() {               
		                public void run() {  
		                	ToastUtil.toast(Consts.SERVER);
		                }  
					});
				}
			});
		}
	}
}
