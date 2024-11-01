/*
*
*N Sunil 
*
*/

package com.fsuite.BgvApi.serviceImpl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fsuite.BgvApi.dao.ActDAO;
import com.fsuite.BgvApi.dao.IntermediateDAO;
import com.fsuite.BgvApi.daoImpl.ActDAOImpl;
import com.fsuite.BgvApi.daoImpl.IntermediateDAOImpl;
import com.fsuite.BgvApi.db.Database;
import com.fsuite.BgvApi.inter.dto.AddressDetailsDTO;
import com.fsuite.BgvApi.inter.dto.BgvFormDataDTO;
import com.fsuite.BgvApi.inter.dto.CandidateDataDTO;
import com.fsuite.BgvApi.inter.dto.EducationDetailsDTO;
import com.fsuite.BgvApi.inter.dto.PastWorkExperienceDTO;
import com.fsuite.BgvApi.service.MigrateService;

public class MigrateServiceImpl implements MigrateService
{
	
	private IntermediateDAO intermediateDAO;
	private ActDAO actDAO;
	
	private Database interDb;
	private Database actDb;
	
	
	private static final Logger log = LogManager.getLogger(MigrateServiceImpl.class);
	private static final String Y = "Y";
	
	public MigrateServiceImpl(Database interDb, Database actDb)
	{
		this.interDb=interDb;
		this.actDb=actDb;
		intermediateDAO=new IntermediateDAOImpl(this.interDb.getConnection());
		actDAO = new ActDAOImpl(this.actDb.getConnection());
	}

	@Override
	public List<CandidateDataDTO> getCandidateList(Integer batchSize)
	{
		return this.intermediateDAO.getCandidateList(batchSize);
	}

	@Override
	public void migrateData(CandidateDataDTO candidateDataDTO)
	{
		log.info("[MigrateServiceImpl][migrateData]");
		BgvFormDataDTO bgv = null;
		List<AddressDetailsDTO> addList = null;
		List<EducationDetailsDTO> eduList = null;
		List<PastWorkExperienceDTO> workList = null;
		try
		{
			bgv = this.intermediateDAO.getBgvFormData(candidateDataDTO);
			candidateDataDTO.setBgv_form_data(bgv);
			
			addList = this.intermediateDAO.getAddressDetails(candidateDataDTO.getCase_id());
			candidateDataDTO.setAddressDetails(addList);
			
			eduList = this.intermediateDAO.getEducationDetails(candidateDataDTO.getCase_id());
			candidateDataDTO.setEducationDetails(eduList);
			
			workList = this.intermediateDAO.getPastWorkExperienceDetails(candidateDataDTO.getCase_id());
			candidateDataDTO.setWorkExperience(workList);
			
			this.actDAO.insertCandidateDetails(candidateDataDTO);
			this.actDAO.insertAddressDetails(candidateDataDTO);
			this.actDAO.insertEducationDetails(candidateDataDTO);
			this.actDAO.insertWorkExperience(candidateDataDTO);
			
			this.intermediateDAO.updateCandidateStatus(candidateDataDTO.getCase_id(), Y);
			
			interDb.commitTransaction();
			actDb.commitTransaction();
		}
		catch(Exception e)
		{
			log.error("Error-[MigrateServiceImpl][migrateData]",e);
			interDb.rollbackTransaction();
			actDb.rollbackTransaction();
		}
	}

}
