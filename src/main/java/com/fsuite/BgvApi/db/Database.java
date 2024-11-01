package com.fsuite.BgvApi.db;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.util.Properties;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.dbcp.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fsuite.BgvApi.exception.AppException;

public class Database
{
	
	private static final Logger log = LoggerFactory.getLogger(Database.class);

	private BasicDataSource datasource;
	private Connection connection;

	public Database(String fileName)
	{
		if(datasource == null)
		{
			try
			{
				initialiseDatabase(fileName);
			}
			catch (Exception ex)
			{
				throw new AppException("DB_INITIALISATION_FAILED", "Db initialised failed",ex);
			}
		}
		connection = createConnection();
	}

	public boolean initialiseDatabase(String fileName) throws Exception
	{
		datasource = getDataSource(fileName);
		return true;
	}

	private static BasicDataSource getDataSource(String fileName) throws Exception
	{
		BasicDataSource basicDataSource;
		try
		{
			basicDataSource = new BasicDataSource();
			Properties properties = readProperties(fileName);
			for (String propertyname : properties.stringPropertyNames())
			{
				log.debug("Setting " + propertyname + " = " + properties.getProperty(propertyname));
				BeanUtils.setProperty(basicDataSource, propertyname, properties.getProperty(propertyname));
			}
		}
		catch (Exception ex)
		{
			log.error("Erro creating datasource", ex);
			throw new AppException("ERR_WHILE_CREATING_DB_CONNECTIONS", "Error occurred while creating datasource", ex);
		}
		return basicDataSource;
	}

	private static Properties readProperties(String fileName)
	{
		Properties properties = null;
		InputStream in = null;
		try
		{
			in = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
			if (in == null)
			{
				in = new FileInputStream(fileName);
			}
			properties = new Properties();
			properties.load(in);
		}
		catch (Exception ex)
		{
			log.error("Error while loading 2fa-rest.properties", ex);
			throw new AppException("ERR_WHILE_READING_PROPERTIES_FILE", "Error while reading properties file", ex);
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
		return properties;
	}

	public Connection createConnection()
	{
		Connection connection = null;
		try
		{
			connection = datasource.getConnection();
			connection.setAutoCommit(false);
		}
		catch (Exception ex)
		{
			log.error("Error ",ex);
			throw new AppException("ERR_CREATING_CONNECTION", "Error occurred while creating connection",ex);
		}
		return connection;
	}

	public Connection getConnection()
	{
		return connection;
	}

	public boolean beginTransaction()
	{
		boolean returnVal = false;
		try
		{
			connection.setAutoCommit(false);
			returnVal = true;
		}
		catch (Exception ex)
		{
			log.error("Error ",ex);
			throw new AppException("ERR_BEGINING_TRANSACTION", "Error while beginning a transaction");
		}
		return returnVal;
	}

	public boolean commitTransaction()
	{
		boolean returnVal = false;
		try
		{
			connection.commit();
			returnVal = true;
		}
		catch (Exception ex)
		{
			log.error("Error ",ex);
			throw new AppException("ERR_COMMITTING_TRANSACTION", "Error while beginning a transaction");
		}
		return returnVal;
	}

	public boolean rollbackTransaction()
	{
		boolean returnVal = false;
		try
		{
			connection.rollback();
			returnVal = true;
		}
		catch (Exception ex)
		{
			log.error("Error ",ex);
			throw new AppException("ERR_ROLLBACK_TRANSACTION", "Error while beginning a transaction");
		}
		return returnVal;
	}

	public boolean closeConnection()
	{
		return closeConnection(connection);
	}

	public boolean closeConnection(Connection connection)
	{
		boolean returnVal = false;
		try
		{
			if(connection != null)
			{
				connection.close();
				returnVal = true;
			}
		}
		catch (Exception ex)
		{
			log.error("Error ",ex);
		}
		return returnVal;
	}
}
