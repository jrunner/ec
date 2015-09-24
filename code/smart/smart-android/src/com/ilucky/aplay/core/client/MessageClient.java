package com.ilucky.aplay.core.client;

import java.net.InetSocketAddress;

import org.apache.http.util.TextUtils;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.service.IoConnector;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

import com.alibaba.fastjson.JSON;
import com.ilucky.aplay.core.consts.Consts;
import com.ilucky.aplay.util.android.LogUtil;
import com.ilucky.aplay.util.android.SharedPreferenceUtil;

/**
 * @author IluckySi
 * @since 20150728
 */
public class MessageClient {
		
	private static final String TAG ="MessageClient";
    private static MessageClient instance;
    private static IoSession session = null;
    
    private MessageClient() {
    }
    
    public static MessageClient getInstance() {
    	if(instance == null) {
    		instance = new MessageClient();
    	}
    	return instance;
    }
    
    public void send() { 
    	 String id = SharedPreferenceUtil.read("user", "id");
    	 if(!TextUtils.isEmpty(id)) {
    		 long time = System.currentTimeMillis();
    		String message = JSON.toJSONString(new Message().setFrom(id).setType(String.valueOf(Consts.MESSAGE_HEART_BEAT)).setTime(time));
    	    this.send(message);
    	 }
    }
    
    public void send(final String message) { 
    	new Thread(new Runnable() {
			@Override
			public void run() {
		        try {  
		        	if(session == null) {
	        			IoConnector connector = new NioSocketConnector();    
			            connector.setConnectTimeoutMillis(30000);  
			            connector.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE, 60);  
			            connector.getFilterChain().addLast("codec", new ProtocolCodecFilter(new JsonProtocolCodecFactory(4)));  
			            connector.setHandler(new ClientIoHandler()); 
			            ConnectFuture future = connector.connect(new InetSocketAddress(Consts.SERVER_ADDRESS, Consts.SERVER_PORT));  
			            future.awaitUninterruptibly();  
			            session = future.getSession();
		        	}
		        	session.write(message);
		        } catch (Exception e) {  
		        	LogUtil.e(TAG, Consts.CONNECT_FAILURE+e);
			    } 
			}
    	}).start();
    }
}
