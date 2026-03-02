package com.pm.patientservice.dto;

import java.util.UUID;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PatientRequestDTO {
	@NotBlank(message = "Name is required")
	@Size(max = 100, message = "Name cannot exceed 100 characters")
	private String name;
	
	@NotBlank(message = "Email is required")
	@Email(message = "Email is not valid")
	private String email;
	
	@NotBlank(message = "Address is required")
	private String address;
	
	@NotBlank(message = "Date of birth is required")
	private String dateOfBirth;
	
	@NotBlank(message = "Registered date is required")
	private String registeredDate;
}
