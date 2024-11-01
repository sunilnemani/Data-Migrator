/*
*
*N Sunil 
*
*/

package com.fsuite.BgvApi.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fsuite.BgvApi.constants.AppConstants;
import com.fsuite.BgvApi.dao.ActDAO;
import com.fsuite.BgvApi.exception.AppException;
import com.fsuite.BgvApi.inter.dto.AddressDetailsDTO;
import com.fsuite.BgvApi.inter.dto.CandidateDataDTO;
import com.fsuite.BgvApi.inter.dto.EducationDetailsDTO;
import com.fsuite.BgvApi.inter.dto.PastWorkExperienceDTO;

public class ActDAOImpl extends BaseDAO implements ActDAO
{
	
	private static final Logger log = LogManager.getLogger(ActDAOImpl.class);
	
	private static final String ADD_CANDIDATE_DETAILS = "insert into cv_candidates (caseid, candidate, employeeid, email, birthdate, mobile, clientid, status, candidate_notes, submtby, subdate ##Names##) values( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? ##Values##)";
	private static final String ADD_CANDIDATE_ADDRESS_DETAILS = "insert into cv_address (caseid, candidate, mobile, client, addresstype, component_name, address1, city, state, pincode, status) values ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String ADD_CANDIDATE_EDUCATION_DETAILS = "insert into cv_education (caseid, candidate, mobile, client, educationtype, rollno, degree, institute, university, passyear, vproof1, date) values ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String ADD_CANDIDATE_WORK_DETAILS = "insert into cv_employment (caseid, candidate, mobile, client, employmenttype, empid, organization, designation, department, startdate, enddate, salary, manager, mcontact, leavereason) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

	public ActDAOImpl(Connection connection)
	{
		super(connection);
	}
	
