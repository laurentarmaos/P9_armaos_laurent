package com.mediscreen.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mediscreen.beans.PatientBean;
import com.mediscreen.proxies.PatientProxy;

@Service
public class MediscreenPatientServiceImpl implements MediscreenPatientService{
	
	private final PatientProxy patientProxy;
	
	public MediscreenPatientServiceImpl(PatientProxy patientProxy) {
		this.patientProxy = patientProxy;
	}

	@Override
	public List<PatientBean> findAllPatients() {

		return patientProxy.findAllPatients();
	}

	@Override
	public PatientBean findPatient(Long id) {
		
		return patientProxy.findPatient(id);
	}

	@Override
	public PatientBean addPatient(PatientBean patientBean) {
		
		return patientProxy.addPatient(patientBean);
	}

	@Override
	public PatientBean updatePatient(PatientBean patientBean) {

		return patientProxy.updatePatient(patientBean);
	}

	@Override
	public void deletePatient(Long patientId) {
		
		patientProxy.deletePatient(patientId);
		
	}
	

}
