package com.surge.surgeAssessment.service;

import com.surge.surgeAssessment.dao.Notes;
import com.surge.surgeAssessment.dto.NotesDto;
import com.surge.surgeAssessment.exception.Exceptions;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface NotesService {
	
	 	public void addNote(NotesDto notesDto) throws Exceptions;

	    public List<Notes> getAllNotes() throws Exceptions;

	    public void updateNote(NotesDto noteDto, String id) throws Exceptions;

	    public void deleteNote(String id) throws Exceptions;

}
