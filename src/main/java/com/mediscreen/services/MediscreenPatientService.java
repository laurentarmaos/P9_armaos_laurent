package com.mediscreen.services;

import java.util.List;

import com.mediscreen.beans.PatientBean;

public interface MediscreenPatientService {

	public List<PatientBean> findAllPatients();
	
	public PatientBean findPatient(Long id);
	
	public PatientBean addPatient(PatientBean patientBean);
	
	public PatientBean updatePatient(PatientBean patientBean);
	
	public void deletePatient(Long id);
	
}
