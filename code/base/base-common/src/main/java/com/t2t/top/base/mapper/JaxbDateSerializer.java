package com.t2t.top.base.mapper;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * @author chenxushao
 *  用在soap接口数字签名处，不要变更该处代码
 */
public class JaxbDateSerializer extends XmlAdapter<String, Date>{
    @Override
    public String marshal(Date date) throws Exception {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
    }
 
    @Override
    public Date unmarshal(String date) throws Exception {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(date);
    }
}