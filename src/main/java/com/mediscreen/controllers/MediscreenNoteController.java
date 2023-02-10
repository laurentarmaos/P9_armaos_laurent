package com.mediscreen.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.mediscreen.beans.NoteBean;
import com.mediscreen.beans.PatientBean;
import com.mediscreen.services.MediscreenNoteService;
import com.mediscreen.services.MediscreenPatientService;

@Controller
public class MediscreenNoteController {

	private final MediscreenNoteService noteService;
	private final MediscreenPatientService patientService;
	
	public MediscreenNoteController(MediscreenNoteService noteService, MediscreenPatientService patientService) {
		this.noteService = noteService;
		this.patientService = patientService;
	}
	
	
	@GetMapping("/patient/{patientId}/findnotes")
	public String getNote(@PathVariable("patientId") String patientId, Model model) {

		try {
			List<NoteBean> notes = noteService.findNoteByPatientId(patientId);
			//System.out.println(notes.get(0).getPractitionnerNotes());
			
			model.addAttribute("notes", notes);
			return "patientnote";
		} catch (Exception e) {
			return "redirect:/patients";
		}
		
		
	}
	
	@GetMapping("/patient/{patientId}/addnote")
	public String addNoteForm(@PathVariable("patientId") Long patientId, Model model) {
		
		PatientBean patientBean = patientService.findPatient(patientId);
			
		if(patientBean!=null) {
			model.addAttribute("patientBean", patientBean);
			model.addAttribute("noteBean", new NoteBean());
			return "addNote";
		}else {
			return "redirect:/patients";
		}
		
		
	}
	
	@PostMapping("/patient/{patientId}/validateAdd")
	public String addNote(@ModelAttribute("noteBean") NoteBean noteBean, @PathVariable("patientId") String patientId, Model model) {

		try {
			noteService.addNote(noteBean);
			return "redirect:/";
		} catch (Exception e) {
			Long id = Long.parseLong(patientId);
			PatientBean patientBean = new PatientBean(id);
			model.addAttribute("patientBean", patientBean);
			return "addNote";
		}
		
		
	}
	
}
