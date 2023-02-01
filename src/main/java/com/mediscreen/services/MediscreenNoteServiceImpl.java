package com.mediscreen.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mediscreen.beans.NoteBean;
import com.mediscreen.proxies.NotesProxy;

@Service
public class MediscreenNoteServiceImpl implements MediscreenNoteService {
	
	private final NotesProxy noteProxy;
	
	public MediscreenNoteServiceImpl(NotesProxy noteProxy) {
		this.noteProxy = noteProxy;
	}

	
	@Override
	public List<NoteBean> findNoteByPatientId(String patientId) {
		
		System.out.println("note : " + noteProxy.findNoteByPatientId(patientId).get(0).getPractitionnerNotes());
		return noteProxy.findNoteByPatientId(patientId);
	}
	
	@Override
	public NoteBean addNote(NoteBean noteBean) {	
		return noteProxy.addNote(noteBean.getPractitionnerNotes(), noteBean.getPatientId());
	}


}
