package com.ilucky.aplay.core.client;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.CumulativeProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;

import com.ilucky.aplay.util.android.LogUtil;

/**
 * @author IluckySi
 * @since 20150728
 */ 
public class JsonProtocolDecoder extends CumulativeProtocolDecoder   {  
  
	private static final String TAG = "JsonProtocolDecoder";
    private int socketPrefixLength;  
  
    public JsonProtocolDecoder(int socketPrefixLength) {  
        this.socketPrefixLength = socketPrefixLength;  
    }  
 
	@Override
	 protected boolean doDecode(IoSession session, IoBuffer in, ProtocolDecoderOutput out) throws Exception {
		InputStream is = null;
		InputStreamReader isr = null;
		BufferedReader br = null; 
		try {  
	        if(!in.prefixedDataAvailable(this.socketPrefixLength, Integer.MAX_VALUE)) {  
	            return false;  
	        }  
	        if(in == null || in.limit() == in.position() || !in.hasRemaining()) {  
	            return false;  
	        }  
	        is = in.asInputStream();  
	        is.read(new byte[this.socketPrefixLength]);  
	        isr = new InputStreamReader(is);
	        br = new BufferedReader(isr);  
		    StringBuffer sb = new StringBuffer();  
		    String line;  
		    while (null != (line = br.readLine())) {  
		    	sb.append(line).append("\n");  
		    }  
		    out.write(sb.toString());
		    return true;
	    } catch (Exception e) {  
	    	LogUtil.e(TAG, e.toString()); 
	        return false;
	    } finally {
	    	try {
	    		if(br != null) {
					br.close();
					br = null;
				}
				if(isr != null) {
					isr.close();
					isr = null;
				}
				if(is != null) {
					is.close();
					is = null;
				}
	    	} catch (Exception e) {
	    		LogUtil.e(TAG, e.toString()); 
	    	}
	    }
	}  
}  
