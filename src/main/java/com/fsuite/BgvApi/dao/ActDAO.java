/*
*
*N Sunil 
*
*/

package com.fsuite.BgvApi.dao;

import com.fsuite.BgvApi.inter.dto.CandidateDataDTO;

public interface ActDAO
{
	
	public void insertCandidateDetails(CandidateDataDTO candidateDataDTO);
	
	public void insertAddressDetails(CandidateDataDTO candidateDataDTO);

	public void insertEducationDetails(CandidateDataDTO candidateDataDTO);
	
	public void insertWorkExperience(CandidateDataDTO candidateDataDTO);
	
}
