package com.ilucky.aplay.core.broadcast;

import android.content.Context;
import android.content.Intent;

import com.alibaba.fastjson.JSON;
import com.ilucky.aplay.core.activity.rank.InvitedPkActivity;
import com.ilucky.aplay.core.application.AplayApplication;
import com.ilucky.aplay.core.client.Message;
import com.ilucky.aplay.core.consts.Consts;

/**
 * @author IluckySi
 * @since 20150906
 */
public class MessageBroadcast {

	public static void send(String message) {
		Message map = (Message)JSON.parseObject(message.toString(), Message.class);
	    int type = Integer.parseInt(map.getType());
		Context context = AplayApplication.getContext();
	    switch (type) {
	    case Consts.MESSAGE_HEART_BEAT:
	    	break;
	    case Consts.MESSAGE_PK_INVITE:
	    	Intent invitedPkIntent = new Intent(context, InvitedPkActivity.class);
	    	invitedPkIntent.putExtra(Consts.MESSAGE, message);
	    	invitedPkIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			context.startActivity(invitedPkIntent);
	    	break;
	    case Consts.MESSAGE_PK_INVITE_FAILURE:
	    	Intent pkInviteFailureIntent = new Intent(Consts.INVITE_PK_FAILURE_RECEIVER);
	    	pkInviteFailureIntent.putExtra(Consts.MESSAGE, message);
	    	context.sendOrderedBroadcast(pkInviteFailureIntent, null);
	    	break;
	    case Consts.MESSAGE_PK_AGREE: 
	    case Consts.MESSAGE_PK_REFUSE:
	    	Intent pkInviteResultIntent = new Intent(Consts.INVITE_PK_RESULT_RECEIVER);
	    	pkInviteResultIntent.putExtra(Consts.MESSAGE, message);
	    	context.sendOrderedBroadcast(pkInviteResultIntent, null);
	    	break;
	    case Consts.MESSAGE_PK_RESULT:
	    	Intent pkResultIntent = new Intent(Consts.PK_RESULT_RECEIVER);
	    	pkResultIntent.putExtra(Consts.MESSAGE, message);
	    	context.sendOrderedBroadcast(pkResultIntent, null);
	    	break;
	    default:
	    	break;
	   }
	}
}
