package com.mediscreen.risks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.mediscreen.beans.PatientBean;
import com.mediscreen.proxies.RiskProxy;
import com.mediscreen.services.MediscreenRiskService;
import com.mediscreen.services.MediscreenRiskServiceImpl;

@SpringBootTest
public class MediscreenRiskServiceTest {
	
	@MockBean
	private RiskProxy riskProxy;
	
	private MediscreenRiskService mediscreenRiskService;
	
	@BeforeEach
	public void setUp() {
		mediscreenRiskService = new MediscreenRiskServiceImpl(riskProxy);
	}
	
	
	@Test
	public void evaluateRiskTest() {
		PatientBean patient = new PatientBean();
		patient.setPatientId((long) 1);
		
		when(riskProxy.evaluateRisk((long) 1)).thenReturn("risk");
		
		String risk = mediscreenRiskService.evaluateRisk((long) 1);
		
		assertEquals(risk, "risk");
		verify(riskProxy).evaluateRisk(Mockito.anyLong());
	}
	
	@Test
	public void getAgeTest() {
		PatientBean patient = new PatientBean();
		patient.setPatientId((long) 1);
		patient.setBirthDate(LocalDate.now().minusYears(40));
		
		when(riskProxy.findPatient(Mockito.anyLong())).thenReturn(patient);
		
		int age = mediscreenRiskService.getAge((long) 1);
		
		assertEquals(age, 40);
		
	}
	
	@Test
	public void findPatientTest() {
		PatientBean patient = new PatientBean();
		patient.setFirstName("firstname");
		patient.setPatientId((long) 1);
		
		when(riskProxy.findPatient(Mockito.anyLong())).thenReturn(patient);
		
		PatientBean foundPatient = mediscreenRiskService.findPatient((long) 1);
		
		assertEquals(foundPatient, patient);
	}

}
