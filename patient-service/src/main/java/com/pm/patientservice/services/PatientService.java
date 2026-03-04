package com.pm.patientservice.services;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.pm.patientservice.dto.PatientRequestDTO;
import com.pm.patientservice.dto.PatientResponseDTO;
import com.pm.patientservice.exceptions.EmailAlreadyExistsException;
import com.pm.patientservice.exceptions.PatientNotFoundException;
import com.pm.patientservice.mapper.PatientMapper;
import com.pm.patientservice.models.Patient;
import com.pm.patientservice.repositories.PatientRepository;

@Service
public class PatientService {
	@Autowired
	private PatientRepository patientRepository;
	
	public List<PatientResponseDTO> getPatients(){
		List<Patient> patients = patientRepository.findAll();
				
		return patients.stream().map(PatientMapper::toDTO).toList();
	}
	
	public PatientResponseDTO createPatient(PatientRequestDTO patientRequestDTO) {
		if(patientRepository.existsByEmail(patientRequestDTO.getEmail())) {
			throw new EmailAlreadyExistsException("A patient with this email address already exists " + patientRequestDTO.getEmail());
		}
		
		Patient patient = patientRepository.save(PatientMapper.toModel(patientRequestDTO));
		
		return PatientMapper.toDTO(patient);
	}
	
	public PatientResponseDTO updatePatient(UUID id, PatientRequestDTO patientRequestDTO) {		
		Patient patient = patientRepository.findById(id).orElseThrow(
				() -> new PatientNotFoundException("Patient not found with " + id));
		
		if(patientRepository.existsByEmailAndIdNot(patientRequestDTO.getEmail(), id)) {
			throw new EmailAlreadyExistsException("A patient with this email address already exists " + patientRequestDTO.getEmail());
		}
		
		patient.setName(patientRequestDTO.getName());
		patient.setEmail(patientRequestDTO.getEmail());
		patient.setAddress(patientRequestDTO.getAddress());
		patient.setDateOfBirth(LocalDate.parse(patientRequestDTO.getDateOfBirth()));
		
		Patient updatedPatient = patientRepository.save(patient);
		
		return PatientMapper.toDTO(updatedPatient);
	}
}
