package com.mediscreen.notes;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.mediscreen.beans.Note;
import com.mediscreen.beans.PatientBean;
import com.mediscreen.controllers.MediscreenController;
import com.mediscreen.controllers.MediscreenNoteController;
import com.mediscreen.services.MediscreenNoteService;
import com.mediscreen.services.MediscreenPatientService;

@SpringBootTest
public class MediscreenNoteControllerTest {
	
	@MockBean
	private MediscreenNoteService mediscreenNoteService;
	
	@MockBean
	private MediscreenPatientService mediscreenPatientService;
	
	private MediscreenNoteController mediscreenNoteController;
	
	private MockMvc mockMvc;
	
	@BeforeEach
	public void setUp() {
		mediscreenNoteController = new MediscreenNoteController(mediscreenNoteService, mediscreenPatientService);
		mockMvc = MockMvcBuilders.standaloneSetup(mediscreenNoteController).build();
	}
	
	@Test
	public void addNoteFormTest() throws Exception {
		PatientBean patient = new PatientBean();
		patient.setPatientId((long) 1);
		
		Long wrongId = (long) 2;
		
		when(mediscreenPatientService.findPatient((long) 1)).thenReturn(patient);
		
		when(mediscreenPatientService.findPatient(wrongId)).thenReturn(null);
		
		
		mockMvc.perform(MockMvcRequestBuilders.get(
				"/patient/{patientId}/addnote",
				patient.getPatientId()
			)).andExpect(status().isOk());
		
		mockMvc.perform(MockMvcRequestBuilders.get(
				"/patient/{patientId}/addnote",
				wrongId
			)).andExpect(status().is3xxRedirection());
	}
	
	
	@Test
	public void addNoteTest() throws Exception {
		Note note = new Note();
		note.setPatientId("1");
		note.setPractitionnerNotes("note");
		
		
		when(mediscreenNoteService.addNote(note)).thenReturn(note);
		
		mockMvc.perform(MockMvcRequestBuilders.post(
			"/patient/{patientId}/validateAdd",
			note.getPractitionnerNotes(),
			note.getPatientId()
		)).andExpect(status().is3xxRedirection());
	}
	
	@Test
	public void getNoteTest() throws Exception {
		PatientBean patient = new PatientBean();
		patient.setPatientId((long) 1);
		
		Long wrongId = (long) 2;
		
		when(mediscreenPatientService.findPatient((long) 1)).thenReturn(patient);
		
		when(mediscreenPatientService.findPatient(wrongId)).thenReturn(null);
		
		mockMvc.perform(MockMvcRequestBuilders.get(
				"/patient/{patientId}/findnotes",
				patient.getPatientId()
			)).andExpect(status().isOk());
		
		mockMvc.perform(MockMvcRequestBuilders.get(
				"/patient/{patientId}/findnotes",
				wrongId
			)).andExpect(status().is3xxRedirection());
	}
	
	@Test
	public void updateNoteTest() throws Exception {
		Note note = new Note();
		note.setPatientId("1");
		note.setPractitionnerNotes("note");
		
		when(mediscreenNoteService.updateNote(note)).thenReturn(note);
		
		mockMvc.perform(MockMvcRequestBuilders.post(
				"/patient/{patientId}/note/{id}/validateupdate",
				note.getPatientId(),
				note
			)).andExpect(status().is3xxRedirection());
	}
	
	@Test
	public void deleteNoteTest() throws Exception {
		Note note = new Note();
		note.setId("1");
		
		
		when(mediscreenNoteService.findNote("1")).thenReturn(note);
		when(mediscreenNoteService.findNote("2")).thenReturn(null);
		
		mockMvc.perform(MockMvcRequestBuilders.get(
				"/note/{noteId}/delete",
				note.getId()
			)).andExpect(status().is3xxRedirection());
		
		mockMvc.perform(MockMvcRequestBuilders.get(
				"/note/{noteId}/delete",
				"2"
			)).andExpect(status().is3xxRedirection());
	}

}
