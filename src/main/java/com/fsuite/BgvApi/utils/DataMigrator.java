/*
*
*N Sunil 
*
*/

package com.fsuite.BgvApi.utils;

import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fsuite.BgvApi.db.Database;
import com.fsuite.BgvApi.inter.dto.CandidateDataDTO;
import com.fsuite.BgvApi.inter.dto.SettingsDTO;
import com.fsuite.BgvApi.service.MigrateService;
import com.fsuite.BgvApi.starter.DataMigratorApplication;

public class DataMigrator implements Runnable
{
	
	private static final Logger log = LogManager.getLogger(DataMigrator.class);
	
//	private static final String INTERMEDIATE_DB_PROPERTIES="intermediate_db.properties";
//	private static final String DB_PROPERTIES="db.properties";
	
//	private MigrateService migrateService=null;

	@Override
	public void run()
	{
		try
		{
			SettingsDTO settingsDTO = DataMigratorApplication.getBean(SettingsDTO.class);
			Date currentTime= new Date();
			if(currentTime.compareTo(settingsDTO.getNextRunTime())>=0)
			{
				log.info("[DataMigrator][run]");
				doProcess();
			}
		}
		catch(Exception e)
		{
			log.error("[DataMigrator][run]",e);
		}

	}
	
	private void doProcess()
	{
		Database intermediateDb=null;
		Database actDb=null;
		MigrateService migrateService = null;
		try
		{
			SettingsDTO settingsDTO = DataMigratorApplication.getBean(SettingsDTO.class);
			
//			intermediateDb=new Database(INTERMEDIATE_DB_PROPERTIES);
//			actDb = new Database(DB_PROPERTIES);
//			migrateService = new MigrateServiceImpl(intermediateDb,actDb);
			
			migrateService = settingsDTO.getMigrateService();
			intermediateDb = settingsDTO.getIntermediateDb();
			actDb = settingsDTO.getActDb();
			List<CandidateDataDTO> candList =migrateService.getCandidateList(settingsDTO.getBatchSize());
			if(candList==null || candList.isEmpty())
			{
		    	settingsDTO.setNextRunTime(settingsDTO.getCronExpression().getNextValidTimeAfter(new Date()));
			}
			else
			{
				for(CandidateDataDTO cand : candList)
				{
					migrateService.migrateData(cand);
				}
			}
		}
		catch(Exception e)
		{
			log.error("Error",e);
			intermediateDb.rollbackTransaction();
			actDb.rollbackTransaction();
		}
	}

}
