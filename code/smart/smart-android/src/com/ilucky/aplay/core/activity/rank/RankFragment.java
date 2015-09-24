package com.ilucky.aplay.core.activity.rank;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.util.TextUtils;

import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.alibaba.fastjson.JSON;
import com.ilucky.aplay.R;
import com.ilucky.aplay.core.client.Message;
import com.ilucky.aplay.core.consts.Consts;
import com.ilucky.aplay.core.http.HttpCondition;
import com.ilucky.aplay.core.model.Rank;
import com.ilucky.aplay.util.android.FileUtil;
import com.ilucky.aplay.util.android.LogUtil;
import com.ilucky.aplay.util.android.ToastUtil;
import com.ilucky.aplay.util.http.HttpCallbackListener;
import com.ilucky.aplay.util.http.HttpUtil;

/**
 * @author IluckySi
 * @since 20150724
 */
public class RankFragment extends Fragment implements OnClickListener {
	
	private static final String TAG = "RankFragment";
	private EditText searchEditText;
	private Button searchButton;
	private ListView userListView;
	private ProgressDialog rankProgressDialog;
	private RankAdapter rankAdapter;
	private OnlineBroadcastReceiver onlineBroadcastReceiver;
	private static int STATE = 1;
	private static final int SEARCH = 1;
	private static final int CANCEL = 2;
	private static List<Rank> rankList = new ArrayList<Rank>();
	private static final int FIRST = 0;
	private static final int LAST = 12;
	private static final int[] sexs = {R.drawable.fragment_rank_itme_iv_sex_man, R.drawable.fragment_rank_itme_iv_sex_women};
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		getUserScoreRankByPage();
		View view = inflater.inflate(R.layout.fragment_rank, container,false);
		searchEditText = (EditText)view.findViewById(R.id.fragment_rank_et_search);
		searchButton = (Button)view.findViewById(R.id.fragment_rank_bn_search);
		searchButton.setOnClickListener(this);
		userListView = (ListView)view.findViewById(R.id.fragment_rank_lv_user);
		rankAdapter = new RankAdapter(getActivity(), R.layout.fragment_rank_item, rankList);
		userListView.setAdapter(rankAdapter);
		IntentFilter intentFilter = new IntentFilter();  
		intentFilter.setPriority(100);
		intentFilter.addAction(Consts.INVITE_PK_FAILURE_RECEIVER);  
		onlineBroadcastReceiver = new OnlineBroadcastReceiver();
        getActivity().registerReceiver(onlineBroadcastReceiver, intentFilter); 
		return view;
	}
	
	@Override
	public void onClick(View v) {
		switch(v.getId()) {
		case R.id.fragment_rank_bn_search:
			if(STATE == SEARCH) {
				String search = searchEditText.getText().toString();
				if(TextUtils.isEmpty(search)) {
					ToastUtil.toast(Consts.RANK_FRAGMENT_EMPTY_SEARCH);
				} else {
					prepareSearch();
					getUserByName(search);
				}
			} else if(STATE == CANCEL){
				prepareCancel();
				searchEditText.setText("");
				getUserScoreRankByPage();
			}
		}
	}
	
	private void prepareSearch() {
		searchButton.setText("取消");
		STATE = CANCEL;
	}
	
	private void prepareCancel() {
		searchButton.setText("搜索");
		STATE = SEARCH;
	}
	
	private void getUserScoreRankByPage() {
		rankProgressDialog = ProgressDialog.show(getActivity(), null, Consts.RANK_FRAGMENT_PROMPT, true);
		HttpUtil.post(Consts.GET_USER_SCORE_RANK_BY_PAGE, new HttpCondition().getHttpCondition("first", FIRST, "last", LAST), new HttpCallbackListener() {
			
			@Override
			public void onSuccess(String response) {
				LogUtil.d(TAG, response);
				@SuppressWarnings("unchecked")
				final Map<Object, Object> responseMap = (Map<Object, Object>)JSON.parse(response);
				if(Consts.SUCCESS.equals(responseMap.get(Consts.RESULT))) {
					convertUserList(responseMap);
				} 
				rankProgressDialog.dismiss();
				getActivity().runOnUiThread(new Runnable() {
					@Override
					public void run() {
						refreshListView(responseMap.get(Consts.MESSAGE).toString());
					}
				});
			}
			
			@Override
			public void onFailure(String response) {
				LogUtil.e(TAG, response);
				rankProgressDialog.dismiss();
				getActivity().runOnUiThread(new Runnable() {               
	                public void run() {  
	                	ToastUtil.toast(Consts.SERVER);
	                }  
				});
			}
		});
	}

	private void getUserByName(String search) {
		rankProgressDialog = ProgressDialog.show(getActivity(), null, Consts.RANK_FRAGMENT_PROMPT, true);
		HttpUtil.post(Consts.GET_USER_BY_NAME, new HttpCondition().getHttpCondition("first", FIRST, "last", LAST, "name", search), new HttpCallbackListener() {
			
			@Override
			public void onSuccess(String response) {
				LogUtil.d(TAG, response);
				@SuppressWarnings("unchecked")
				final Map<Object, Object> responseMap = (Map<Object, Object>)JSON.parse(response);
				if(Consts.SUCCESS.equals(responseMap.get(Consts.RESULT))) {
					convertUserList(responseMap);
				} 
				rankProgressDialog.dismiss();
				getActivity().runOnUiThread(new Runnable() {
					@Override
					public void run() {
						refreshListView(responseMap.get(Consts.MESSAGE).toString());
					}
				});
			}
			
			@Override
			public void onFailure(String response) {
				LogUtil.e(TAG, response);
				rankProgressDialog.dismiss();
				getActivity().runOnUiThread(new Runnable() {               
	                public void run() {  
	                	ToastUtil.toast(Consts.SERVER);
	                }  
				});
			}
		});
	}
	
	//TODO:需要优化.
	private void convertUserList(Map<Object, Object> responseMap) {
		rankList.clear();
		@SuppressWarnings("unchecked")
		List<Map<Object, Object>> mapList = (List<Map<Object, Object>>)JSON.parse(responseMap.get(Consts.RANK_FRAGMENT_USER).toString());
		for(int i = 0; mapList != null && i < mapList.size(); i++) {
			Map<Object, Object> map = mapList.get(i);
			File photoDir = FileUtil.getOtherDir("photo");
			String photoName = map.get("id").toString() + Consts.PNG;
			File photoFile = new File(photoDir.getPath() + File.separator + photoName);
			String photoFileUrl = null;
			long length = Long.parseLong(map.get("length").toString());
		    if(!photoFile.exists()) {
		    	if(length > 0) {
		    		if(HttpUtil.download(Consts.WEB_ADDRESS + Consts.USER_PHOTO + File.separator + photoName, photoFile.getPath())) {
						photoFileUrl = photoFile.getPath();
					}
		    	}
		    } else {
		    	if(photoFile.length() != length) {
		    		if(HttpUtil.download(Consts.WEB_ADDRESS + Consts.USER_PHOTO + File.separator + photoName, photoFile.getPath())) {
						photoFileUrl = photoFile.getPath();
					} 
		    	} else {
		    		photoFileUrl = photoFile.getPath();
		    	}
		    }
			boolean sex = (Boolean)map.get("sex");
			Rank rank = new Rank(String.valueOf(i), map.get("id").toString(), photoFileUrl, map.get("name").toString(), map.get("totalScore").toString(), 
					sex == true ? sexs[0] : sexs[1] , String.valueOf(0), String.valueOf(0), String.valueOf(0), String.valueOf(length));	
			rankList.add(rank);
		}
	}
	
	private void refreshListView(String prompt) {
		rankAdapter.notifyDataSetChanged();
		userListView.setSelection(0);
		ToastUtil.toast(prompt);
	}
	
	class OnlineBroadcastReceiver extends BroadcastReceiver {
		
		@Override
		public void onReceive(Context context, final Intent intent) {
			getActivity().runOnUiThread(new Runnable() {               
                public void run() {  
                	String message = intent.getSerializableExtra(Consts.MESSAGE).toString();
                	Message map = (Message)JSON.parseObject(message.toString(), Message.class);
            	    int type = Integer.parseInt(map.getType());
                	switch (type) {
                	case Consts.MESSAGE_PK_INVITE_FAILURE:
                		ToastUtil.toast(map.getMessage());
                	default:
                		break;
                	}
                }  
			});
		}
	}
	
	@Override
	public void onDestroy() {
		getActivity().unregisterReceiver(onlineBroadcastReceiver);
		super.onDestroy();
	}
}
