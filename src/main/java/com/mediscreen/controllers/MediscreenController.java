package com.mediscreen.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mediscreen.beans.PatientBean;
import com.mediscreen.proxies.PatientProxy;

@Controller
public class MediscreenController {
	
	private final PatientProxy patientProxy;
	
	public MediscreenController(PatientProxy patientProxy) {
		this.patientProxy = patientProxy;
	}

	@GetMapping("/")
	public String accueil(Model model)
	{
	    return "accueil";
    }
	  
	@GetMapping("/patients")
	public String findAllPatients(Model model) {
		List<PatientBean> patients = patientProxy.findAllPatients();
		model.addAttribute("patients", patients);
		  
		return "patients";
	}
	  
	@GetMapping("/patient/{id}")
	public String findPatient(@PathVariable("id") Long id, Model model) {
		PatientBean patient = patientProxy.findPatient(id);
		model.addAttribute("patient", patient);
		  
		return "patient";
	}
	  
	@GetMapping("/patient/add")
	public String addPatientForm(Model model) {
	
		model.addAttribute("patient", new PatientBean());
		  
		return "addPatient";
	}
	  
	@PostMapping("/patient/validateAdd")
	public String addPatient(@ModelAttribute("patient") PatientBean patientBean, Model model) {
		patientProxy.addPatient(patientBean);
		model.addAttribute("patients", patientProxy.findAllPatients());
		  
		return "redirect:/patients";
	 }
	
	@GetMapping("/patient/{patientId}/update")
	public String updatePatientForm(@PathVariable("patientId") Long patientId, Model model) {
		
		model.addAttribute("patient", patientProxy.findPatient(patientId));
		
		return "updatePatient";
	}
	
	@PostMapping("/patient/{patientId}/validateUpdate")
	public String updatePatient(@PathVariable("patientId") Long patientId, @ModelAttribute("patient") PatientBean patientBean, Model model) {
		patientProxy.updatePatient(patientBean);
		
		model.addAttribute("patients", patientProxy.findAllPatients());
		
		return "redirect:/patients";
	}
	  
	  
	@GetMapping("/patient/{patientId}/delete")
	public String deletePatient(@PathVariable("patientId") Long patientId) {
		patientProxy.deletePatient(patientId);
		  
		return "redirect:/patients";
	}
	  
	  
}
