package com.pm.patientservice.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pm.patientservice.models.Patient;

@Repository
public interface PatientRepository extends JpaRepository<Patient, UUID>{
	public boolean existsByEmail(String email);
	public boolean existsByEmailAndIdNot(String email, UUID id);
}
