package com.mediscreen.notes;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.mediscreen.beans.NoteBean;
import com.mediscreen.beans.PatientBean;
import com.mediscreen.controllers.MediscreenNoteController;
import com.mediscreen.services.MediscreenNoteService;

@SpringBootTest
public class MediscreenNoteControllerTest {
	
	@MockBean
	private MediscreenNoteService mediscreenNoteService;
	
	private MediscreenNoteController mediscreenNoteController;
	
	private MockMvc mockMvc;
	
	@BeforeEach
	public void setUp() {
		mediscreenNoteController = new MediscreenNoteController(mediscreenNoteService);
		mockMvc = MockMvcBuilders.standaloneSetup(mediscreenNoteController).build();
	}
	
	@Test
	public void addNoteFormTest() throws Exception {
		PatientBean patient = new PatientBean();
		patient.setPatientId((long) 1);
		
		mockMvc.perform(MockMvcRequestBuilders.get(
				"/patient/{patientId}/addnote",
				patient.getPatientId()
			)).andExpect(status().isOk());
	}
	
	
	@Test
	public void addNoteTest() throws Exception {
		NoteBean note = new NoteBean();
		note.setPatientId("1");
		note.setPractitionnerNotes("note");
		
		when(mediscreenNoteService.addNote(note)).thenReturn(note);
		
		mockMvc.perform(MockMvcRequestBuilders.post(
			"/patient/{patientId}/validateAdd",
			note.getPractitionnerNotes(),
			note.getPatientId()
		)).andExpect(status().is3xxRedirection());
	}

}
