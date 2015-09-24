package com.ilucky.aplay.core.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ilucky.aplay.R;
import com.ilucky.aplay.core.activity.home.HomeFragment;
import com.ilucky.aplay.core.activity.rank.RankFragment;
import com.ilucky.aplay.core.activity.setting.SettingFragment;
import com.ilucky.aplay.core.consts.Consts;
import com.ilucky.aplay.core.service.HeartBeatService;

/**
 * @author IluckySi
 * @since 20150724
 */
public class MainActivity extends FragmentActivity implements OnClickListener{

	private TextView titleTextView;
	private RelativeLayout homeRelativeLayout;
	private RelativeLayout rankRelativeLayout;
	private RelativeLayout settingRelativeLayout;
	private ImageView homeImageView;
	private ImageView rankImageView;
	private ImageView settingImageView;
	private TextView homeTextView;
	private TextView rankTextView;
	private TextView settingTextView;
	private FragmentManager fragmentManager;
	private HomeFragment homeFragment;
	private RankFragment rankFragment;
	private SettingFragment settingFragment;
	private static int TAB = 1;
	private static final int HOME = 1;
	private static final int RANK = 2;
	private static final int SETTING = 3;
    private long exitTime = 0;
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		fragmentManager = getSupportFragmentManager();
		initViews();
		if (savedInstanceState != null) {
			TAB = Integer.parseInt(savedInstanceState.getString("tab"));
		}
		setTab(TAB);
		sendHeartBeat();
	}
	
	private void initViews() {
		titleTextView = (TextView)findViewById(R.id.activity_main_tv_title);
		homeRelativeLayout = (RelativeLayout) findViewById(R.id.activity_main_rl_home);
		rankRelativeLayout = (RelativeLayout) findViewById(R.id.activity_main_rl_rank);
		settingRelativeLayout = (RelativeLayout) findViewById(R.id.activity_main_rl_setting);
		homeImageView = (ImageView) findViewById(R.id.activity_main_iv_home);
		rankImageView = (ImageView) findViewById(R.id.activity_main_iv_rank);
		settingImageView = (ImageView) findViewById(R.id.activity_main_iv_setting);
		homeTextView = (TextView) findViewById(R.id.activity_main_tv_home);
		rankTextView = (TextView) findViewById(R.id.activity_main_tv_rank);
		settingTextView = (TextView) findViewById(R.id.activity_main_tv_setting);
		homeRelativeLayout.setOnClickListener(this);
		rankRelativeLayout.setOnClickListener(this); 
		settingRelativeLayout.setOnClickListener(this);
	}
	
	@Override
	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.activity_main_rl_home:
			TAB = HOME;
			setTab(HOME);
			break;
	    case R.id.activity_main_rl_rank:
	    	TAB = RANK;
	    	setTab(RANK);
	    	break;
	    case R.id.activity_main_rl_setting:
	    	TAB = SETTING;
	    	setTab(SETTING);
	    	break;
	    default:
			break;
		}
	}
	
	private void setTab(int index){
		resetTab();
		FragmentTransaction transaction = fragmentManager.beginTransaction();  
		hideFragments(transaction);
		switch (index) {
		case HOME:
			titleTextView.setText(R.string.title_home);
			homeImageView.setImageResource(R.drawable.activity_main_iv_home2);  
			homeTextView.setTextColor(Consts.blue);
            if (homeFragment == null) {  
            	homeFragment = new HomeFragment();  
                transaction.add(R.id.activity_main_fl_content, homeFragment);  
            } else {  
                transaction.show(homeFragment);  
            }  
            break;  
		case RANK:
			titleTextView.setText(R.string.title_rank);
			rankImageView.setImageResource(R.drawable.activity_main_iv_rank2);  
			rankTextView.setTextColor(Consts.blue);
            if (rankFragment == null) {  
            	rankFragment = new RankFragment();  
                transaction.add(R.id.activity_main_fl_content, rankFragment);  
            } else {  
                transaction.show(rankFragment);  
            }  
            break;      
		 case SETTING:
			titleTextView.setText(R.string.title_setting);
			settingImageView.setImageResource(R.drawable.activity_main_iv_setting2);  
			settingTextView.setTextColor(Consts.blue);
            if (settingFragment == null) {  
            	settingFragment = new SettingFragment();  
                transaction.add(R.id.activity_main_fl_content, settingFragment);  
            } else {  
                transaction.show(settingFragment);  
            }  
            break;                 
		}
		transaction.commit();
	}
	
	private void resetTab() {
		homeImageView.setImageResource(R.drawable.activity_main_iv_home);
		homeTextView.setTextColor(Consts.gray);
		rankImageView.setImageResource(R.drawable.activity_main_iv_rank);
		rankTextView.setTextColor(Consts.gray);
		settingImageView.setImageResource(R.drawable.activity_main_iv_setting);
		settingTextView.setTextColor(Consts.gray);
	}
	
	private void hideFragments(FragmentTransaction transaction) {  
        if (homeFragment != null) {  
            transaction.hide(homeFragment);  
        }  
        if (rankFragment != null) {  
            transaction.hide(rankFragment);  
        }  
        if (settingFragment != null) {  
            transaction.hide(settingFragment);  
        }  
    }  
	
	private void sendHeartBeat() {
		Intent intent = new Intent(this, HeartBeatService.class);
		startService(intent);
	}
	
	@Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                finish();
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
	
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putString("tab", String.valueOf(TAB));
	}
}
