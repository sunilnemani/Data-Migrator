/*
*
*N Sunil 
*
*/

package com.fsuite.BgvApi.inter.dto;

public class PastWorkExperienceDTO
{
	
	private Integer id;
	
	private Integer case_id;
	
	private int iam_currently_working;
	
    private String work_skills;
    
    private String ctc;
    
    private String reason_for_leaving_job;
    
    private String employee_id;
    
    private String reporting_manager_contact_number;
    
    private String name_of_reporting_manager;
    
    private String reporting_manager_email_id;
    
    private String last_3_months_payslips;
    
    private String appraisal_letter;
    
    private String relieving_letter;
    
    private String offer_letter_of_previous_company;
    
    private String work_experience_proof;
    
    private String company;
    
    private String title;
    
    private String location;
    
    private String from_date;
    
    private String to_date;
    
    private String summary;

    
	public Integer getId()
	{
		return id;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public Integer getCase_id()
	{
		return case_id;
	}

	public void setCase_id(Integer case_id)
	{
		this.case_id = case_id;
	}

	public int getIam_currently_working()
	{
		return iam_currently_working;
	}

	public void setIam_currently_working(int iam_currently_working)
	{
		this.iam_currently_working = iam_currently_working;
	}

	public String getWork_skills()
	{
		return work_skills;
	}

	public void setWork_skills(String work_skills)
	{
		this.work_skills = work_skills;
	}

	public String getCtc()
	{
		return ctc;
	}

	public void setCtc(String ctc)
	{
		this.ctc = ctc;
	}

	public String getReason_for_leaving_job()
	{
		return reason_for_leaving_job;
	}

	public void setReason_for_leaving_job(String reason_for_leaving_job)
	{
		this.reason_for_leaving_job = reason_for_leaving_job;
	}

	public String getEmployee_id()
	{
		return employee_id;
	}

	public void setEmployee_id(String employee_id)
	{
		this.employee_id = employee_id;
	}

	public String getReporting_manager_contact_number()
	{
		return reporting_manager_contact_number;
	}

	public void setReporting_manager_contact_number(
			String reporting_manager_contact_number)
	{
		this.reporting_manager_contact_number = reporting_manager_contact_number;
	}

	public String getName_of_reporting_manager()
	{
		return name_of_reporting_manager;
	}

	public void setName_of_reporting_manager(String name_of_reporting_manager)
	{
		this.name_of_reporting_manager = name_of_reporting_manager;
	}

	public String getReporting_manager_email_id()
	{
		return reporting_manager_email_id;
	}

	public void setReporting_manager_email_id(String reporting_manager_email_id)
	{
		this.reporting_manager_email_id = reporting_manager_email_id;
	}

	public String getLast_3_months_payslips()
	{
		return last_3_months_payslips;
	}

	public void setLast_3_months_payslips(String last_3_months_payslips)
	{
		this.last_3_months_payslips = last_3_months_payslips;
	}

	public String getAppraisal_letter()
	{
		return appraisal_letter;
	}

	public void setAppraisal_letter(String appraisal_letter)
	{
		this.appraisal_letter = appraisal_letter;
	}

	public String getRelieving_letter()
	{
		return relieving_letter;
	}

	public void setRelieving_letter(String relieving_letter)
	{
		this.relieving_letter = relieving_letter;
	}

	public String getOffer_letter_of_previous_company()
	{
		return offer_letter_of_previous_company;
	}

	public void setOffer_letter_of_previous_company(
			String offer_letter_of_previous_company)
	{
		this.offer_letter_of_previous_company = offer_letter_of_previous_company;
	}

	public String getWork_experience_proof()
	{
		return work_experience_proof;
	}

	public void setWork_experience_proof(String work_experience_proof)
	{
		this.work_experience_proof = work_experience_proof;
	}

	public String getCompany()
	{
		return company;
	}

	public void setCompany(String company)
	{
		this.company = company;
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public String getLocation()
	{
		return location;
	}

	public void setLocation(String location)
	{
		this.location = location;
	}

	public String getFrom_date()
	{
		return from_date;
	}

	public void setFrom_date(String from_date)
	{
		this.from_date = from_date;
	}

	public String getTo_date()
	{
		return to_date;
	}

	public void setTo_date(String to_date)
	{
		this.to_date = to_date;
	}

	public String getSummary()
	{
		return summary;
	}

	public void setSummary(String summary)
	{
		this.summary = summary;
	}

	@Override
	public String toString()
	{
		return "PastWorkExperienceDTO [id=" + id + ", case_id=" + case_id
				+ ", iam_currently_working=" + iam_currently_working
				+ ", work_skills=" + work_skills + ", ctc=" + ctc
				+ ", reason_for_leaving_job=" + reason_for_leaving_job
				+ ", employee_id=" + employee_id
				+ ", reporting_manager_contact_number="
				+ reporting_manager_contact_number
				+ ", name_of_reporting_manager=" + name_of_reporting_manager
				+ ", reporting_manager_email_id=" + reporting_manager_email_id
				+ ", last_3_months_payslips=" + last_3_months_payslips
				+ ", appraisal_letter=" + appraisal_letter
				+ ", relieving_letter=" + relieving_letter
				+ ", offer_letter_of_previous_company="
				+ offer_letter_of_previous_company + ", work_experience_proof="
				+ work_experience_proof + ", company=" + company + ", title="
				+ title + ", location=" + location + ", from_date=" + from_date
				+ ", to_date=" + to_date + ", summary=" + summary + "]";
	}

	

}
