/*
*
*N Sunil 
*
*/

package com.fsuite.BgvApi.inter.dto;

public class EducationDetailsDTO
{
	
	private Integer id;
	
	private Integer case_id;
	
	private String education_category;
	
    private String level_of_study;
    
    private String field_of_study;
    
    private String course_type;
    
    private String gpa_percentage;
    
    private String max_gpa_percentage;
    
    private int iam_currently_student;
    
    private String institution_name;
    
    private String university;
    
    private String notes;
    
    private String activities;
    
    private int high_edu_qualification;
    
    private String educational_documents_proofs;
    
    private String other_level_of_study;
    
    private String other_field_of_study;
    
    private String other_education_category;
    
    private String registration_number;
    
    private String address_of_educational_institute;
    
    private String _10th_certificate_pl;
    
    private String _12th_certificatepl;
    
    private String from_date;
    
    private String to_date;
    
    

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

	public String getEducation_category()
	{
		return education_category;
	}

	public void setEducation_category(String education_category)
	{
		this.education_category = education_category;
	}

	public String getLevel_of_study()
	{
		return level_of_study;
	}

	public void setLevel_of_study(String level_of_study)
	{
		this.level_of_study = level_of_study;
	}

	public String getField_of_study()
	{
		return field_of_study;
	}

	public void setField_of_study(String field_of_study)
	{
		this.field_of_study = field_of_study;
	}

	public String getCourse_type()
	{
		return course_type;
	}

	public void setCourse_type(String course_type)
	{
		this.course_type = course_type;
	}

	public String getGpa_percentage()
	{
		return gpa_percentage;
	}

	public void setGpa_percentage(String gpa_percentage)
	{
		this.gpa_percentage = gpa_percentage;
	}

	public String getMax_gpa_percentage()
	{
		return max_gpa_percentage;
	}

	public void setMax_gpa_percentage(String max_gpa_percentage)
	{
		this.max_gpa_percentage = max_gpa_percentage;
	}

	public int getIam_currently_student()
	{
		return iam_currently_student;
	}

	public void setIam_currently_student(int iam_currently_student)
	{
		this.iam_currently_student = iam_currently_student;
	}

	public String getInstitution_name()
	{
		return institution_name;
	}

	public void setInstitution_name(String institution_name)
	{
		this.institution_name = institution_name;
	}

	public String getUniversity()
	{
		return university;
	}

	public void setUniversity(String university)
	{
		this.university = university;
	}

	public String getNotes()
	{
		return notes;
	}

	public void setNotes(String notes)
	{
		this.notes = notes;
	}

	public String getActivities()
	{
		return activities;
	}

	public void setActivities(String activities)
	{
		this.activities = activities;
	}

	public int getHigh_edu_qualification()
	{
		return high_edu_qualification;
	}

	public void setHigh_edu_qualification(int high_edu_qualification)
	{
		this.high_edu_qualification = high_edu_qualification;
	}

	public String getEducational_documents_proofs()
	{
		return educational_documents_proofs;
	}

	public void setEducational_documents_proofs(String educational_documents_proofs)
	{
		this.educational_documents_proofs = educational_documents_proofs;
	}

	public String getOther_level_of_study()
	{
		return other_level_of_study;
	}

	public void setOther_level_of_study(String other_level_of_study)
	{
		this.other_level_of_study = other_level_of_study;
	}

	public String getOther_field_of_study()
	{
		return other_field_of_study;
	}

	public void setOther_field_of_study(String other_field_of_study)
	{
		this.other_field_of_study = other_field_of_study;
	}

	public String getOther_education_category()
	{
		return other_education_category;
	}

	public void setOther_education_category(String other_education_category)
	{
		this.other_education_category = other_education_category;
	}

	public String getRegistration_number()
	{
		return registration_number;
	}

	public void setRegistration_number(String registration_number)
	{
		this.registration_number = registration_number;
	}

	public String getAddress_of_educational_institute()
	{
		return address_of_educational_institute;
	}

	public void setAddress_of_educational_institute(
			String address_of_educational_institute)
	{
		this.address_of_educational_institute = address_of_educational_institute;
	}

	public String get_10th_certificate_pl()
	{
		return _10th_certificate_pl;
	}

	public void set_10th_certificate_pl(String _10th_certificate_pl)
	{
		this._10th_certificate_pl = _10th_certificate_pl;
	}

	public String get_12th_certificatepl()
	{
		return _12th_certificatepl;
	}

	public void set_12th_certificatepl(String _12th_certificatepl)
	{
		this._12th_certificatepl = _12th_certificatepl;
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

	@Override
	public String toString()
	{
		return "EducationDetailsDTO [id=" + id + ", case_id=" + case_id
				+ ", education_category=" + education_category
				+ ", level_of_study=" + level_of_study + ", field_of_study="
				+ field_of_study + ", course_type=" + course_type
				+ ", gpa_percentage=" + gpa_percentage + ", max_gpa_percentage="
				+ max_gpa_percentage + ", iam_currently_student="
				+ iam_currently_student + ", institution_name="
				+ institution_name + ", university=" + university + ", notes="
				+ notes + ", activities=" + activities
				+ ", high_edu_qualification=" + high_edu_qualification
				+ ", educational_documents_proofs="
				+ educational_documents_proofs + ", other_level_of_study="
				+ other_level_of_study + ", other_field_of_study="
				+ other_field_of_study + ", other_education_category="
				+ other_education_category + ", registration_number="
				+ registration_number + ", address_of_educational_institute="
				+ address_of_educational_institute + ", _10th_certificate_pl="
				+ _10th_certificate_pl + ", _12th_certificatepl="
				+ _12th_certificatepl + ", from_date=" + from_date
				+ ", to_date=" + to_date + "]";
	}

	
    
    

}
