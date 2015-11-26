package com.t2t.top.devops.dao;

import com.t2t.top.devops.model.po.PubHistory;
import com.t2t.top.devops.model.po.PubHistoryExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PubHistoryMapper {
    int countByExample(PubHistoryExample example);

    int deleteByExample(PubHistoryExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(PubHistory record);

    int insertSelective(PubHistory record);

    List<PubHistory> selectByExample(PubHistoryExample example);

    PubHistory selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") PubHistory record, @Param("example") PubHistoryExample example);

    int updateByExample(@Param("record") PubHistory record, @Param("example") PubHistoryExample example);

    int updateByPrimaryKeySelective(PubHistory record);

    int updateByPrimaryKey(PubHistory record);
}