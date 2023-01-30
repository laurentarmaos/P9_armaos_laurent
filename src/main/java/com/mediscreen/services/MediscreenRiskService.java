package com.mediscreen.services;

import com.mediscreen.beans.PatientBean;

public interface MediscreenRiskService {

	public String evaluateRisk(Long patientId);
	
	public int getAge(Long patientId);
	
	public PatientBean findPatient(Long id);
}
