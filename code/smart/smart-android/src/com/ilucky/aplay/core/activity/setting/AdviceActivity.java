package com.ilucky.aplay.core.activity.setting;

import java.util.Map;

import org.apache.http.util.TextUtils;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.alibaba.fastjson.JSON;
import com.ilucky.aplay.R;
import com.ilucky.aplay.core.consts.Consts;
import com.ilucky.aplay.core.http.HttpCondition;
import com.ilucky.aplay.util.android.LogUtil;
import com.ilucky.aplay.util.android.SharedPreferenceUtil;
import com.ilucky.aplay.util.android.ToastUtil;
import com.ilucky.aplay.util.http.HttpCallbackListener;
import com.ilucky.aplay.util.http.HttpUtil;

/**
 * @author IluckySi
 * @since 20150814
 */
public class AdviceActivity extends Activity implements OnClickListener {

	private String TAG = "AdviceActivity";
	private Button backButton;
	private EditText adviceEditText;
	private Button saveButton;
	private ProgressDialog adviceProgressDialog;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_advice);
		initViews();
	}
	
	private void initViews() {
		backButton = (Button)findViewById(R.id.activity_advice_bn_back);
		backButton.setOnClickListener(this);
		adviceEditText = (EditText)findViewById(R.id.activity_advice_et_advice);
		saveButton = (Button)findViewById(R.id.activity_advice_bn_save);
		saveButton.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch(v.getId()) {
		case R.id.activity_advice_bn_back:
			finish();
			break;
		case R.id.activity_advice_bn_save:
			save();
			break;
		default:
			break;
		}
	}
	
	private void save() {
		String advice = adviceEditText.getText().toString();
		if(TextUtils.isEmpty(advice) || (advice.length() < 12 || advice.length() > 120)) {
			ToastUtil.toast(Consts.ADVICE_ACTIVITY_VALIDATE_ADVICE);
		} else {
			adviceProgressDialog = ProgressDialog.show(AdviceActivity.this, null, Consts.ADVICE_ACTIVITY_PROMPT, true);
			String id = SharedPreferenceUtil.read("user", "id");
			HttpUtil.post(Consts.SUBMIT_ADVICE, new HttpCondition().getHttpCondition("id", id, "advice", advice), new HttpCallbackListener() {
				
				@Override
				public void onSuccess(String response) {
					LogUtil.d(TAG, response);
					@SuppressWarnings("unchecked")
					final Map<Object, Object> responseMap = (Map<Object, Object>)JSON.parse(response);
					if(Consts.SUCCESS.equals(responseMap.get(Consts.RESULT))) {
						finish();
					} 
					adviceProgressDialog.dismiss();
					runOnUiThread(new Runnable() {               
		                public void run() {  
		                	ToastUtil.toast(responseMap.get(Consts.MESSAGE).toString());
		                }  
					});
				}

				@Override
				public void onFailure(final String response) {
					adviceProgressDialog.dismiss();
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
