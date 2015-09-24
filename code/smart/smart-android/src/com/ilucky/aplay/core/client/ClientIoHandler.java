package com.ilucky.aplay.core.client;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

import com.ilucky.aplay.core.broadcast.MessageBroadcast;
import com.ilucky.aplay.core.consts.Consts;
import com.ilucky.aplay.util.android.LogUtil;

/**
 * @author IluckySi
 * @since 20150728
 */
public class ClientIoHandler extends IoHandlerAdapter {  
	
	private static final String TAG = "ClientIoHandler";
	
	@Override
	public void sessionCreated(IoSession session) throws Exception {
		LogUtil.i(TAG, Consts.CONNECT_CREATE+session.hashCode());
		super.sessionCreated(session);
	}
	
	@Override
	public void sessionOpened(IoSession session) throws Exception {
		LogUtil.i(TAG, Consts.CONNECT_OPEN+session.hashCode());
		super.sessionOpened(session);
	}

    public void messageSent(IoSession session, Object message) {
    	LogUtil.i(TAG, Consts.MESSAGE_SENT+session.hashCode()+":"+message);
    }
    
	 public void messageReceived(IoSession session, Object message) throws Exception {  
		 LogUtil.i(TAG, Consts.MESSAGE_RECEIVED+session.hashCode()+":"+message); 
		 if(message == null) {
				return;
		}
		MessageBroadcast.send(message.toString());
    }  
    
	@Override
	public void sessionIdle(IoSession session, IdleStatus status) throws Exception {
		LogUtil.i(TAG, Consts.CONNECT_IDLE+session.hashCode()+":"+status);
		super.sessionIdle(session, status);
	}
	
	@Override
	public void sessionClosed(IoSession session) throws Exception {
		LogUtil.i(TAG, Consts.CONNECT_CLOSE+session.hashCode());
		super.sessionClosed(session);
	}
	
	@Override
	public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
		LogUtil.i(TAG, Consts.CONNECT_EXCEPTION+session.hashCode()+":"+cause);
		super.exceptionCaught(session, cause);
	}
}
