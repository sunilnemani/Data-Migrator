/*
*
*N Sunil 
*
*/

package com.fsuite.BgvApi.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fsuite.BgvApi.dao.IntermediateDAO;
import com.fsuite.BgvApi.exception.AppException;
import com.fsuite.BgvApi.inter.dto.AddressDetailsDTO;
import com.fsuite.BgvApi.inter.dto.BgvFormDataDTO;
import com.fsuite.BgvApi.inter.dto.CandidateDataDTO;
import com.fsuite.BgvApi.inter.dto.EducationDetailsDTO;
import com.fsuite.BgvApi.inter.dto.PastWorkExperienceDTO;

public class IntermediateDAOImpl extends BaseDAO implements IntermediateDAO
{
	private static final Logger log = LogManager.getLogger(IntermediateDAOImpl.class);
	
	private static final String GET_CANDIDATE_LIST = "select case_id,unique_id,bgv_id,client_id,employee_id,candidate_id from cand_data where status='N' LIMIT ?";
	private static final String GET_CANDIDATE_BGV_DATA="select case_id,adharcard,date_of_birth,firstname,gender,lastname,pancard,personal_email_id,primary_mobile from bgv_form_data where case_id=?";
	private static final String GET_CANDIDATE_ADDRESS_DETAILS = "select case_id,address,address_period_of_stay_from,address_period_of_stay_to,address_proof,city,country,landmark,pincode,state,street,address_type from address_details where case_id=?";
	private static final String GET_CANDIDATE_EDUCATION_DETAILS="select case_id,_10th_certificate_pl,_12th_certificatepl,activities,university,institution_name,address_of_educational_institute,course_type,education_category,educational_documents_proofs,field_of_study,from_date,to_date,gpa_percentage,high_edu_qualification,iam_currently_student,level_of_study,max_gpa_percentage,notes,other_education_category,other_field_of_study,other_level_of_study,registration_number from education_details where case_id=?";
	private static final String GET_CANDIDATE_WORK_DETAILS = "select case_id, appraisal_letter, company, ctc, employee_id, from_date, iam_currently_working, last_3_months_payslips, location,name_of_reporting_manager, offer_letter_of_previous_company, reason_for_leaving_job, relieving_letter, reporting_manager_contact_number, reporting_manager_email_id, summary, title, to_date, work_experience_proof, work_skills from past_work_experience where case_id=?";
	
	private static final String UPDATE_CANDIDATE_STATUS = "update cand_data set status=? where case_id=?";
	
	public IntermediateDAOImpl(Connection connection)
	{
		super(connection);
	}
	
	@Override
	public List<CandidateDataDTO> getCandidateList(Integer batchSize)
	{
		List<CandidateDataDTO> candList = null;
		PreparedStatement preparedStatement=null;
		ResultSet resultSet = null;
		try
		{
			preparedStatement = connection.prepareStatement(GET_CANDIDATE_LIST);
			preparedStatement.setInt(1, batchSize);
			resultSet = preparedStatement.executeQuery();
			
			candList = new ArrayList<>();
			CandidateDataDTO loadedDTO = null;
			while(resultSet.next())
			{
				loadedDTO = new CandidateDataDTO();
				loadedDTO.setCase_id(resultSet.getInt("case_id"));
				loadedDTO.setUnique_id(resultSet.getString("unique_id"));
				loadedDTO.setBgv_id(resultSet.getString("bgv_id"));
				loadedDTO.setClient_id(resultSet.getString("client_id"));
				loadedDTO.setEmployee_id(resultSet.getString("employee_id"));
				loadedDTO.setCandidate_id(resultSet.getString("candidate_id"));
				candList.add(loadedDTO);
			}
		}
		catch(Exception e)
		{
			log.error("Error in fetching Candidate List",e);
			throw new AppException(AppException.ERR_IN_FETCHING_CANDIDATE_LIST, "Error in fetching Candidate List",e);
		}
		finally
		{
			close(preparedStatement,resultSet);
		}
		return candList;
	}
	
