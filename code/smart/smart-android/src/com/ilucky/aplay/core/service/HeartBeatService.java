package com.ilucky.aplay.core.service;

import java.util.Date;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.SystemClock;

import com.ilucky.aplay.core.broadcast.HeartBeatBroadcastReceiver;
import com.ilucky.aplay.core.client.MessageClient;
import com.ilucky.aplay.util.android.LogUtil;

/**
 * @author IluckySi
 * @since 20150906
 */
public class HeartBeatService extends Service {

	private static final String TAG = "HeartBeatService";
	
	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				LogUtil.d(TAG, new Date().toString());
			}
		}).start();
		MessageClient.getInstance().send();
		AlarmManager manager = (AlarmManager) getSystemService(ALARM_SERVICE);
		int cycle = 60 * 1000; 
		long triggerAtTime = SystemClock.elapsedRealtime() + cycle;
		Intent i = new Intent(this, HeartBeatBroadcastReceiver.class);
		PendingIntent pi = PendingIntent.getBroadcast(this, 0, i, 0);
		manager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, triggerAtTime, pi);
		return super.onStartCommand(intent, flags, startId);
	}
}
