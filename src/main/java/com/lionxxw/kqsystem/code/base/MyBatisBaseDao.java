package com.lionxxw.kqsystem.code.base;



import com.lionxxw.kqsystem.code.utils.reflect.DynamicMethod;

import java.util.Date;

/**
 * <p>Description: mybatisDao 基类 </p>
 * 
 * @author wangxiang	
 * @date 16/5/6 上午8:59
 * @version 1.0
 */
public abstract class MyBatisBaseDao<T> {
	
	public int deleteByPrimaryKey(Integer id) throws Exception{
		return (Integer) DynamicMethod.invokeMethod(getMapper(), "deleteByPrimaryKey", new Object[]{id});
	}
	
	public T selectByPrimaryKey(Integer id) throws Exception{
		return (T) DynamicMethod.invokeMethod(getMapper(), "selectByPrimaryKey", new Object[]{id});
	}

	public int insert(T obj) throws Exception{
		return (Integer) DynamicMethod.invokeMethod(getMapper(), "insert", new Object[]{obj});
	}
	
	public int updateByPrimaryKey(T obj){
		return (Integer) DynamicMethod.invokeMethod(getMapper(), "updateByPrimaryKey", new Object[]{obj});
	}
	
	public int insertSelective(T obj){
		return (Integer) DynamicMethod.invokeMethod(getMapper(), "insertSelective", new Object[]{obj});
	}
	
	public int updateByPrimaryKeySelective(T obj){
		return (Integer) DynamicMethod.invokeMethod(getMapper(), "updateByPrimaryKeySelective", new Object[]{obj});
	}
	
	public abstract Object getMapper();
}
