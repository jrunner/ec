package com.ilucky.aplay.core.activity.rank;

import java.io.File;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.ilucky.aplay.R;
import com.ilucky.aplay.core.activity.common.Common;
import com.ilucky.aplay.core.application.AplayApplication;
import com.ilucky.aplay.core.client.Message;
import com.ilucky.aplay.core.client.MessageClient;
import com.ilucky.aplay.core.consts.Consts;
import com.ilucky.aplay.util.android.FileUtil;
import com.ilucky.aplay.util.android.LogUtil;
import com.ilucky.aplay.util.file.FileDeleteUtil;
import com.ilucky.aplay.util.file.FileReadUtil;
import com.ilucky.aplay.util.http.HttpUtil;
import com.ilucky.aplay.util.zip.UnZipUtil;

/**
 * @author IluckySi
 * @since 20150906
 */
public class InvitedPkActivity extends Activity implements OnClickListener {

	private static final String TAG = "InvitedPkActivity";
	private ImageView photoImageView;
	private TextView promptTextView;
	private Button agreeButton;
	private Button refuseButton;
	private Message map;
	private ProgressDialog invitedProgressDialog;
	private InvitedPkResultBroadcastReceiver invitedPkResultBroadcastReceiver;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_invited_pk);
		initViews();
	}
	
	private void initViews() {
		String message = getIntent().getStringExtra(Consts.MESSAGE);
		map = (Message)JSON.parseObject(message.toString(), Message.class);
		@SuppressWarnings("unchecked")
		final Map<Object, Object> user = (Map<Object, Object>)JSON.parse(map.getMessage());
		String name = user.get("name").toString();
		new Thread(new Runnable() {
			@Override
			public void run() {
				File photoDir = FileUtil.getOtherDir("photo");
				String photoName = map.getFrom() + Consts.PNG;
				File photoFile = new File(photoDir.getPath() + File.separator + photoName);
				String photoFileUrl = null;
				long length = Long.parseLong(user.get("length").toString());
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
				Bitmap bitmap = null;
				try {
					if(photoFileUrl == null) {
						bitmap = BitmapFactory.decodeStream(AplayApplication.getContext().getAssets().open("photo" + File.separator + Consts.DEFAULT_PHOTO));
					}  else {
						bitmap = BitmapFactory.decodeFile(photoFileUrl);
					}
				} catch (Exception e) {
					LogUtil.e(TAG, e.toString());
				}
				photoImageView =  (ImageView)findViewById(R.id.activity_invited_pk_iv_photo);
				photoImageView.setImageBitmap(bitmap);
			}
		}).start();
		promptTextView = (TextView)findViewById(R.id.activity_invited_pk_tv_prompt);
		promptTextView.setText("用户["+name+"]向你发起挑战");
		agreeButton = (Button)findViewById(R.id.activity_invited_pk_bn_agree);
		agreeButton.setOnClickListener(this);
		refuseButton = (Button)findViewById(R.id.activity_invited_pk_bn_refuse);
		refuseButton.setOnClickListener(this);
		IntentFilter intentFilter = new IntentFilter();  
		intentFilter.setPriority(100);
		intentFilter.addAction(Consts.INVITE_PK_RESULT_RECEIVER);  
		invitedPkResultBroadcastReceiver = new InvitedPkResultBroadcastReceiver();
        registerReceiver(invitedPkResultBroadcastReceiver, intentFilter); 
	}

	@Override
	public void onClick(View v) {
		switch(v.getId()) {
		case R.id.activity_invited_pk_bn_agree:
			agree();
			break;
		case R.id.activity_invited_pk_bn_refuse:
			refuse();
			break;
		default:
			break;
		}
	}
	
	private void agree() {
		  MessageClient.getInstance().send(JSON.toJSONString(new Message().setFrom(map.getTo()).setTo(map.getFrom()).
	        		setType(String.valueOf(Consts.MESSAGE_PK_AGREE)).setMessage(JSON.toJSONString(Common.getUserData()))));
		  invitedProgressDialog = ProgressDialog.show(this, null, Consts.INVITED_PK_ACTIVITY_PROMPT, true);
	}
	
	private void refuse() {
		  MessageClient.getInstance().send(JSON.toJSONString(new Message().setFrom(map.getTo()).setTo(map.getFrom()).
	        		setType(String.valueOf(Consts.MESSAGE_PK_REFUSE)).setMessage(JSON.toJSONString(Common.getUserData()))));
		  finish();
	}
	
	class InvitedPkResultBroadcastReceiver extends BroadcastReceiver {
		
		@Override
		public void onReceive(Context context, final Intent intent) {
			final Message message = (Message)JSON.parseObject( intent.getStringExtra(Consts.MESSAGE).toString(), Message.class);
			@SuppressWarnings("unchecked")
			final Map<Object, Object> resource = (Map<Object, Object>)JSON.parse(message.getMessage());
			final String url = resource.get("url").toString();
        	new Thread(new Runnable() {
    			@Override
    			public void run() {
                	final File resourceDir = FileUtil.getOtherDir("resource");
            		FileDeleteUtil.delete(resourceDir.getPath(), false);
					final String resourceName = url.substring(url.lastIndexOf("/") + 1).split("\\.")[0];
					String resourceZipFile = resourceDir.getPath() + File.separator + resourceName +  Consts.ZIP;
					if(HttpUtil.download(url, resourceZipFile)) {
						UnZipUtil unZipUtil = new UnZipUtil();
						unZipUtil.setSrcPath(resourceDir.getPath() + File.separator + resourceName + Consts.ZIP);
						unZipUtil.setDstPath(resourceDir.getPath());
						unZipUtil.startUnZip();
						File resourceTxtFile = new File(resourceDir.getPath() + File.separator + resourceName + Consts.TXT);
						String result = FileReadUtil.readFile(resourceTxtFile);
						@SuppressWarnings("unchecked")
						final Map<String, List<String>> resultMap = (Map<String, List<String>>)JSON.parse(result);
						Intent startPkIntent = new Intent(AplayApplication.getContext(), StartPkActivity.class);
						startPkIntent.putExtra("playMap", JSON.toJSONString(resultMap));
						startPkIntent.putExtra("from",message.getTo());
					    startPkIntent.putExtra("to", message.getFrom());
				        startActivity(startPkIntent);
					}
					runOnUiThread(new Runnable() {               
		                public void run() {  
		                	invitedProgressDialog.dismiss();
		                	finish();
		                }
					});
    			}
			}).start();
		}
	}
	
	@Override
	protected void onDestroy() {
		unregisterReceiver(invitedPkResultBroadcastReceiver);
		super.onDestroy();
	}
}
