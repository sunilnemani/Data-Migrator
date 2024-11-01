/*
*
*N Sunil 
*
*/

package com.fsuite.BgvApi.inter.dto;

public class AddressDetailsDTO
{
	private Integer id;
	
	private Integer case_id;
	
	private String country;
	
    private String state;
    
    private String city;
    
    private String street;
    
    private String address;
    
    private String landmark;
    
    private String pincode;
    
    private String address_proof;
    
    private String address_period_of_stay_from;
    
    private String address_period_of_stay_to;
    
    private String address_type;

    
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

	public String getCountry()
	{
		return country;
	}

	public void setCountry(String country)
	{
		this.country = country;
	}

	public String getState()
	{
		return state;
	}

	public void setState(String state)
	{
		this.state = state;
	}

	public String getCity()
	{
		return city;
	}

	public void setCity(String city)
	{
		this.city = city;
	}

	public String getStreet()
	{
		return street;
	}

	public void setStreet(String street)
	{
		this.street = street;
	}

	public String getAddress()
	{
		return address;
	}

	public void setAddress(String address)
	{
		this.address = address;
	}

	public String getLandmark()
	{
		return landmark;
	}

	public void setLandmark(String landmark)
	{
		this.landmark = landmark;
	}

	public String getPincode()
	{
		return pincode;
	}

	public void setPincode(String pincode)
	{
		this.pincode = pincode;
	}

	public String getAddress_proof()
	{
		return address_proof;
	}

	public void setAddress_proof(String address_proof)
	{
		this.address_proof = address_proof;
	}

	public String getAddress_period_of_stay_from()
	{
		return address_period_of_stay_from;
	}

	public void setAddress_period_of_stay_from(String address_period_of_stay_from)
	{
		this.address_period_of_stay_from = address_period_of_stay_from;
	}

	public String getAddress_period_of_stay_to()
	{
		return address_period_of_stay_to;
	}

	public void setAddress_period_of_stay_to(String address_period_of_stay_to)
	{
		this.address_period_of_stay_to = address_period_of_stay_to;
	}

	public String getAddress_type()
	{
		return address_type;
	}

	public void setAddress_type(String address_type)
	{
		this.address_type = address_type;
	}

	@Override
	public String toString()
	{
		return "AddressDetailsDTO [id=" + id + ", case_id=" + case_id
				+ ", country=" + country + ", state=" + state + ", city=" + city
				+ ", street=" + street + ", address=" + address + ", landmark="
				+ landmark + ", pincode=" + pincode + ", address_proof="
				+ address_proof + ", address_period_of_stay_from="
				+ address_period_of_stay_from + ", address_period_of_stay_to="
				+ address_period_of_stay_to + ", address_type=" + address_type
				+ "]";
	}

	
}
