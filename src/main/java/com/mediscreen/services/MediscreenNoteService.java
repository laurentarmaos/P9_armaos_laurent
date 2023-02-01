package com.mediscreen.services;

import java.util.List;

import com.mediscreen.beans.NoteBean;

public interface MediscreenNoteService {

	public List<NoteBean> findNoteByPatientId(String patientId);
	
	public NoteBean addNote(NoteBean noteBean);
}