	@Override
	public void insertCandidateDetails(CandidateDataDTO candidateDataDTO)
	{
		PreparedStatement preparedStatement=null;
		String name = "";
		String value = "";
		try
		{
			int count=1;
			int val=1;
			if(candidateDataDTO.getAddressDetails()!=null && !candidateDataDTO.getAddressDetails().isEmpty())
			{
				for(int i=0 ; i<candidateDataDTO.getAddressDetails().size(); i++)
				{
					name+=",check"+count+", document"+count++;
					value+= ","+(candidateDataDTO.getAddressDetails().get(i).getAddress_type().equals(AppConstants.PERMANENT_ADDRESS_STATUS)?"'Permanent Address'":"'Current Address'")+", ?";
				}
			}
			if(candidateDataDTO.getEducationDetails()!=null && !candidateDataDTO.getEducationDetails().isEmpty())
			{
				count = 3;
				for(int i=0 ; i<candidateDataDTO.getEducationDetails().size(); i++)
				{
					name+=",check"+count+", document"+count++;
					if(i==0)
					{
						value+= ",'Highest Education'"+", ?";
					}
					else if(i==1)
					{
						value+= ",'Under Graduate Education'"+", ?";
						break;
					}
				}
			}
			if(candidateDataDTO.getWorkExperience()!=null && !candidateDataDTO.getWorkExperience().isEmpty())
			{
				count = 5;
				for(int i=0 ; i<candidateDataDTO.getWorkExperience().size(); i++)
				{
					name+=",check"+count+", document"+count++;
					if(i==0)
					{
						value+=",'Current Employement', ?";
					}
					else if(i==1)
					{
						value+=",'Previous Employement' ,?";
						break;
					}
				}
			}
			
			String query = ADD_CANDIDATE_DETAILS.replace("##Names##", name).replace("##Values##", value);
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(val++, candidateDataDTO.getUnique_id());
			preparedStatement.setString(val++, candidateDataDTO.getBgv_form_data().getFirstname()+" "+candidateDataDTO.getBgv_form_data().getLastname());
			preparedStatement.setString(val++, candidateDataDTO.getEmployee_id());
			preparedStatement.setString(val++, candidateDataDTO.getBgv_form_data().getPersonal_email_id());
			preparedStatement.setString(val++, candidateDataDTO.getBgv_form_data().getDate_of_birth());
			preparedStatement.setString(val++, candidateDataDTO.getBgv_form_data().getPrimary_mobile());
			preparedStatement.setString(val++, candidateDataDTO.getClient_id());
			preparedStatement.setString(val++, candidateDataDTO.getEmployee_status());
			preparedStatement.setString(val++, "");
			preparedStatement.setString(val++, "API");
			preparedStatement.setTimestamp(val++, new Timestamp(System.currentTimeMillis()));
			if(candidateDataDTO.getAddressDetails()!=null && !candidateDataDTO.getAddressDetails().isEmpty())
			{
				for(int i=0 ; i<candidateDataDTO.getAddressDetails().size(); i++)
				{
					if(i==0)
					{
						preparedStatement.setString(val++, candidateDataDTO.getAddressDetails().get(i).getAddress_proof());
					}
					else if(i==1)
					{
						preparedStatement.setString(val++, candidateDataDTO.getAddressDetails().get(i).getAddress_proof());
						break;
					}
						
				}
			}
			if(candidateDataDTO.getEducationDetails()!=null && !candidateDataDTO.getEducationDetails().isEmpty())
			{
				for(int i=0 ; i<candidateDataDTO.getEducationDetails().size(); i++)
				{
					if(i==0)
					{
						preparedStatement.setString(val++, candidateDataDTO.getEducationDetails().get(i).getEducational_documents_proofs());
					}
					else if(i==1)
					{
						preparedStatement.setString(val++, candidateDataDTO.getEducationDetails().get(i).getEducational_documents_proofs());
						break;
					}
						
				}
			}
			if(candidateDataDTO.getWorkExperience()!=null && !candidateDataDTO.getWorkExperience().isEmpty())
			{
				for(int i=0 ; i<candidateDataDTO.getWorkExperience().size(); i++)
				{
					if(i==0)
					{
						preparedStatement.setString(val++, candidateDataDTO.getWorkExperience().get(i).getWork_experience_proof());
					}
					else if(i==1)
					{
						preparedStatement.setString(val++, candidateDataDTO.getWorkExperience().get(i).getWork_experience_proof());
						break;
					}
				}
			}
			preparedStatement.execute();
		}
		catch(Exception e)
		{
			log.error("Error while fetching comments from db",e);
			throw new AppException(AppException.ERR_IN_INSERTING_CANDIDATE_DETAILS, "Error while Inserting Candidate Details in DB",e);
		}
		finally
		{
			close(preparedStatement);
		}
	}

	@Override
	public void insertAddressDetails(CandidateDataDTO candidateDataDTO)
	{
		PreparedStatement preparedStatement=null;
		List<AddressDetailsDTO> addList=null;
		try
		{
			addList = candidateDataDTO.getAddressDetails();
			preparedStatement = connection.prepareStatement(ADD_CANDIDATE_ADDRESS_DETAILS);
			for(AddressDetailsDTO address : addList)
			{
				preparedStatement.setString(1, candidateDataDTO.getUnique_id());
				preparedStatement.setString(2, candidateDataDTO.getBgv_form_data().getFirstname()+" "+candidateDataDTO.getBgv_form_data().getLastname());
				preparedStatement.setString(3, candidateDataDTO.getBgv_form_data().getPrimary_mobile());
				preparedStatement.setString(4, candidateDataDTO.getClient_id());
				preparedStatement.setString(5, address.getAddress_type().equals(AppConstants.PERMANENT_ADDRESS_STATUS)?"Permanent Address":"Present Address");
				preparedStatement.setString(6, "");
				preparedStatement.setString(7, address.getAddress()+", "+address.getStreet()+", "+address.getLandmark());
				preparedStatement.setString(8, address.getCity());
				preparedStatement.setString(9, address.getState());
				preparedStatement.setString(10, address.getPincode());
				preparedStatement.setString(11, "");
				preparedStatement.addBatch();
			}
			preparedStatement.executeBatch();
		}
		catch(Exception e)
		{
			log.error("Error while inserting candidate address details in db",e);
			throw new AppException(AppException.ERR_IN_INSERTING_CANDIDATE_ADDRESS_DETAILS, "Error while inserting candidate address details in db",e);
		}
		finally
		{
			close(preparedStatement);
		}
	}
	
