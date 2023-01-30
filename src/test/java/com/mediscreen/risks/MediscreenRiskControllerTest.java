package com.mediscreen.risks;

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
import com.mediscreen.controllers.MediscreenRiskController;
import com.mediscreen.services.MediscreenRiskService;

@SpringBootTest
public class MediscreenRiskControllerTest {

	@MockBean
	private MediscreenRiskService mediscreenRiskService;
	
	private MediscreenRiskController mediscreenRiskController;
	
	private MockMvc mockMvc;
	
	@BeforeEach
	public void setUp() {
		mediscreenRiskController = new MediscreenRiskController(mediscreenRiskService);
		mockMvc = MockMvcBuilders.standaloneSetup(mediscreenRiskController).build();
	}
	
	
	@Test
	public void evaluateRiskTest() throws Exception {
		PatientBean patient = new PatientBean();
		patient.setPatientId((long) 1);
		
		when(mediscreenRiskService.evaluateRisk((long) 1)).thenReturn("risk");
		
		mockMvc.perform(MockMvcRequestBuilders.get(
				"/assess/{patientId}",
				patient.getPatientId()
			)).andExpect(status().isOk());
	}
}