	@Override
	public BgvFormDataDTO getBgvFormData(CandidateDataDTO candidateDataDTO)
	{
		PreparedStatement preparedStatement=null;
		ResultSet resultSet = null;
		BgvFormDataDTO bgvFormDataDTO=null;
		try
		{
			preparedStatement = connection.prepareStatement(GET_CANDIDATE_BGV_DATA);
			preparedStatement.setInt(1, candidateDataDTO.getCase_id());
			resultSet = preparedStatement.executeQuery();
			if(resultSet.next())
			{
				bgvFormDataDTO = new BgvFormDataDTO();
				bgvFormDataDTO.setCase_id(resultSet.getInt("case_id"));
				bgvFormDataDTO.setAdharcard(resultSet.getString("adharcard"));
				bgvFormDataDTO.setDate_of_birth(resultSet.getString("date_of_birth"));
				bgvFormDataDTO.setFirstname(resultSet.getString("firstname"));
				bgvFormDataDTO.setGender(resultSet.getString("gender"));
				bgvFormDataDTO.setLastname(resultSet.getString("lastname"));
				bgvFormDataDTO.setPancard(resultSet.getString("pancard"));
				bgvFormDataDTO.setPersonal_email_id(resultSet.getString("personal_email_id"));
				bgvFormDataDTO.setPrimary_mobile(resultSet.getString("primary_mobile"));
			}
		}
		catch(Exception e)
		{
			log.error("Error while fetching comments from db",e);
			throw new AppException(AppException.ERR_IN_FETCHING_CANDIDATE_EDUCATION_DETAILS, "Error in fetching Candidate Education Details",e);
		}
		finally
		{
			close(preparedStatement,resultSet);
		}
		return bgvFormDataDTO;
	}

	@Override
	public List<AddressDetailsDTO> getAddressDetails(Integer case_id)
	{
		List<AddressDetailsDTO> addList=null;
		AddressDetailsDTO addressDetailsDTO=null;
		PreparedStatement preparedStatement=null;
		ResultSet resultSet = null;
		try
		{
			preparedStatement = connection.prepareStatement(GET_CANDIDATE_ADDRESS_DETAILS);
			preparedStatement.setInt(1, case_id);
			resultSet = preparedStatement.executeQuery();
			addList=new ArrayList<AddressDetailsDTO>();
			while (resultSet.next()) 
			{
				addressDetailsDTO = new AddressDetailsDTO();
				
                addressDetailsDTO.setCase_id(resultSet.getInt("case_id"));
                addressDetailsDTO.setAddress(resultSet.getString("address"));
                addressDetailsDTO.setAddress_period_of_stay_from(resultSet.getString("address_period_of_stay_from"));
                addressDetailsDTO.setAddress_period_of_stay_to(resultSet.getString("address_period_of_stay_to"));
                addressDetailsDTO.setAddress_proof(resultSet.getString("address_proof"));
                addressDetailsDTO.setCity(resultSet.getString("city"));
                addressDetailsDTO.setCountry(resultSet.getString("country"));
                addressDetailsDTO.setLandmark(resultSet.getString("landmark"));
                addressDetailsDTO.setPincode(resultSet.getString("pincode"));
                addressDetailsDTO.setState(resultSet.getString("state"));
                addressDetailsDTO.setStreet(resultSet.getString("street"));
                addressDetailsDTO.setAddress_type(resultSet.getString("address_type"));
                
                addList.add(addressDetailsDTO);

            }
		}
		catch(Exception e)
		{
			log.error("Error while fetching comments from db",e);
			throw new AppException(AppException.ERR_IN_FETCHING_CANDIDATE_ADDRESS_DETAILS, "Error in fetching Candidate Address Details",e);
		}
		finally
		{
			close(preparedStatement,resultSet);
		}
		return addList;
	}
	
	@Override
	public List<EducationDetailsDTO> getEducationDetails(Integer case_id)
	{
		List<EducationDetailsDTO> eduList=null;
		EducationDetailsDTO eduDTO=null;
		PreparedStatement preparedStatement=null;
		ResultSet resultSet = null;
		try
		{
			preparedStatement = connection.prepareStatement(GET_CANDIDATE_EDUCATION_DETAILS);
			preparedStatement.setInt(1, case_id);
			resultSet = preparedStatement.executeQuery();
			eduList=new ArrayList<EducationDetailsDTO>();
			while (resultSet.next()) 
			{
				eduDTO = new EducationDetailsDTO();
				eduDTO.setCase_id(resultSet.getInt("case_id"));
				eduDTO.set_10th_certificate_pl(resultSet.getString("_10th_certificate_pl"));
				eduDTO.set_12th_certificatepl(resultSet.getString("_12th_certificatepl"));
				eduDTO.setActivities(resultSet.getString("activities"));
				eduDTO.setUniversity(resultSet.getString("university"));
				eduDTO.setInstitution_name(resultSet.getString("institution_name"));
				eduDTO.setAddress_of_educational_institute(resultSet.getString("address_of_educational_institute"));
				eduDTO.setCourse_type(resultSet.getString("course_type"));
				eduDTO.setEducation_category(resultSet.getString("education_category"));
				eduDTO.setEducational_documents_proofs(resultSet.getString("educational_documents_proofs"));
				eduDTO.setField_of_study(resultSet.getString("field_of_study"));
				eduDTO.setFrom_date(resultSet.getString("from_date"));
				eduDTO.setTo_date(resultSet.getString("to_date"));
				eduDTO.setGpa_percentage(resultSet.getString("gpa_percentage"));
				eduDTO.setHigh_edu_qualification(resultSet.getInt("high_edu_qualification"));
				eduDTO.setIam_currently_student(resultSet.getInt("iam_currently_student"));
				eduDTO.setLevel_of_study(resultSet.getString("level_of_study"));
				eduDTO.setMax_gpa_percentage(resultSet.getString("max_gpa_percentage"));
				eduDTO.setNotes(resultSet.getString("notes"));
				eduDTO.setOther_education_category(resultSet.getString("other_education_category"));
				eduDTO.setOther_field_of_study(resultSet.getString("other_field_of_study"));
				eduDTO.setOther_level_of_study(resultSet.getString("other_level_of_study"));
				eduDTO.setRegistration_number(resultSet.getString("registration_number"));
                eduList.add(eduDTO);
            }
		}
		catch(Exception e)
		{
			log.error("Error while fetching comments from db",e);
			throw new AppException(AppException.ERR_IN_FETCHING_CANDIDATE_EDUCATION_DETAILS, "Error in fetching Candidate Education Details",e);
		}
		finally
		{
			close(preparedStatement,resultSet);
		}
		return eduList;
	}
	
