package com.mediscreen.patients;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.mediscreen.beans.PatientBean;
import com.mediscreen.proxies.PatientProxy;
import com.mediscreen.services.MediscreenPatientService;
import com.mediscreen.services.MediscreenPatientServiceImpl;

@SpringBootTest
public class MediscreenPatientServiceTest {
	
	@MockBean
	private PatientProxy patientProxy;
	
	private MediscreenPatientService mediscreenPatientService;
	
	@BeforeEach
	public void setUp() {
		mediscreenPatientService = new MediscreenPatientServiceImpl(patientProxy);
	}
	
	
	@Test
	public void findAllPatientsTest() {
		PatientBean patient1 = new PatientBean();
		patient1.setFirstName("fname1");
		patient1.setLastName("lname1");
		patient1.setGender("M");
		patient1.setAddress("address");
		patient1.setPhone("123456");
		
		PatientBean patient2 = new PatientBean();
		patient2.setFirstName("fname2");
		
		List<PatientBean> patients = new ArrayList<>();
		patients.add(patient1);
		patients.add(patient2);
		
		when(patientProxy.findAllPatients()).thenReturn(patients);
		
		List<PatientBean> foundPatients = mediscreenPatientService.findAllPatients();
		
		assertEquals(patients, foundPatients);
	}
	
	@Test
	public void addPatientTest() {
		PatientBean patient = new PatientBean();
		patient.setFirstName("fname");
		
		when(patientProxy.addPatient(Mockito.any(PatientBean.class))).thenReturn(patient);
		
		PatientBean savedPatient = mediscreenPatientService.addPatient(patient);
		
		assertEquals(savedPatient, patient);
	}
	
	@Test
	public void findPatientTest() {
		PatientBean patient = new PatientBean();
		patient.setPatientId((long) 1);
		
		when(patientProxy.findPatient(Mockito.anyLong())).thenReturn(patient);
		
		PatientBean foundPatient = mediscreenPatientService.findPatient((long) 1);
		
		assertEquals(foundPatient, patient);
	}
	
	@Test
	public void updatePatientTest() {
		PatientBean patient = new PatientBean();
		patient.setFirstName("fname");
		
		when(patientProxy.updatePatient(Mockito.any(PatientBean.class))).thenReturn(patient);
		
		PatientBean updatedPatient = mediscreenPatientService.updatePatient(patient);
		
		assertEquals(updatedPatient, patient);
	}
	

}
