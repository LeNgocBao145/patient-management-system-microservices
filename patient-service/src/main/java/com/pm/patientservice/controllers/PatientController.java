package com.pm.patientservice.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pm.patientservice.dto.PatientRequestDTO;
import com.pm.patientservice.dto.PatientResponseDTO;
import com.pm.patientservice.exceptions.validators.CreatePatientValidationGroup;
import com.pm.patientservice.services.PatientService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.groups.Default;

@RestController
@RequestMapping("/patients")
@Tag(name = "Patient", description = "API for managing patients")
public class PatientController {
	@Autowired
	private PatientService patientService;
	
	@GetMapping
	@Operation(summary = "Get patients")
	public ResponseEntity<List<PatientResponseDTO>> getPatients() {
		List<PatientResponseDTO> patients = patientService.getPatients();
		
		return ResponseEntity.ok().body(patients);
	}
	
	@PostMapping
	@Operation(summary = "Create a new patient")
	public ResponseEntity<PatientResponseDTO> createPatient(@Validated({Default.class, CreatePatientValidationGroup.class}) @RequestBody PatientRequestDTO patientRequestDTO){		
		PatientResponseDTO patientResponseDTO = patientService.createPatient(patientRequestDTO);
		return ResponseEntity.status(HttpStatus.CREATED).body(patientResponseDTO);
	}
	
	@PutMapping("/{id}")
	@Operation(summary = "Update a patient")
	public ResponseEntity<PatientResponseDTO> updatePatient(@PathVariable("id") UUID id, 
			@RequestBody @Validated({Default.class}) PatientRequestDTO patientRequestDTO){
		PatientResponseDTO updatedPatient = patientService.updatePatient(id, patientRequestDTO);
		
		return ResponseEntity.ok().body(updatedPatient);
	}
	
	@DeleteMapping("/{id}")
	@Operation(summary = "Delete a patient")
	public ResponseEntity<Void> deletePatient(@PathVariable("id") UUID id){
		patientService.deletePatient(id);
		return ResponseEntity.noContent().build();
	}
}
