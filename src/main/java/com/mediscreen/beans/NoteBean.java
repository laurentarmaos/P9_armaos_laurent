package com.mediscreen.beans;

public class NoteBean {

	private String id;
	
	private String practitionnerNotes;

	private String patientId;
	
	public NoteBean() {}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPractitionnerNotes() {
		return practitionnerNotes;
	}

	public void setPractitionnerNotes(String practitionnerNotes) {
		this.practitionnerNotes = practitionnerNotes;
	}

	public String getPatientId() {
		return patientId;
	}

	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}
	
	
}
