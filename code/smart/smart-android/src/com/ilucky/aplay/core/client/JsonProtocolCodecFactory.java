package com.ilucky.aplay.core.client;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFactory;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolEncoder;

/**
 * @author IluckySi
 * @since 20150728
 */
public class JsonProtocolCodecFactory implements ProtocolCodecFactory {
	
	private final JsonProtocolEncoder encoder;  
    private final JsonProtocolDecoder decoder;  
  
    public JsonProtocolCodecFactory(int socketPrefixLength) {  
        encoder = new JsonProtocolEncoder(socketPrefixLength);  
        decoder = new JsonProtocolDecoder(socketPrefixLength);  
    }  
  
    public ProtocolEncoder getEncoder(IoSession session) throws Exception {  
        return encoder;  
    }  
  
    public ProtocolDecoder getDecoder(IoSession session) throws Exception {  
        return decoder;  
    }  
}
