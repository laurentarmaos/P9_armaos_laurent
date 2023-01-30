package com.mediscreen.notes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.mediscreen.beans.NoteBean;
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
		NoteBean note = new NoteBean();
		note.setPatientId("1");
		note.setPractitionnerNotes("note");
		
		when(notesProxy.addNote(note.getPractitionnerNotes(), note.getPatientId())).thenReturn(note);
		
		NoteBean savedNote = mediscreenNoteService.addNote(note);
		
		assertNotNull(savedNote);
		assertEquals(note.getPatientId(), savedNote.getPatientId());
		assertEquals(note.getPractitionnerNotes(), savedNote.getPractitionnerNotes());
	}

}
