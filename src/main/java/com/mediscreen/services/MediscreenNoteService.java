package com.mediscreen.services;

import java.util.List;

import com.mediscreen.beans.Note;

public interface MediscreenNoteService {

	public List<Note> findNoteByPatientId(String patientId);
	
	public Note addNote(Note noteBean);
	
	public Note updateNote(Note note);
	
	public void deleteNote(String noteId);
	
	public Note findNote(String noteId);
}
