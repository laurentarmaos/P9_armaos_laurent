package com.mediscreen.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.mediscreen.beans.PatientBean;
import com.mediscreen.services.MediscreenPatientService;
import com.mediscreen.services.MediscreenRiskService;

@Controller
public class MediscreenRiskController {
	
	private final MediscreenRiskService riskService;
	
	
	public MediscreenRiskController(MediscreenRiskService riskService) {
		this.riskService = riskService;
	}
	
	
	
	@GetMapping("/assess/{patientId}")
	public String evaluateRisk(@PathVariable("patientId") Long patientId, Model model) {
		
		PatientBean patient = riskService.findPatient(patientId);
		if(patient!=null) {
			model.addAttribute("patient", patient);
			model.addAttribute("risk", riskService.evaluateRisk(patientId));
			model.addAttribute("agePatient", riskService.getAge(patientId));
			
			return "diabeteRisk";
		}else {
			return "redirect:/patients";
		}
		
	}

}
