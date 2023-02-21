package com.mediscreen.proxies;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.mediscreen.beans.PatientBean;


@FeignClient(name="microservice-risks", url="risks:8083")
public interface RiskProxy {

	
	@GetMapping("/assess/{patientId}")
	public String evaluateRisk(@PathVariable Long patientId);
	
	
	@GetMapping("/patient/{id}")
	public PatientBean findPatient(@PathVariable Long id);
}