	@Override
	public void insertEducationDetails(CandidateDataDTO candidateDataDTO)
	{
		PreparedStatement preparedStatement=null;
		List<EducationDetailsDTO> eduList=null;
		try
		{
			eduList = candidateDataDTO.getEducationDetails();
			preparedStatement = connection.prepareStatement(ADD_CANDIDATE_EDUCATION_DETAILS);
			for(EducationDetailsDTO edu : eduList)
			{
				preparedStatement.setString(1, candidateDataDTO.getUnique_id());
				preparedStatement.setString(2, candidateDataDTO.getBgv_form_data().getFirstname()+" "+candidateDataDTO.getBgv_form_data().getLastname());
				preparedStatement.setString(3, candidateDataDTO.getBgv_form_data().getPrimary_mobile());
				preparedStatement.setString(4, candidateDataDTO.getClient_id());
				preparedStatement.setString(5, edu.getEducation_category());	//need to change
				preparedStatement.setString(6, edu.getRegistration_number());
				preparedStatement.setString(7, edu.getCourse_type());	//need to change
				preparedStatement.setString(8, edu.getInstitution_name()+","+edu.getAddress_of_educational_institute());
				preparedStatement.setString(9, edu.getUniversity());
				preparedStatement.setString(10, edu.getTo_date());
				preparedStatement.setString(11, edu.getEducational_documents_proofs());
				preparedStatement.setTimestamp(12, new Timestamp(System.currentTimeMillis()));
				preparedStatement.addBatch();
			}
			preparedStatement.executeBatch();
		}
		catch(Exception e)
		{
			log.error("Error while inserting candidate education details in db",e);
			throw new AppException(AppException.ERR_IN_INSERTING_CANDIDATE_EDUCATION_DETAILS, "Error while inserting candidate education details in db",e);	
		}
		finally
		{
			close(preparedStatement);
		}
	}
	
	@Override
	public void insertWorkExperience(CandidateDataDTO candidateDataDTO)
	{
		PreparedStatement preparedStatement=null;
		List<PastWorkExperienceDTO> workList=null;
		try
		{
			workList = candidateDataDTO.getWorkExperience();
			preparedStatement = connection.prepareStatement(ADD_CANDIDATE_WORK_DETAILS);
			for(PastWorkExperienceDTO work : workList)
			{
				preparedStatement.setString(1, candidateDataDTO.getUnique_id());
				preparedStatement.setString(2, candidateDataDTO.getBgv_form_data().getFirstname()+" "+candidateDataDTO.getBgv_form_data().getLastname());
				preparedStatement.setString(3, candidateDataDTO.getBgv_form_data().getPrimary_mobile());
				preparedStatement.setString(4, candidateDataDTO.getClient_id());
				preparedStatement.setString(5, "");
				preparedStatement.setString(6, work.getEmployee_id());
				preparedStatement.setString(7, work.getCompany());
				preparedStatement.setString(8, work.getTitle());
				preparedStatement.setString(9, "");
				preparedStatement.setString(10, work.getFrom_date());
				preparedStatement.setString(11, work.getTo_date());
				preparedStatement.setString(12, work.getCtc());
				preparedStatement.setString(13, work.getName_of_reporting_manager());
				preparedStatement.setString(14, work.getReporting_manager_contact_number());
				preparedStatement.setString(15, work.getReason_for_leaving_job());
				preparedStatement.addBatch();
			}
			preparedStatement.executeBatch();
		}
		catch(Exception e)
		{
			log.error("Error while inserting candidate Work Experience details in db",e);
			throw new AppException(AppException.ERR_IN_INSERTING_CANDIDATE_WORK_EXPERIENCE_DETAILS, "Error while inserting candidate Work Experience details in db",e);	
		}
		finally
		{
			close(preparedStatement);
		}
	}
	
}
