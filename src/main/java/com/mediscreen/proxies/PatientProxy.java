package com.mediscreen.proxies;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.mediscreen.beans.PatientBean;

@FeignClient(name="microservice-patients", url="localhost:8081")
public interface PatientProxy {
	
	@GetMapping("/patients")
	List<PatientBean> findAllPatients();
	
	@GetMapping("/patient/{id}")
	PatientBean findPatient(@PathVariable Long id);
	
	@PostMapping("/patient/add")
	public PatientBean addPatient(@RequestBody PatientBean patientBean);
	
	@PutMapping("/patient/update/{id}")
	public PatientBean updatePatient(@PathVariable("id") Long id, @RequestBody PatientBean patientBean);
	
	
	@DeleteMapping("/patient/delete/{id}")
	public void deletePatient(@PathVariable("id") Long id);
}
