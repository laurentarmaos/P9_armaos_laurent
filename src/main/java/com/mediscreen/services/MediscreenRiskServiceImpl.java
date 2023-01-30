package com.mediscreen.services;


import java.time.LocalDate;
import java.time.Period;

import org.springframework.stereotype.Service;

import com.mediscreen.beans.PatientBean;
import com.mediscreen.proxies.RiskProxy;

@Service
public class MediscreenRiskServiceImpl implements MediscreenRiskService{
	
	private final RiskProxy riskProxy;
	
	public MediscreenRiskServiceImpl(RiskProxy riskProxy) {
		this.riskProxy = riskProxy;
	}

	@Override
	public String evaluateRisk(Long patientId) {
		
		return riskProxy.evaluateRisk(patientId);
	}
	
	
	@Override
	public int getAge(Long patientId) {
		PatientBean patientBean = riskProxy.findPatient(patientId);
		LocalDate dateNow = LocalDate.now();
		int age = Period.between(patientBean.getBirthDate(), dateNow).getYears();
		
		return age;
	}
	
	@Override
	public PatientBean findPatient(Long patientId) {
		
		return riskProxy.findPatient(patientId);
	}
	
}
