package com.mediscreen.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.mediscreen.beans.Note;
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
		
		PatientBean patientBean = patientService.findPatient(Long.parseLong(patientId));
		
		if(patientBean != null) {
			List<Note> notes = noteService.findNoteByPatientId(patientId);
			
			model.addAttribute("notes", notes);
			model.addAttribute("patient", patientBean);
			return "patientnote";
		}else {
			return "redirect:/patients";
		}
	
	}
	
	@GetMapping("/patient/{patientId}/addnote")
	public String addNoteForm(@PathVariable("patientId") Long patientId, Model model) {
		
		PatientBean patientBean = patientService.findPatient(patientId);
			
		if(patientBean!=null) {
			model.addAttribute("patientBean", patientBean);
			model.addAttribute("noteBean", new Note());
			return "addNote";
		}else {
			return "redirect:/patients";
		}
			
	}
	
	@PostMapping("/patient/{patientId}/validateAdd")
	public String addNote(@ModelAttribute("noteBean") Note noteBean, @PathVariable("patientId") String patientId, Model model) {

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
	
	@GetMapping("/patient/{patientId}/note/{noteId}/update")
	public String updateNoteForm(@PathVariable("patientId") Long patientId, @PathVariable("noteId") String noteId,  Model model) {
		PatientBean patientBean = patientService.findPatient(patientId);
		Note note = noteService.findNote(noteId);
		
		if(patientBean!=null && note!=null) {
			model.addAttribute("patientBean", patientBean);
			model.addAttribute("note", note);
			return "updateNote";
		}else {
			return "redirect:/patients";
		}
	}
	
	
	@PostMapping("/patient/{patientId}/note/{id}/validateupdate")
	public String updateNote(@ModelAttribute("noteBean") Note note, @PathVariable("patientId") String patientId, @PathVariable("id") String id, Model model) {
		try {
			noteService.updateNote(note);
			return "redirect:/";
		} catch (Exception e) {
			return "redirect:/patients";
		}
	}
	
	@GetMapping("/note/{noteId}/delete")
	public String deleteNote(@PathVariable("noteId") String noteId) {
		Note note = noteService.findNote(noteId);
		
		if(note!=null) {
			noteService.deleteNote(noteId);
			return "redirect:/patients";
		}else {
			return "redirect:/patients";
		}
	}
}
