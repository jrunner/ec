package com.ilucky.aplay.core.client;

import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoderAdapter;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;

import com.ilucky.aplay.util.android.LogUtil;

/**
 * @author IluckySi
 * @since 20150728
 */
public class JsonProtocolEncoder extends ProtocolEncoderAdapter {  
	  
	private static final String TAG = "JsonProtocolEncoder";
    private int socketPrefixLength;  
  
    public JsonProtocolEncoder(int socketPrefixLength) {  
        this.socketPrefixLength = socketPrefixLength;  
    }  
  
    public void encode(IoSession session, Object message, ProtocolEncoderOutput out) throws Exception { 
    	IoBuffer buffer = null;
    	try {
    		CharsetEncoder charsetEncoder = Charset.forName("UTF-8").newEncoder();  
	        buffer = IoBuffer.allocate(1024);  
	        buffer.setAutoExpand(true);  
	        buffer.putPrefixedString((String)message, this.socketPrefixLength, charsetEncoder);  
	        buffer.flip();  
	        out.write(buffer);
    	} catch (Exception e) {
    		LogUtil.e(TAG, e.toString()); 
    	}
    }  
}  
