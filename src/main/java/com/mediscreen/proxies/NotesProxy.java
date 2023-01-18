package com.mediscreen.proxies;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.mediscreen.beans.NoteBean;

@FeignClient(name="microservice-notes", url="localhost:8082")
public interface NotesProxy {

	@PostMapping("/patient/addnote/{id}")
	public NoteBean addNote(@PathVariable("id") String id, @RequestBody NoteBean noteBean);
}
