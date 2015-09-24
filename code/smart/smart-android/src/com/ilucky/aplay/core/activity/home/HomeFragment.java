package com.ilucky.aplay.core.activity.home;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import android.app.ActionBar.LayoutParams;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.alibaba.fastjson.JSON;
import com.ilucky.aplay.R;
import com.ilucky.aplay.core.application.AplayApplication;
import com.ilucky.aplay.core.consts.Consts;
import com.ilucky.aplay.core.http.HttpCondition;
import com.ilucky.aplay.util.android.FileUtil;
import com.ilucky.aplay.util.android.LogUtil;
import com.ilucky.aplay.util.file.FileDeleteUtil;
import com.ilucky.aplay.util.file.FileReadUtil;
import com.ilucky.aplay.util.http.HttpCallbackListener;
import com.ilucky.aplay.util.http.HttpUtil;
import com.ilucky.aplay.util.zip.UnZipUtil;

/**
 * @author IluckySi
 * @since 20150730
 */
public class HomeFragment extends Fragment implements OnClickListener, OnPageChangeListener {
	
	private static final String TAG = "HomeFragment";
	private Button sixtyButton;
	private Button seventyButton;
	private Button eightyButton;
	private Button nintyButton;
	private Button dlButton;
	private Button gtButton;
	private Button rhButton;
	private Button omButton;
	private Button lxButton;
	private Button ygButton;
	private Button myButton;
	private Button scButton;
	private Button xyButton;
	private Button sgButton;
	private Button lzButton;
	private Button tfButton;
	private Button playButton;
	private ProgressDialog playProgressDialog;
	private static final String PLAY_YEAR = "playYear";
	private static final String SIXTY = "SIXTY";
	private static final String SEVENTY = "SEVENTY";
	private static final String EIGHTY = "EIGHTY";
	private static final String NINTY = "NINTY";
	private static final String PLAY_REGION = "playRegion";
	private static final String DL = "DL";
	private static final String GT = "GT";
	private static final String RH = "RH";
	private static final String OM = "OM";
	private static final String PLAY_STYLE = "playStyle";
	private static final String LX = "LX";
	private static final String YG = "YG";
	private static final String MY = "MY";
	private static final String SC = "SC";
	private static final String PLAY_EMOTION = "playEmotion";
	private static final String XY = "XY";
	private static final String SG = "SG";
	private static final String LZ = "LZ";
	private static final String TF = "TF";
	private Map<String, List<String>> selectedPlayDimensions = new HashMap<String, List<String>>();
	
