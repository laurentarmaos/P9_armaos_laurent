package com.mediscreen.beans;

public class NoteBean {

	private String id;
	
	private String practitionner_notes;

	private String patient_id;
	
	public NoteBean() {}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPractitionner_notes() {
		return practitionner_notes;
	}

	public void setPractitionner_notes(String practitionner_notes) {
		this.practitionner_notes = practitionner_notes;
	}

	public String getPatient_id() {
		return patient_id;
	}

	public void setPatient_id(String patient_id) {
		this.patient_id = patient_id;
	}
	
	
}
