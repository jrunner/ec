package com.ilucky.aplay.core.broadcast;

import com.ilucky.aplay.core.service.HeartBeatService;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * @author IluckySi
 * @since 20150907
 */
public class HeartBeatBroadcastReceiver extends BroadcastReceiver {
	
	@Override
	public void onReceive(Context context, Intent intent) {
		Intent i = new Intent(context, HeartBeatService.class);
		context.startService(i);
	}
}
