package com.revature.dto;

import java.util.Objects;

// This DTO adds or updates a client
public class AddOrUpdateClientDTO {

	private String firstName;
	private String lastName;
	private String phoneNumber;
	private int age;
	
	public AddOrUpdateClientDTO() {
		
	}
	
	public AddOrUpdateClientDTO(String firstName, String lastName, String phoneNumber, int age) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
		this.age = age;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "AddOrUpdateClientDTO [firstName=" + firstName + ", lastName=" + lastName + ", phoneNumber="
				+ phoneNumber + ", age=" + age + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(age, firstName, lastName, phoneNumber);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AddOrUpdateClientDTO other = (AddOrUpdateClientDTO) obj;
		return age == other.age && Objects.equals(firstName, other.firstName)
				&& Objects.equals(lastName, other.lastName) && Objects.equals(phoneNumber, other.phoneNumber);
	}
	
}
