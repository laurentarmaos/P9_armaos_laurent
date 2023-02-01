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

		List<NoteBean> notes = noteService.findNoteByPatientId(patientId);
		//System.out.println(notes.get(0).getPractitionnerNotes());
		
		model.addAttribute("notes", notes);
		return "patientnote";
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
