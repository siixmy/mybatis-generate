package com.springboot.test.dao;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
* 标记接口。
* 使用说明：所有DAO 定义成接口并继承IMyBatisRepository，由Spring Context扫描，动态生成代理的实现类
*
* @author sijie
* @date 2023/09/02 13:21
* @version 0.0.1
*/
public interface IMybatisBaseDao<T> {
    /**
    *  将一个对象存入到db中
    * @param t
    * @return
    */
    int insert(T t);

    /**
    * 更新一个对象的信息到db中
    * @param t 被更新的对像，必须传入主键值
    * @return
    */
    int updateByPk(T t);// 根据主键来更新

    /**
    * 根据主键值删除db中的一条记录
    * @param t 被删除的对象，必须提供主键值
    * @return
    */
    int deleteByPrimaryKey(Long key);

    /**
    * 根据主键值获取一个对象
    *
    * @param key
    * @return
    */
    T getOneByPrimaryKey(Long key);


    /**
    * 返回满足条件的所有结果集合
    *
    * @return
    */
    List<T> getAllItemsByQueryObject(@Param("qo") final T queryObject);
        
    /**
    * 返回满足条件的所有结果集合(用于分页)
    *
    * @return
    */
    List<T> getListByCriteria(@Param("qo") final T queryObject,
                              @Param("qp") final Map<String, Object> otherParameters,
                              @Param("pageSize")Integer pageSize, @Param("offSet") Integer offSet);

    /**
    * 统计满足条件的数量
    *
    * @return
    */
    int countByCriteria(@Param("qo") final T queryObject, @Param("qp") final Map<String, Object> otherParameters);

    /**
    * 根据主键ID集合去查询
    * @param keys
    * @return
    */
    List<T> queryListByKeys(@Param("keys")Collection<Long> keys);

     /**
     * 根据主键ID集合去删除
     * @param keys
     * @return
     */
     void deleteByKeys(@Param("keys")Collection<Long>keys);

     /**
     * 批量插入
     * @param list
     * @return
     */
     int insertBatch(@Param("list")Collection<T> list);
   }
