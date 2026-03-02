package com.pm.patientservice.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pm.patientservice.dto.PatientResponseDTO;
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
}
