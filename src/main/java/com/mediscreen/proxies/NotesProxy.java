package com.mediscreen.proxies;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mediscreen.beans.NoteBean;

@FeignClient(name="microservice-notes", url="localhost:8082")
public interface NotesProxy {
	
	
	@GetMapping("/patient/{patientId}/findnotes")
	public List<NoteBean> findNoteByPatientId(@PathVariable String patientId);
	
	@PostMapping("/patient/addnote")
	public NoteBean addNote(@RequestParam("practitionnerNotes") String practitionnerNotes, @RequestParam("patientId") String patientId);
	
}
