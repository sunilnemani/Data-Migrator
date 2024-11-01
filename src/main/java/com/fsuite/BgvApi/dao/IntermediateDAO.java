/*
*
*N Sunil 
*
*/

package com.fsuite.BgvApi.dao;

import java.util.List;

import com.fsuite.BgvApi.inter.dto.AddressDetailsDTO;
import com.fsuite.BgvApi.inter.dto.BgvFormDataDTO;
import com.fsuite.BgvApi.inter.dto.CandidateDataDTO;
import com.fsuite.BgvApi.inter.dto.EducationDetailsDTO;
import com.fsuite.BgvApi.inter.dto.PastWorkExperienceDTO;

public interface IntermediateDAO
{
	
	public List<CandidateDataDTO> getCandidateList(Integer batchSize);
	
	public BgvFormDataDTO getBgvFormData(CandidateDataDTO candidateDataDTO);
	
	public List<AddressDetailsDTO> getAddressDetails(Integer case_id);
	
	public List<EducationDetailsDTO> getEducationDetails(Integer case_id);
	
	public List<PastWorkExperienceDTO> getPastWorkExperienceDetails(Integer case_id);

	public void updateCandidateStatus(Integer case_id, String status);
	
}
