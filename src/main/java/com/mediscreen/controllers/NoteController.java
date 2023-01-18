package com.mediscreen.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.mediscreen.beans.NoteBean;
import com.mediscreen.beans.PatientBean;
import com.mediscreen.proxies.NotesProxy;
import com.mediscreen.proxies.PatientProxy;

@Controller
public class NoteController {
	
private final PatientProxy patientProxy;
private final NotesProxy noteProxy;
	
	public NoteController(PatientProxy patientProxy, NotesProxy noteProxy) {
		this.patientProxy = patientProxy;
		this.noteProxy = noteProxy;
	}
	
	@GetMapping("/patient/addnote/{id}")
	public String addNoteForm(@PathVariable("id") Long id, Model model) {
		
		PatientBean patientBean = patientProxy.findPatient(id);
		model.addAttribute("patientBean", patientBean);
		model.addAttribute("noteBean", new NoteBean());
		
		return "addNote";
	}
	
	@PostMapping("/patient/validateNote/{id}")
	public String addNote(@ModelAttribute("noteBean") NoteBean noteBean, @PathVariable("id") String id, Model model) {
		noteBean.setPatient_id(id);
		noteProxy.addNote(id, noteBean);
		
		return "redirect:/";
	}
	

}
