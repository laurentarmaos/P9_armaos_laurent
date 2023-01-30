package com.mediscreen.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.mediscreen.beans.NoteBean;
import com.mediscreen.beans.PatientBean;
import com.mediscreen.services.MediscreenNoteService;

@Controller
public class MediscreenNoteController {

	private final MediscreenNoteService noteService;
	
	public MediscreenNoteController(MediscreenNoteService noteService) {
		this.noteService = noteService;
	}
	
	@GetMapping("/patient/{patientId}/addnote")
	public String addNoteForm(@PathVariable("patientId") Long patientId, Model model) {
		
		PatientBean patientBean = new PatientBean(patientId);
		model.addAttribute("patientBean", patientBean);
		model.addAttribute("noteBean", new NoteBean());
		
		return "addNote";
	}
	
	@PostMapping("/patient/{patientId}/validateAdd")
	public String addNote(@ModelAttribute("noteBean") NoteBean noteBean, @PathVariable("patientId") String patientId) {

		noteService.addNote(noteBean);
		return "redirect:/";
	}
	
}
