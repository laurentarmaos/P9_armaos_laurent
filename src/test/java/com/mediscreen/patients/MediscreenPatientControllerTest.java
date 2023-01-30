package com.mediscreen.patients;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.mediscreen.beans.PatientBean;
import com.mediscreen.controllers.MediscreenController;
import com.mediscreen.services.MediscreenPatientService;

@SpringBootTest
public class MediscreenPatientControllerTest {

	@MockBean
	private MediscreenPatientService mediscreenPatientService;
	
	private MediscreenController mediscreenController;
	
	private MockMvc mockMvc;
	
	@BeforeEach
	public void setUp() {
		mediscreenController = new MediscreenController(mediscreenPatientService);
		mockMvc = MockMvcBuilders.standaloneSetup(mediscreenController).build();
	}
	
	@Test
	public void accueilTest() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get(
				"/"
			)).andExpect(status().isOk());
	}
	
	@Test
	public void findPatientTest() throws Exception {
		PatientBean patient = new PatientBean();
		patient.setPatientId((long) 1);
		
		when(mediscreenPatientService.findPatient((long) 1)).thenReturn(patient);
		
		mockMvc.perform(MockMvcRequestBuilders.get(
				"/patient/{id}",
				patient.getPatientId()
			)).andExpect(status().isOk());
	}
	
	@Test
	public void addPatientFormTest() throws Exception {
		
		mockMvc.perform(MockMvcRequestBuilders.get(
				"/patient/add"
			)).andExpect(status().isOk());
	}
	
	@Test
	public void addPatientTest() throws Exception {
		PatientBean patient = new PatientBean();
		patient.setFirstName("fname");
		
		when(mediscreenPatientService.addPatient(patient)).thenReturn(patient);
		
		mockMvc.perform(MockMvcRequestBuilders.post(
				"/patient/validateAdd",
				patient
			)).andExpect(status().is3xxRedirection());
	}
	
	@Test
	public void updatePatientFormTest() throws Exception {
		PatientBean patient = new PatientBean();
		patient.setPatientId((long) 1);
		
		mockMvc.perform(MockMvcRequestBuilders.get(
				"/patient/{patientId}/update",
				patient.getPatientId()
			)).andExpect(status().isOk());
	}
	
	@Test
	public void updatePatientTest() throws Exception {
		PatientBean patient = new PatientBean();
		patient.setPatientId((long) 1);
		
		when(mediscreenPatientService.updatePatient(patient)).thenReturn(patient);
		
		mockMvc.perform(MockMvcRequestBuilders.post(
				"/patient/{patientId}/validateUpdate",
				patient.getPatientId(),
				patient
			)).andExpect(status().is3xxRedirection());
	}
	
	@Test
	public void deletePatientTest() throws Exception {
		PatientBean patient = new PatientBean();
		patient.setPatientId((long) 1);
		
		doNothing().when(mediscreenPatientService).deletePatient((long) 1);
		
		mockMvc.perform(MockMvcRequestBuilders.get(
				"/patient/{patientId}/delete",
				patient.getPatientId()
			)).andExpect(status().is3xxRedirection());
	}
}