	public List<PastWorkExperienceDTO> getPastWorkExperienceDetails(Integer case_id)
	{
		List<PastWorkExperienceDTO> workList=null;
		PastWorkExperienceDTO workDetails=null;
		PreparedStatement preparedStatement=null;
		ResultSet resultSet = null;
		try
		{
			preparedStatement = this.connection.prepareStatement(GET_CANDIDATE_WORK_DETAILS);
			preparedStatement.setInt(1, case_id);
			resultSet = preparedStatement.executeQuery();
			if(resultSet!=null)
			{
				workList=new ArrayList<PastWorkExperienceDTO>();
				while (resultSet.next()) 
				{
					workDetails = new PastWorkExperienceDTO();
					workDetails.setCase_id(resultSet.getInt("case_id"));
					workDetails.setAppraisal_letter(resultSet.getString("appraisal_letter"));
					workDetails.setCompany(resultSet.getString("company"));
					workDetails.setCtc(resultSet.getString("ctc"));
					workDetails.setEmployee_id(resultSet.getString("employee_id"));
					workDetails.setFrom_date(resultSet.getString("from_date"));
					workDetails.setIam_currently_working(resultSet.getInt("iam_currently_working"));
					workDetails.setLast_3_months_payslips(resultSet.getString("last_3_months_payslips"));
					workDetails.setLocation(resultSet.getString("location"));
					workDetails.setName_of_reporting_manager(resultSet.getString("name_of_reporting_manager"));
					workDetails.setOffer_letter_of_previous_company(resultSet.getString("offer_letter_of_previous_company"));
					workDetails.setReason_for_leaving_job(resultSet.getString("reason_for_leaving_job"));
					workDetails.setRelieving_letter(resultSet.getString("relieving_letter"));
					workDetails.setReporting_manager_contact_number(resultSet.getString("reporting_manager_contact_number"));
					workDetails.setReporting_manager_email_id(resultSet.getString("reporting_manager_email_id"));
					workDetails.setSummary(resultSet.getString("summary"));
					workDetails.setTitle(resultSet.getString("title"));
					workDetails.setTo_date(resultSet.getString("to_date"));
					workDetails.setWork_experience_proof(resultSet.getString("work_experience_proof"));
					workDetails.setWork_skills(resultSet.getString("work_skills"));
					workList.add(workDetails);
				}
			}
		}
		catch(Exception e)
		{
			log.error("Error in fetching Candidate Work Details",e);
			throw new AppException(AppException.ERR_IN_FETCHING_CANDIDATE_WORK_DETAILS, "Error in fetching Candidate Work Details",e);	
		}
		finally
		{
			close(preparedStatement,resultSet);
		}
		return workList;
	}
	
	@Override
	public void updateCandidateStatus(Integer case_id, String status)
	{
		PreparedStatement preparedStatement = null;
		try
		{
			preparedStatement = this.connection.prepareStatement(UPDATE_CANDIDATE_STATUS);
			preparedStatement.setString(1, status);
			preparedStatement.setInt(2, case_id);
			preparedStatement.execute();
		}
		catch(Exception e)
		{
			log.error("Error while updating candidate status in db",e);
			throw new AppException(AppException.ERR_IN_UPDATING_CANDIDATE_STATUS, "Error while updating candidate status in db",e);	
		}
		finally
		{
			close(preparedStatement);
		}
	}

}
