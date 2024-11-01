package com.fsuite.BgvApi.daoImpl;

import java.sql.Connection;

public class BaseDAO
{
	protected Connection connection;

	public BaseDAO(Connection connection)
	{
		this.connection = connection;
	}

	protected void close(AutoCloseable... closeables )
	{
		if(closeables != null)
		{
			for(int i = 0 ;i < closeables.length ; i++)
			{
				if(closeables[i] != null)
				{
					try
					{
						closeables[i].close();
					}
					catch (Exception ex)
					{
						// TODO: handle exception
					}
				}
			}
		}
	}
}
