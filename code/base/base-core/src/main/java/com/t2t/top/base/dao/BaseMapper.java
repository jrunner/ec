package com.t2t.top.base.dao;


import java.util.List;
import java.util.Map;

/**
 * Created by yangpengfei on 2015/11/3.
 */
public interface BaseMapper {

    /**
     * 根据主键ID删除记录.
     *
     * @param id
     * @return int
     */
    public int deleteByPrimaryKey(Integer id);

    /**
     * 插入一条记录，可以有空值.
     *
     * @param record
     * @return int
     */
    public int insert(Object record);

    /**
     * 插入一条记录，自动过滤空值.
     *
     * @param record
     * @return int
     */
    public int insertSelective(Object record);

    /**
     * 按主键ID查询一条记录.
     *
     * @param id
     * @return Object
     */
    public Object selectByPrimaryKey(Integer id);

    /**
     * 更新一条记录，自动过滤空值.
     *
     * @param record
     * @return int
     */
    public int updateByPrimaryKeySelective(Object record);

    /**
     * 取得资源中大字段属性.
     *
     * @param record
     * @return int
     */
    public int updateByPrimaryKeyWithBLOBs(Object record);

    /**
     * 更新一条记录，可以有空值.
     *
     * @param record
     * @return int
     */
    public int updateByPrimaryKey(Object record);

    /**
     * 按条件MAP进行查询.
     *
     * @param conditionMap
     * @return List
     */
    public List queryByCondition(Map<String, Object> conditionMap);

    /**
     * 按条件进行查询统计.
     *
     * @param conditionMap
     * @return int
     */
    public int queryCountByCondition(Map<String, Object> conditionMap);

    /**
     * 根据主键批量删除.
     *
     * @param primaryKeys
     */
    public int batchDeleteByPrimaryKeys(String[] primaryKeys);

    /**
     * 按给定的一组ID查询关系对象.
     *
     * @param selectMap
     * @return List
     */
    public List queryRelationByOneKeyId(Map<String, Object> selectMap);

    /**
     * 按资源ID查询对应的资源对象.
     *
     * @param ids
     * @return List
     */
    public List queryResourcesByPrimaryId(String[] ids);

    /**
     * 根据资源Ids批量更新map里面所包含的字段.
     *
     * @param coditonMap
     * @return int
     */
    public int batchUpdateByPrimaryKeysSelective(Map<String, Object> coditonMap);

    /**
     * 查找要导入的资源是否存在.
     */
    public int checkImportResource(Map<String, Object> coditonMap);

    /**
     * 删除关系资源通过关系字段.
     *
     * @param map
     * @return
     */
    public int deleteRelationByOneKeyId(Map<String, Object> map);

}


