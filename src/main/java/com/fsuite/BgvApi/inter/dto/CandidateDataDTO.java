/*
*
*N Sunil 
*
*/

package com.fsuite.BgvApi.inter.dto;

import java.util.List;

public class CandidateDataDTO
{
	
	private Integer case_id;
	
	private String unique_id;
	
	private String bgv_id;
	
	private String api_key;
	
    private String candidate_id;
    
    private String employee_id;
    
    private String employee_status;
    
    private String client_id;
    
    private String details_url;
    
    private Integer retry_count;
    
    private String status;
    
    private BgvFormDataDTO bgv_form_data;
    
    private List<AddressDetailsDTO> addressDetails;
    
    private List<EducationDetailsDTO> educationDetails;
    
    private List<PastWorkExperienceDTO> workExperience;
	
    
	public Integer getCase_id()
	{
		return case_id;
	}

	public void setCase_id(Integer case_id)
	{
		this.case_id = case_id;
	}

	public String getUnique_id()
	{
		return unique_id;
	}

	public void setUnique_id(String unique_id)
	{
		this.unique_id = unique_id;
	}
	
	public String getBgv_id()
	{
		return bgv_id;
	}

	public void setBgv_id(String bgv_id)
	{
		this.bgv_id = bgv_id;
	}
	
	public String getApi_key()
	{
		return api_key;
	}

	public void setApi_key(String api_key)
	{
		this.api_key = api_key;
	}

	public String getCandidate_id()
	{
		return candidate_id;
	}

	public void setCandidate_id(String candidate_id)
	{
		this.candidate_id = candidate_id;
	}

	public String getEmployee_id()
	{
		return employee_id;
	}

	public void setEmployee_id(String employee_id)
	{
		this.employee_id = employee_id;
	}

	
	public String getClient_id()
	{
		return client_id;
	}

	public void setClient_id(String client_id)
	{
		this.client_id = client_id;
	}

	public String getEmployee_status()
	{
		return employee_status;
	}

	public void setEmployee_status(String employee_status)
	{
		this.employee_status = employee_status;
	}

	public String getDetails_url()
	{
		return details_url;
	}

	public void setDetails_url(String details_url)
	{
		this.details_url = details_url;
	}

	public Integer getRetry_count()
	{
		return retry_count;
	}

	public void setRetry_count(Integer retry_count)
	{
		this.retry_count = retry_count;
	}

	public String getStatus()
	{
		return status;
	}

	public void setStatus(String status)
	{
		this.status = status;
	}

	public BgvFormDataDTO getBgv_form_data()
	{
		return bgv_form_data;
	}

	public void setBgv_form_data(BgvFormDataDTO bgv_form_data)
	{
		this.bgv_form_data = bgv_form_data;
	}
	
	public List<AddressDetailsDTO> getAddressDetails()
	{
		return addressDetails;
	}

	public void setAddressDetails(List<AddressDetailsDTO> addressDetails)
	{
		this.addressDetails = addressDetails;
	}

	public List<EducationDetailsDTO> getEducationDetails()
	{
		return educationDetails;
	}

	public void setEducationDetails(List<EducationDetailsDTO> educationDetails)
	{
		this.educationDetails = educationDetails;
	}

	public List<PastWorkExperienceDTO> getWorkExperience()
	{
		return workExperience;
	}

	public void setWorkExperience(List<PastWorkExperienceDTO> workExperience)
	{
		this.workExperience = workExperience;
	}

	@Override
	public String toString()
	{
		return "CandidateDataDTO [case_id=" + case_id + ", unique_id="
				+ unique_id + ", bgv_id=" + bgv_id + ", api_key=" + api_key
				+ ", candidate_id=" + candidate_id + ", employee_id="
				+ employee_id + ", employee_status=" + employee_status
				+ ", client_id=" + client_id + ", details_url=" + details_url
				+ ", retry_count=" + retry_count + ", status=" + status
				+ ", bgv_form_data=" + bgv_form_data + ", addressDetails="
				+ addressDetails + ", educationDetails=" + educationDetails
				+ ", workExperience=" + workExperience + "]";
	}
	
}
