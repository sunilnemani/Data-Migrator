package com.fsuite.BgvApi.starter;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.core.util.CronExpression;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fsuite.BgvApi.db.Database;
import com.fsuite.BgvApi.inter.dto.SettingsDTO;
import com.fsuite.BgvApi.service.MigrateService;
import com.fsuite.BgvApi.serviceImpl.MigrateServiceImpl;
import com.fsuite.BgvApi.utils.Commons;
import com.fsuite.BgvApi.utils.DataMigrator;

public class DataMigratorApplication 
{
	
	private static Map<Class<?>, Object> beanMap = new HashMap<Class<?>,Object>();
	
	private static final Logger log = LoggerFactory.getLogger(DataMigratorApplication.class);
	
	private static final String PROPERTIES_FILE = "properties.properties";
	
	private static final String INTERMEDIATE_DB_PROPERTIES="intermediate_db.properties";
	private static final String ACT_DB_PROPERTIES="act_db.properties";

	public static void main(String[] args) 
	{
		DataMigratorApplication data = new DataMigratorApplication();
		data.startApplication();
	}
	
	private void startApplication()
	{
		InputStream in = null;
		Properties props=null;
		Map<String, String> propsMap=null;
		try
		{
			in = new FileInputStream(new File(PROPERTIES_FILE));
			props = new Properties();
			props.load(in);
		}
		catch(Exception e)
		{
			log.error("Unable to load properties.properties",e);
			throw new RuntimeException("Unable to load properties.properties");
		}
		finally
		{
			if (in != null)
			{
				try
				{
					in.close();
				}
				catch (Exception exception)
				{
				}
			}
		}
		
		try
	    {
	    	SettingsDTO settingsDTO=new SettingsDTO();
	    	String value = null;
	    	propsMap=new HashMap<String, String>();
	    	for(String key : props.stringPropertyNames())
			{
	    		value = props.getProperty(key);
	    		propsMap.put(key, value);
			}
	    	Commons.readAndFillValues(settingsDTO,propsMap);
	    	try
	    	{
		    	CronExpression expression = new CronExpression(settingsDTO.getCronExpressionFormat());
		    	settingsDTO.setCronExpression(expression);
		    	settingsDTO.setNextRunTime(expression.getNextValidTimeAfter(new Date()));
	    	}
	    	catch(ParseException e)
	    	{
	    		log.error("Invalid Cron Expression",e);
				throw new RuntimeException("Invalid Cron Expression");
	    	}
	    	
	    	//Load db
	    	Database intermediateDb = new Database(INTERMEDIATE_DB_PROPERTIES);
	    	Database actDb = new Database(ACT_DB_PROPERTIES);
	    	
	    	MigrateService migrateService = new MigrateServiceImpl(intermediateDb,actDb);
	    	settingsDTO.setMigrateService(migrateService);
	    	
	    	beanMap.put(SettingsDTO.class, settingsDTO);
	    	log.info(" beanMap : "+beanMap);
	    	
	    	ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
	    	service.scheduleWithFixedDelay(new DataMigrator(), settingsDTO.getInitialDelay(), settingsDTO.getDelay(), TimeUnit.SECONDS);
	    }
	    catch (Exception e)
	    {
	    	log.error("Unable to load properties.properties",e);
			throw new RuntimeException("Unable to load SettingsDTO class");
		}
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T getBean(Class<T> cls)
	{
		return (T)beanMap.get(cls);
	}

}
