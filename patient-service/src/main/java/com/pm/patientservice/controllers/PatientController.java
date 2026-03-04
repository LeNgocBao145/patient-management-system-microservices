package com.pm.patientservice.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pm.patientservice.dto.PatientRequestDTO;
import com.pm.patientservice.dto.PatientResponseDTO;
import com.pm.patientservice.services.PatientService;

import jakarta.validation.Valid;
import jakarta.validation.groups.Default;

@RestController
@RequestMapping("/patients")
public class PatientController {
	@Autowired
	private PatientService patientService;
	
	@GetMapping
	public ResponseEntity<List<PatientResponseDTO>> getPatients() {
		List<PatientResponseDTO> patients = patientService.getPatients();
		
		return ResponseEntity.ok().body(patients);
	}
	
	@PostMapping
	public ResponseEntity<PatientResponseDTO> createPatient(@Valid @RequestBody PatientRequestDTO patientRequestDTO){		
		PatientResponseDTO patientResponseDTO = patientService.createPatient(patientRequestDTO);
		return ResponseEntity.status(HttpStatus.CREATED).body(patientResponseDTO);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<PatientResponseDTO> updatePatient(@PathVariable("id") UUID id, 
			@RequestBody @Validated({Default.class}) PatientRequestDTO patientRequestDTO){
		PatientResponseDTO updatedPatient = patientService.updatePatient(id, patientRequestDTO);
		
		return ResponseEntity.ok().body(updatedPatient);
	}
}
