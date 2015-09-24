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
import android.view.KeyEvent;
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
import com.ilucky.aplay.util.android.ToastUtil;
import com.ilucky.aplay.util.file.FileDeleteUtil;
import com.ilucky.aplay.util.file.FileReadUtil;
import com.ilucky.aplay.util.http.HttpUtil;
import com.ilucky.aplay.util.zip.UnZipUtil;

/**
 * @author IluckySi
 * @since 20150727
 */
public class InvitePkActivity extends Activity implements OnClickListener {

	private static final String TAG = "InvitePkActivity";
	private ImageView photoImageView;
	private TextView promptTextView;
	private Button operateButton;
	private ProgressDialog invitePkProgressDialog;
	private InvitePkResultBroadcastReceiver invitePkResultBroadcastReceiver;
	private String from;
	private String to;
	private String toName;
	private String toPhoto;
	private String url;
	private long length;
	private long time;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_invite_pk);
		initViews();
		sendInvitePkMessage();
	}
	
	private void initViews() {
		from = getIntent().getStringExtra("from");
		to = getIntent().getStringExtra("to");
		toName = getIntent().getStringExtra("toName");
		toPhoto = getIntent().getStringExtra("toPhoto");
		photoImageView = (ImageView)findViewById(R.id.activity_invite_pk_iv_photo);
		Bitmap bitmap = null;
		try {
			if(toPhoto == null) {
				bitmap = BitmapFactory.decodeStream(FileUtil.getAssestInputStream("photo" + File.separator + Consts.DEFAULT_PHOTO));
			}  else {
				bitmap = BitmapFactory.decodeFile(toPhoto);
			}
		} catch (Exception e) {
			LogUtil.e(TAG, e.toString());
		}
		photoImageView.setImageBitmap(bitmap);
		promptTextView = (TextView)findViewById(R.id.activity_invite_pk_tv_prompt);
		promptTextView.setText("正在邀请用户["+toName+"]挑战......");
		operateButton = (Button)findViewById(R.id.activity_invite_pk_bn_operate);
		operateButton.setOnClickListener(this);
		IntentFilter intentFilter = new IntentFilter();  
		intentFilter.setPriority(100);
		intentFilter.addAction(Consts.INVITE_PK_RESULT_RECEIVER);  
		invitePkResultBroadcastReceiver = new InvitePkResultBroadcastReceiver();
        registerReceiver(invitePkResultBroadcastReceiver, intentFilter); 
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.activity_invite_pk_bn_operate:
			cancel();
			break;
		default:
			break;
		}
	}
	
	private void sendInvitePkMessage() {
		time = System.currentTimeMillis();
        MessageClient.getInstance().send(JSON.toJSONString(new Message().setFrom(from).setTo(to).
        		setType(String.valueOf(Consts.MESSAGE_PK_INVITE)).setMessage(JSON.toJSONString(Common.getUserData())).setTime(time)));
	}
	
	private void startPk() {
		invitePkProgressDialog = ProgressDialog.show(this, null, Consts.INVITE_PK_ACTIVITY_PROMPT, true);
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
			        startPkIntent.putExtra("from", from);
			        startPkIntent.putExtra("to", to);
			        startActivity(startPkIntent);
			        invitePkProgressDialog.dismiss();
			        finish();
				}
				runOnUiThread(new Runnable() {               
	                public void run() {  
	                	invitePkProgressDialog.dismiss();
	                }
				});
			}
		}).start();
	}
	
	class InvitePkResultBroadcastReceiver extends BroadcastReceiver {
		
		@Override
		public void onReceive(Context context, final Intent intent) {
			runOnUiThread(new Runnable() {               
                public void run() {  
                	String message = intent.getSerializableExtra(Consts.MESSAGE).toString();
                	Message map = (Message)JSON.parseObject(message.toString(), Message.class);
            	    int type = Integer.parseInt(map.getType());
                	switch (type) {
                	case Consts.MESSAGE_PK_AGREE:
                		@SuppressWarnings("unchecked")
						Map<Object, Object> result = (Map<Object, Object>)JSON.parse(map.getMessage());
    					url = result.get("url").toString();
    					length = Long.parseLong(result.get("length").toString());
    					if(length > 0) {
    						ToastUtil.toast("用户["+toName+"]同意了您的邀请");
                    		operateButton.setVisibility(View.INVISIBLE);
                    		startPk();
    					}
                    	break;
                	case Consts.MESSAGE_PK_REFUSE:
                		ToastUtil.toast("用户["+toName+"]拒绝了您的邀请");
                		finish();
                		break;
                	default:
                		break;
                	}
                }  
			});
		}
	}

	private void cancel() {
		 MessageClient.getInstance().send(JSON.toJSONString(new Message().setFrom(from).setTo(to).
	        		setType(String.valueOf(Consts.MESSAGE_PK_INVITE_CANCEL)).setTime(time)));
		finish();
	}
	
	@Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

	@Override
	protected void onDestroy() {
		unregisterReceiver(invitePkResultBroadcastReceiver);
		super.onDestroy();
	}
}
