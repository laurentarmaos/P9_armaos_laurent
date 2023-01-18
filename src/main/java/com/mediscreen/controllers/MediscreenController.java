package com.mediscreen.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
	  
//	  @RequestMapping("/patient/update/{id}")
//		public String updatePatient(@PathVariable("id") Long id, @RequestBody PatientBean patientBean, Model model) {
//		  PatientBean patient = patientProxy.updatePatient(id, patientBean);
//		  model.addAttribute("patient", patient);
//		  
//		  return "updatePatient";
//		  
//		}
	  
	  @RequestMapping("/patient/delete/{id}")
	  public String deletePatient(@PathVariable("id") Long id) {
		  patientProxy.deletePatient(id);
		  
		  return "redirect:/patients";
	  }
	  
	  
}
