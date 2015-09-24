package com.ilucky.aplay.core.activity.setting;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.ilucky.aplay.R;

/**
 * @author IluckySi
 * @since 20150814
 */
public class HelpActivity extends Activity implements OnClickListener {

	private Button backButton;
	private TextView helpTextView;
	private static final String help = 
			"简介\r\n" +
            "乐猜音乐是一个让你不断发现好音乐的音乐社区。\r\n" +
            "在这里你除了可以不断发现好音乐,还可以结交很多志趣相投的乐友。\r\n" +
            "\r\n\r\n" +
            "内容\r\n" +
            "1.修炼升级\r\n" + 
             "用户在主页选取歌曲纬度,按下play按钮获取音乐片段,倾听音乐,试猜歌名,每猜对一个相应获得一个积分。\r\n" +
             "通过修炼升级获取的最高积分为100积分。\r\n" +
             "\r\n" +
			"2.擂台挑战\r\n" +
             "用户在排行可以邀请挑战其他用户,被邀请用户同意应战后,两方获取相同的音乐片段,倾听音乐,试猜歌名,\r\n" +
             "有一方结束,视为挑战结束,猜对多的一方视为胜利,获取10个积分,输的一方减去10个积分,通过擂台挑战获取的最高积分不受限制。\r\n"+
             "\r\n\r\n" +
             "其他\r\n" +
             "1.注册便可获得100积分。\r\n"+
             "2.您可以为我们提供您宝贵的意见,我们会认真接受。\r\n";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_help);
		initViews();
	}
	
	private void initViews() {
		backButton = (Button)findViewById(R.id.activity_help_bn_back);
		backButton.setOnClickListener(this);
		helpTextView = (TextView)findViewById(R.id.activity_help_tv_help);
		helpTextView.setText(help);
	}

	@Override
	public void onClick(View v) {
		switch(v.getId()) {
		case R.id.activity_help_bn_back:
			finish();
			break;
		default:
			break;
		}		
	}
}
