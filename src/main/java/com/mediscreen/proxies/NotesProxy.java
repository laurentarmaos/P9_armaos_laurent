package com.mediscreen.proxies;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.mediscreen.beans.Note;

@FeignClient(name="microservice-notes", url="practitionnernotes:8082")
public interface NotesProxy {

	@GetMapping("/note/{noteId}")
	public Note findNote(@PathVariable String noteId);
	
	@GetMapping("/patient/{patientId}/findnotes")
	public List<Note> findNoteByPatientId(@PathVariable String patientId);
	
	@PostMapping("/patient/addnote")
	public Note addNote(@RequestParam("practitionnerNotes") String practitionnerNotes, @RequestParam("patientId") String patientId);
	
	@PutMapping("/note/update")
	public Note updateNote(@RequestBody Note note);
	
	@DeleteMapping("/note/{noteId}/delete")
	public void deleteNote(@PathVariable("noteId") String noteId);
	
}
