package com.mediscreen.services;

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
	public NoteBean addNote(NoteBean noteBean) {
		
		return noteProxy.addNote(noteBean);
	}

}
