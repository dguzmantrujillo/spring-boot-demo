package com.example.demo.web.dto.model;

import com.example.demo.validator.NotBlankFirstname;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@NotBlankFirstname
public class UserDto {

	@NotEmpty
	@NotBlank
	private String idType;

	@NotEmpty
	@NotBlank
	private String idNumber;

	private String firstName;

	private String lastName;

	private String name;

	/**
	 * 
	 */
	public UserDto() {
		super();
	}

	/**
	 * @param idType
	 * @param idNumber
	 * @param firstName
	 * @param lastName
	 * @param name
	 */
	public UserDto(String idType, String idNumber, String firstName, String lastName, String name) {
		super();
		this.idType = idType;
		this.idNumber = idNumber;
		this.firstName = firstName;
		this.lastName = lastName;
		this.name = name;
	}

	/**
	 * @return the idType
	 */
	public String getIdType() {
		return idType;
	}

	/**
	 * @param idType the idType to set
	 */
	public void setIdType(String idType) {
		this.idType = idType;
	}

	/**
	 * @return the idNumber
	 */
	public String getIdNumber() {
		return idNumber;
	}

	/**
	 * @param idNumber the idNumber to set
	 */
	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
}
