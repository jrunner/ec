package com.ilucky.aplay.core.activity.home;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.ilucky.aplay.R;
import com.ilucky.aplay.core.activity.MainActivity;
import com.ilucky.aplay.core.consts.Consts;
import com.ilucky.aplay.core.http.HttpCondition;
import com.ilucky.aplay.util.android.FileUtil;
import com.ilucky.aplay.util.android.LogUtil;
import com.ilucky.aplay.util.android.ToastUtil;
import com.ilucky.aplay.util.http.HttpCallbackListener;
import com.ilucky.aplay.util.http.HttpUtil;

/**
 * @author IluckySi
 * @since 20150813
 */
public class PlayActivity extends Activity implements OnClickListener {

	private static final String TAG = "PlayActivity";
	private Button backButton;
	private ImageView playImageView;
	private LinearLayout answerLinerLayout;
	private LinearLayout aLinearLayout;
	private LinearLayout bLinearLayout;
	private LinearLayout cLinearLayout;
	private LinearLayout dLinearLayout;
	private LinearLayout eLinearLayout;
	private TextView aTextView;
	private TextView bTextView;
	private TextView cTextView;
	private TextView dTextView;
	private TextView eTextView;
	private Button nextButton;
	private Map<String, List<String>> playMap = new HashMap<String, List<String>>();
	private List<String> playList = new ArrayList<String>();
	private String currentPlay;
	private int currentPlayListIndex = 1;
	private MediaPlayer mediplayer;
	private static final int A = 0;
	private static final int B = 1;
	private static final int C = 2;
	private static final int D = 3;
	private static final int E = 4;
	private static final int COUNT = 5;
	private int score = 0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_play);
		initViews();
		initData();
	}
	
	private void initViews() {
		backButton = (Button)findViewById(R.id.activity_play_bn_back);
		backButton.setOnClickListener(this);
		playImageView = (ImageView)findViewById(R.id.activity_play_iv_play);
		playImageView.setOnClickListener(this);
		answerLinerLayout = (LinearLayout)findViewById(R.id.activity_play_ll_answer);
		aLinearLayout = (LinearLayout)findViewById(R.id.activity_play_ll_a);
		aLinearLayout.setOnClickListener(this);
		bLinearLayout = (LinearLayout)findViewById(R.id.activity_play_ll_b);
		bLinearLayout.setOnClickListener(this);
		cLinearLayout = (LinearLayout)findViewById(R.id.activity_play_ll_c);
		cLinearLayout.setOnClickListener(this);
		dLinearLayout = (LinearLayout)findViewById(R.id.activity_play_ll_d);
		dLinearLayout.setOnClickListener(this);
		eLinearLayout = (LinearLayout)findViewById(R.id.activity_play_ll_e);
		eLinearLayout.setOnClickListener(this);
		aTextView = (TextView)findViewById(R.id.activity_play_tv_a);
		bTextView = (TextView)findViewById(R.id.activity_play_tv_b);
		cTextView = (TextView)findViewById(R.id.activity_play_tv_c);
		dTextView = (TextView)findViewById(R.id.activity_play_tv_d);
		eTextView = (TextView)findViewById(R.id.activity_play_tv_e);
		nextButton = (Button)findViewById(R.id.activity_play_bn_next);
		nextButton.setOnClickListener(this);
	}
	
	@SuppressWarnings("unchecked")
	private void initData() {
		playMap = (Map<String, List<String>>)JSON.parse(getIntent().getExtras().get("playMap").toString());
		setData();
	}
	
	private void setData() {
		for(Entry<String, List<String>> entry : playMap.entrySet()) {
			currentPlay = entry.getKey();
			playList = entry.getValue();
			aTextView.setText(playList.get(0));
			bTextView.setText(playList.get(1));
			cTextView.setText(playList.get(2));
			dTextView.setText(playList.get(3));
			eTextView.setText(playList.get(4));
			initMediplayer();
			playMap.remove(currentPlay);
			break;
		}
	}

	@Override
	public void onClick(View v) {
		switch(v.getId()) {
		case R.id.activity_play_bn_back:
			finish();
			break;
		case R.id.activity_play_iv_play:
			play();
			break;
		case R.id.activity_play_ll_a:
			choose(A);
			break;
		case R.id.activity_play_ll_b:
			choose(B);
			break;
		case R.id.activity_play_ll_c:
			choose(C);
			break;
		case R.id.activity_play_ll_d:
			choose(D);
			break;
		case R.id.activity_play_ll_e:
			choose(E);
			break;
		case R.id.activity_play_bn_next:
			next();
			break;
		default:
			break;
		}
	}
	
	private void choose(int choose) {
		switch(choose) {
		case A:
			judge(aTextView.getText().toString());
			break;
		case B:
			judge(bTextView.getText().toString());
			break;
		case C:
			judge(cTextView.getText().toString());
			break;
		case D:
			judge(dTextView.getText().toString());
			break;
		case E:
			judge(eTextView.getText().toString());
			break;
		}
	}
	
	private void judge(String alternativeAnswer) {
		enableChildren(false);
		if(currentPlay.equals(alternativeAnswer)) {
			ToastUtil.toast(Consts.PLAY_ACTIVITY_RIGHT);
			score+=1;
		} else {
			ToastUtil.toast(Consts.PLAY_ACTIVITY_WRONG+currentPlay);
		}
	}
	
	private void play() {
		if (mediplayer.isPlaying()) {
			mediplayer.pause(); 
			playImageView.setImageResource(R.drawable.ic_launcher);
		} else {
			mediplayer.start(); 
			playImageView.setImageResource(R.drawable.back);
		}
	}
	
	private void initMediplayer() {
		mediplayer = null;
		FileInputStream fis = null;
		try {
			File resourceFile = FileUtil.getOtherDir("resource");
			if(!resourceFile.exists()) {
				resourceFile.mkdirs();
			}
			File resourceMp3File = new File(resourceFile.getPath() + File.separator + currentPlay + Consts.MP3);
			fis = new FileInputStream(resourceMp3File);
			mediplayer= new MediaPlayer();
			mediplayer.setDataSource(fis.getFD()); 
			mediplayer.prepare();
			mediplayer.start(); 
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(fis != null) {
					fis.close();
					fis = null;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	private void next() {
		if (mediplayer.isPlaying()) {
			mediplayer.pause(); 
		}
		if (mediplayer != null) {
			mediplayer.stop();
			mediplayer.release();
		}
		if(currentPlayListIndex == COUNT) {
			submitScore();
		} else {
			currentPlayListIndex++;
			if(currentPlayListIndex == COUNT) {
				nextButton.setText(Consts.PLAY_ACTIVITY_SUBMIT);
			}
			setData();
		}
		enableChildren(true);
	}
	
	private void submitScore() {
		HttpUtil.post(Consts.SUBMIT_SCORE, new HttpCondition().getHttpCondition("id", "36e5d05006c54bb5852cb73ad619d75f", "score", score), new HttpCallbackListener() {
			
			@Override
			public void onSuccess(String response) {
				LogUtil.d(TAG, response);
				@SuppressWarnings("unchecked")
				final Map<Object, Object> responseMap = (Map<Object, Object>)JSON.parse(response);
				if(Consts.SUCCESS.equals(responseMap.get(Consts.RESULT))) {
					Intent mainIntent = new Intent(PlayActivity.this, MainActivity.class);
					startActivity(mainIntent);
					finish();
				} 
				runOnUiThread(new Runnable() {               
	                public void run() {  
	                	ToastUtil.toast(responseMap.get(Consts.MESSAGE).toString());
	                }  
				});
			}
			
			@Override
			public void onFailure(String response) {
				LogUtil.e(TAG, response);
			}
		});
	}
	
	private void enableChildren(boolean flag) {
		for ( int i = 0; i < answerLinerLayout.getChildCount();  i++ ){
		    View child = answerLinerLayout.getChildAt(i);
		    child.setEnabled(flag);
		}
	}
}
