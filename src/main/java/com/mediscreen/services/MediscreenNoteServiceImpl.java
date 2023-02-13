package com.mediscreen.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mediscreen.beans.Note;
import com.mediscreen.proxies.NotesProxy;

@Service
public class MediscreenNoteServiceImpl implements MediscreenNoteService {
	
	private final NotesProxy noteProxy;
	
	public MediscreenNoteServiceImpl(NotesProxy noteProxy) {
		this.noteProxy = noteProxy;
	}

	
	@Override
	public List<Note> findNoteByPatientId(String patientId) {
		
		return noteProxy.findNoteByPatientId(patientId);
	}
	
	@Override
	public Note addNote(Note note) {	
		return noteProxy.addNote(note.getPractitionnerNotes(), note.getPatientId());
	}


	@Override
	public Note updateNote(Note note) {

		return noteProxy.updateNote(note);
	}


	@Override
	public void deleteNote(String noteId) {
		noteProxy.deleteNote(noteId);
		
	}


	@Override
	public Note findNote(String noteId) {
		
		return noteProxy.findNote(noteId);
	}

	
	

}
