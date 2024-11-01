/*
*
*N Sunil 
*
*/

package com.fsuite.BgvApi.inter.dto;

import java.util.Date;

import org.apache.logging.log4j.core.util.CronExpression;

import com.fsuite.BgvApi.db.Database;
import com.fsuite.BgvApi.service.MigrateService;
import com.fsuite.BgvApi.utils.Value;

public class SettingsDTO
{
	
	@Value("${INITIAL_DELAY}")
	private Integer initialDelay;
	
	@Value("${DELAY}")
	private Integer delay;
	
	@Value("${BATCH_SIZE}")
	private Integer batchSize;
	
	@Value("${DATA_CRON_EXPRESSION}")
	private String cronExpressionFormat;
	
	private CronExpression cronExpression;
	
	private Date nextRunTime;
	
	private MigrateService migrateService;
	
	private Database intermediateDb;
	
	private Database actDb;

	public Integer getInitialDelay()
	{
		return initialDelay;
	}

	public void setInitialDelay(Integer initialDelay)
	{
		this.initialDelay = initialDelay;
	}

	public Integer getDelay()
	{
		return delay;
	}

	public void setDelay(Integer delay)
	{
		this.delay = delay;
	}

	public Integer getBatchSize()
	{
		return batchSize;
	}

	public void setBatchSize(Integer batchSize)
	{
		this.batchSize = batchSize;
	}

	public String getCronExpressionFormat()
	{
		return cronExpressionFormat;
	}

	public void setCronExpressionFormat(String cronExpressionFormat)
	{
		this.cronExpressionFormat = cronExpressionFormat;
	}

	public CronExpression getCronExpression()
	{
		return cronExpression;
	}

	public void setCronExpression(CronExpression cronExpression)
	{
		this.cronExpression = cronExpression;
	}

	public Date getNextRunTime()
	{
		return nextRunTime;
	}

	public void setNextRunTime(Date nextRunTime)
	{
		this.nextRunTime = nextRunTime;
	}

	public MigrateService getMigrateService()
	{
		return migrateService;
	}

	public void setMigrateService(MigrateService migrateService)
	{
		this.migrateService = migrateService;
	}	

	public Database getIntermediateDb()
	{
		return intermediateDb;
	}

	public void setIntermediateDb(Database intermediateDb)
	{
		this.intermediateDb = intermediateDb;
	}

	public Database getActDb()
	{
		return actDb;
	}

	public void setActDb(Database actDb)
	{
		this.actDb = actDb;
	}

	@Override
	public String toString()
	{
		return "SettingsDTO [initialDelay=" + initialDelay + ", delay=" + delay
				+ ", batchSize=" + batchSize + ", cronExpressionFormat="
				+ cronExpressionFormat + ", cronExpression=" + cronExpression
				+ ", nextRunTime=" + nextRunTime + "]";
	}
}
