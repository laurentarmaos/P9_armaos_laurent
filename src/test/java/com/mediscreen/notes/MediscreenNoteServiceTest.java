package com.mediscreen.notes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.mediscreen.beans.Note;
import com.mediscreen.proxies.NotesProxy;
import com.mediscreen.services.MediscreenNoteService;
import com.mediscreen.services.MediscreenNoteServiceImpl;

@SpringBootTest
public class MediscreenNoteServiceTest {
	
	@MockBean
	private NotesProxy notesProxy;
	
	private MediscreenNoteService mediscreenNoteService;
	
	@BeforeEach
	public void seTup() {
		mediscreenNoteService = new MediscreenNoteServiceImpl(notesProxy);
	}
	
	
	@Test
	public void addNoteTest() {
		Note note = new Note();
		note.setPatientId("1");
		note.setPractitionnerNotes("note");
		
		when(notesProxy.addNote(note.getPractitionnerNotes(), note.getPatientId())).thenReturn(note);
		
		Note savedNote = mediscreenNoteService.addNote(note);
		
		assertNotNull(savedNote);
		assertEquals(note.getPatientId(), savedNote.getPatientId());
		assertEquals(note.getPractitionnerNotes(), savedNote.getPractitionnerNotes());
	}
	
	@Test
	public void findNoteByPatientIdTest() {
		Note note1 = new Note();
		note1.setPatientId("1");
		
		Note note2 = new Note();
		note2.setPatientId("1");
		
		List<Note> allNotes = new ArrayList<>();	
		allNotes.add(note1);
		allNotes.add(note2);
		
		when(notesProxy.findNoteByPatientId(Mockito.anyString())).thenReturn(allNotes);
		
		List<Note> foundNotes = mediscreenNoteService.findNoteByPatientId("1");
		
		assertEquals(allNotes, foundNotes);
	}
	
	@Test
	public void findNoteTest() {
		Note note = new Note();
		note.setPatientId("1");
		
		when(notesProxy.findNote(Mockito.anyString())).thenReturn(note);
		
		Note foundNote = mediscreenNoteService.findNote("1");
		
		assertEquals(note, foundNote);
	}
	
	@Test
	public void updateNoteTest() {
		Note note = new Note();
		note.setPatientId("1");
		note.setPractitionnerNotes("note");
		
		when(notesProxy.updateNote(Mockito.any(Note.class))).thenReturn(note);
		
		Note updatedNote = mediscreenNoteService.updateNote(note);
		
		assertEquals(note, updatedNote);
	}
	

}
