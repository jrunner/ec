package com.t2t.top.base.mapper;

import java.math.BigDecimal;
import java.text.DecimalFormat;

import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * @author chenxushao
 * BigDecimal定制序列化
 */
public class JaxbBigDecimalSerializer extends XmlAdapter<String,BigDecimal >{
    @Override
    public String marshal(BigDecimal bigDecimal) throws Exception {
        return  	new DecimalFormat("#.00").format(bigDecimal);
    }
 
    @Override
    public BigDecimal unmarshal(String value) throws Exception {
        return new BigDecimal(value);
    }
}