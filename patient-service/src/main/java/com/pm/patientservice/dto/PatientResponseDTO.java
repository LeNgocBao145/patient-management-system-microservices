package com.pm.patientservice.dto;

import java.time.LocalDate;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PatientResponseDTO {
	private UUID id;
	private String name;
	private String email;
	private String address;
	private LocalDate dateOfBirth;
}
