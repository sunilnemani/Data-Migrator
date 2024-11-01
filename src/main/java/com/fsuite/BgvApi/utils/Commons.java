/*
*
*N Sunil 
*
*/

package com.fsuite.BgvApi.utils;

import java.lang.reflect.Field;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Commons
{
	
	private static final Logger log = LoggerFactory.getLogger(Commons.class);
	
	public static <T> void readAndFillValues(T t,Map<String,String> propsMap)
	{
		try
		{
			Class<?> tClass = t.getClass();
			Field[] declaredFields = tClass.getDeclaredFields();
			if(declaredFields != null)
			{
				Value value = null;
				String propertyValue = null;
				String key = null;
				for(Field field : declaredFields)
				{
					if(field.isAnnotationPresent(Value.class))
					{
						value = field.getAnnotation(Value.class);
						key = removePlaceHolders(value.value());
						propertyValue = propsMap.get(key);
						if(isNullOrEmpty(propertyValue))
						{
							log.error("No value found for property "+key);
							throw new RuntimeException("No value found for property "+key);
						}
						propertyValue.trim();
						try
						{
							BeanUtils.copyProperty(t, field.getName(), convert(propertyValue, field.getType()));
						}
						catch(Exception ex)
						{
							log.error("Error Unable to parse value for "+key,ex);
							throw new RuntimeException("Error Unable to parse value for "+key);
						}
					}
				}
			}
			log.info(t.toString());
		}
		catch (Exception ex)
		{
			log.error("Error ",ex);
			throw new RuntimeException("Error initialising beans",ex);
		}
	}
	
	public static String removePlaceHolders(String data)
	{
		String returnVal = data;
		if(data != null)
		{
			if(data.startsWith("${") && data.endsWith("}"))
			{
				returnVal = data.substring(2, data.length()-1);
			}
		}
		return returnVal;
	}
	
	public static boolean isNullOrEmpty(String val)
	{
		return val == null ? true : val.trim().isEmpty();
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T convert(String data,Class<T> type)
	{
		T t = null;
		if(type.equals(Integer.class))
		{
			t = (T) toInteger(data);
		}
		else if(type.equals(String.class))
		{
			t = (T) data;
		}
		else if(type.equals(Boolean.class))
		{
			t = (T)toBoolean(data);
		}
		else
		{
			log.error("Error. Support for type "+type+" is not implemented");
			throw new RuntimeException("Error. Support for type "+type+" is not implemented");
		}
		return t;
	}
	
	private static Integer toInteger(String value)
	{
		Integer intVal = null;
		try
		{
			intVal = Integer.parseInt(value);
		}
		catch (Exception ex)
		{
			log.error("Error ",ex);
			throw new RuntimeException("Invalid value for Integer "+value);
		}
		return intVal;
	}
	
	private static Boolean toBoolean(String value)
	{
		return value == null ? false : value.equalsIgnoreCase("Y") || value.equalsIgnoreCase("TRUE");
	}

}
