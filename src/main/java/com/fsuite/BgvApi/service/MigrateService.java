/*
*
*N Sunil 
*
*/

package com.fsuite.BgvApi.service;

import java.util.List;

import com.fsuite.BgvApi.inter.dto.CandidateDataDTO;

public interface MigrateService
{

	public List<CandidateDataDTO> getCandidateList(Integer batchSize);
	
	public void migrateData(CandidateDataDTO candidateDataDTO);

}