	private int width;
	private int newWidth;
	private int padding;
	private ViewPager mViewpager;
	private ViewPagerAdapter mViewPagerAdp;
	private ImageView[][] mImageViews;
	private int[] mImageRes = { R.drawable.a, R.drawable.b, R.drawable.c, R.drawable.d, R.drawable.e };
	private ImageView[] mDots;
	private LinearLayout layoutDots;
	private final long delay = 3 * 1000;
	private final int AUTO = 0;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_home, container,false);
		initViews(view);
		return view;
	}
	
	private void initViews(View view) {
		sixtyButton = (Button)view.findViewById(R.id.activity_home_bn_sixty);
		sixtyButton.setOnClickListener(this);
		seventyButton = (Button)view.findViewById(R.id.activity_home_bn_seventy);
		seventyButton.setOnClickListener(this);
		eightyButton = (Button)view.findViewById(R.id.activity_home_bn_eighty);
		eightyButton.setOnClickListener(this);
		nintyButton = (Button)view.findViewById(R.id.activity_home_bn_ninty);
		nintyButton.setOnClickListener(this);
		dlButton = (Button)view.findViewById(R.id.activity_home_bn_dl);
		dlButton.setOnClickListener(this);
		gtButton = (Button)view.findViewById(R.id.activity_home_bn_gt);
		gtButton.setOnClickListener(this);
		rhButton = (Button)view.findViewById(R.id.activity_home_bn_rh);
		rhButton.setOnClickListener(this);
		omButton = (Button)view.findViewById(R.id.activity_home_bn_om);
		omButton.setOnClickListener(this);
		lxButton = (Button)view.findViewById(R.id.activity_home_bn_lx);
		lxButton.setOnClickListener(this);
		ygButton = (Button)view.findViewById(R.id.activity_home_bn_yg);
		ygButton.setOnClickListener(this);
		myButton = (Button)view.findViewById(R.id.activity_home_bn_my);
		myButton.setOnClickListener(this);
		scButton = (Button)view.findViewById(R.id.activity_home_bn_sc);
		scButton.setOnClickListener(this);
		xyButton = (Button)view.findViewById(R.id.activity_home_bn_xy);
		xyButton.setOnClickListener(this);
		sgButton = (Button)view.findViewById(R.id.activity_home_bn_sg);
		sgButton.setOnClickListener(this);
		lzButton = (Button)view.findViewById(R.id.activity_home_bn_lz);
		lzButton.setOnClickListener(this);
		tfButton = (Button)view.findViewById(R.id.activity_home_bn_tf);
		tfButton.setOnClickListener(this);
		playButton = (Button)view.findViewById(R.id.activity_home_bn_play);
		playButton.setOnClickListener(this);
		width = getResources().getDisplayMetrics().widthPixels;
		newWidth = (int) (divideWidth(width, 1080, 6) * 17);
		padding = (int) (divideWidth(width, 1080, 6) * 9);  
		mViewpager = (ViewPager) view.findViewById(R.id.activity_home_vp_ads);
		layoutDots = (LinearLayout) view.findViewById(R.id.activity_home_ll_ads);
		mViewpager.setOnPageChangeListener(this);
		initDots();
		initViewPager();
	}
	
	@Override
	public void onClick(View v) {
		Button b = (Button)v;
		switch(v.getId()) {
			case R.id.activity_home_bn_sixty:
				selectedPlayDimensions(b, PLAY_YEAR, SIXTY);
				break;
			case R.id.activity_home_bn_seventy:
				selectedPlayDimensions(b, PLAY_YEAR, SEVENTY);
				break;
			case R.id.activity_home_bn_eighty:
				selectedPlayDimensions(b, PLAY_YEAR, EIGHTY);
				break;
			case R.id.activity_home_bn_ninty:
				selectedPlayDimensions(b, PLAY_YEAR, NINTY);
				break;
			case R.id.activity_home_bn_dl:
				selectedPlayDimensions(b, PLAY_REGION, DL);
				break;
			case R.id.activity_home_bn_gt:
				selectedPlayDimensions(b, PLAY_REGION, GT);
				break;
			case R.id.activity_home_bn_rh:
				selectedPlayDimensions(b, PLAY_REGION, RH);
				break;
			case R.id.activity_home_bn_om:
				selectedPlayDimensions(b, PLAY_REGION, OM);
				break;
			case R.id.activity_home_bn_lx:
				selectedPlayDimensions(b, PLAY_STYLE, LX);
				break;
			case R.id.activity_home_bn_yg:
				selectedPlayDimensions(b, PLAY_STYLE, YG);
				break;
			case R.id.activity_home_bn_my:
				selectedPlayDimensions(b, PLAY_STYLE, MY);
				break;
			case R.id.activity_home_bn_sc:
				selectedPlayDimensions(b, PLAY_STYLE, SC);
				break;
			case R.id.activity_home_bn_xy:
				selectedPlayDimensions(b, PLAY_EMOTION, XY);
				break;
			case R.id.activity_home_bn_sg:
				selectedPlayDimensions(b, PLAY_EMOTION, SG);
				break;
			case R.id.activity_home_bn_lz:
				selectedPlayDimensions(b, PLAY_EMOTION, LZ);
				break;
			case R.id.activity_home_bn_tf:
				selectedPlayDimensions(b, PLAY_EMOTION, TF);
				break;
			case R.id.activity_home_bn_play:
				play();
				break;
		}
	}
	
	private void selectedPlayDimensions(Button b, String big, String small) {
		List<String> smalls = selectedPlayDimensions.get(big);
		if(smalls == null) {
			smalls = new ArrayList<String>();
			selectedPlayDimensions.put(big, smalls);
		}
		if(smalls.contains(small)) {
			smalls.remove(small);
			b.setTextColor(Color.BLACK);
		} else {
			smalls.add(small);
			b.setTextColor(Color.RED);
		}
	}
	
	private void play() {
		final File resourceDir = FileUtil.getOtherDir("resource");
		FileDeleteUtil.delete(resourceDir.getPath(), false);
		playProgressDialog = ProgressDialog.show(getActivity(), null, Consts.HOME_FRAGMENT_PROMPT, true);
		HttpCondition httpCondition = new HttpCondition();
		for(Entry<String, List<String>> entry : selectedPlayDimensions.entrySet()) {
			String key = entry.getKey();
			List<String> value = entry.getValue();
			if(value != null && value.size() > 0) {
				httpCondition.put(key, JSON.toJSONString(value));
				Log.d(TAG, JSON.toJSONString(value));
			}
		}
		HttpUtil.post(Consts.GET_LIST_BY_RANDOM, httpCondition.getHttpCondition("id", "36e5d05006c54bb5852cb73ad619d75f"), new HttpCallbackListener() {

			@Override
			public void onSuccess(String response) {
				LogUtil.d(TAG, response);
				@SuppressWarnings("unchecked")
				final Map<Object, Object> responseMap = (Map<Object, Object>)JSON.parse(response);
				if(Consts.SUCCESS.equals(responseMap.get(Consts.RESULT))) {
					final String resourceUrl = responseMap.get(Consts.HOME_FRAGMENT_URL).toString();
					final String resourceName = resourceUrl.substring(resourceUrl.lastIndexOf("/") + 1).split("\\.")[0];
					String resourceZipFile = resourceDir.getPath() + File.separator + resourceName +  Consts.ZIP;
					if(HttpUtil.download(resourceUrl, resourceZipFile)) {
						playProgressDialog.dismiss();
						UnZipUtil unZipUtil = new UnZipUtil();
						unZipUtil.setSrcPath(resourceDir.getPath() + File.separator + resourceName + Consts.ZIP);
						unZipUtil.setDstPath(resourceDir.getPath());
						unZipUtil.startUnZip();
						File resourceTxtFile = new File(resourceDir.getPath() + File.separator + resourceName + Consts.TXT);
						String result = FileReadUtil.readFile(resourceTxtFile);
						LogUtil.d(TAG, result);
						@SuppressWarnings("unchecked")
						final Map<String, List<String>> resultMap = (Map<String, List<String>>)JSON.parse(result);
						Intent startPkIntent = new Intent(getActivity(), PlayActivity.class);
				        startPkIntent.putExtra("playMap", JSON.toJSONString(resultMap));
				        startActivity(startPkIntent);
					} else {
						LogUtil.e(TAG, response);
						playProgressDialog.dismiss();
					}
				} else {
					playProgressDialog.dismiss();
				}
			}

			@Override
			public void onFailure(final String response) {
				LogUtil.e(TAG, response);
				playProgressDialog.dismiss();
			}
		});
	}

	public double divideWidth(int screenWidth, int picWidth, int retainValue) {
		BigDecimal screenBD = new BigDecimal(Double.toString(screenWidth));
		BigDecimal picBD = new BigDecimal(Double.toString(picWidth));
		return screenBD.divide(picBD, retainValue, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	private void initDots() {
		mDots = new ImageView[mImageRes.length];
		for (int i = 0; i < mImageRes.length; i++) {
			ImageView iv = new ImageView(AplayApplication.getContext());
			LayoutParams lp = new LayoutParams(newWidth, newWidth);
			lp.leftMargin = padding;
			lp.rightMargin = padding;
			lp.topMargin = padding;
			lp.bottomMargin = padding;
			iv.setLayoutParams(lp);
			iv.setImageResource(R.drawable.dot_normal);
			layoutDots.addView(iv);
			mDots[i] = iv;
		}
		mDots[0].setImageResource(R.drawable.dot_selected);
	}

	private void initViewPager() {
		mImageViews = new ImageView[2][];
		mImageViews[0] = new ImageView[mImageRes.length];
		mImageViews[1] = new ImageView[mImageRes.length];
		for (int i = 0; i < mImageViews.length; i++) {
			for (int j = 0; j < mImageViews[i].length; j++) {
				ImageView iv = new ImageView(AplayApplication.getContext());
				iv.setBackgroundResource(mImageRes[j]);
				mImageViews[i][j] = iv;
			}
		}
		mViewPagerAdp = new ViewPagerAdapter(mImageViews, mImageRes);
		mViewpager.setAdapter(mViewPagerAdp);
		mViewpager.setCurrentItem(mImageRes.length * 50);
		mHandler.sendEmptyMessageDelayed(AUTO, delay);
	}

	private Handler mHandler = new Handler() {
		@Override
		public void dispatchMessage(Message msg) {
			switch (msg.what) {
			case AUTO:
				int index = mViewpager.getCurrentItem();
				mViewpager.setCurrentItem(index + 1);
				mHandler.sendEmptyMessageDelayed(AUTO, delay);
				break;
			default:
				break;
			}
		};
	};

	private void setCurrentDot(int currentPosition) {
		for (int i = 0; i < mDots.length; i++) {
			if (i == currentPosition) {
				mDots[i].setImageResource(R.drawable.dot_selected);
			} else {
				mDots[i].setImageResource(R.drawable.dot_normal);
			}
		}
	}

	@Override
	public void onPageScrollStateChanged(int arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPageSelected(int arg0) {
		// TODO Auto-generated method stub
		
	}
}

