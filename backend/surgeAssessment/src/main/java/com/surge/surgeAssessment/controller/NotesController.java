package com.surge.surgeAssessment.controller;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.surge.surgeAssessment.dao.AuthenticationResponse;
import com.surge.surgeAssessment.dao.Notes;
import com.surge.surgeAssessment.dto.NotesDto;
import com.surge.surgeAssessment.exception.Exceptions;
import com.surge.surgeAssessment.service.NotesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/notes")
public class NotesController {

	@Autowired
	private NotesService notesService;
	
	@Autowired
	protected ObjectMapper mapper;

	protected AuthenticationResponse setResponse(Boolean success, HttpStatus status, String message, Object data) {
		AuthenticationResponse response = new AuthenticationResponse();
		response.setSuccess(success);
		response.setStatusCode(status.value());
		response.setResponse(message);
		response.setData(data);
		return response;
		
	}
	
	protected AuthenticationResponse setResponse(Boolean success, HttpStatus status, Object data) {
		AuthenticationResponse response = new AuthenticationResponse();
		response.setSuccess(success);
		response.setStatusCode(status.value());
		response.setData(data);
		return response;
		
		
	}
	
	
	@PostMapping()
	public ResponseEntity<AuthenticationResponse> addNote (@RequestBody NotesDto noteDto) throws Exceptions {
		

		notesService.addNote(noteDto);
		
		String message = String.format("Added note!");
		AuthenticationResponse response  = setResponse(Boolean.TRUE, HttpStatus.OK ,message, noteDto);
		return ResponseEntity.ok().body(response);
	}


    @GetMapping()
    public ResponseEntity<AuthenticationResponse> getAllNotes() throws Exceptions{
    	
    	List<Notes> notes = notesService.getAllNotes();
    	List<NotesDto> noteList = new ArrayList<>();
    	
    	for(Notes note : notes) {
    		NotesDto noteDto = mapper.convertValue(note, NotesDto.class);
    		noteList.add(noteDto);
    	}
    	
    	AuthenticationResponse response  = setResponse(Boolean.TRUE, HttpStatus.OK, noteList);
		return ResponseEntity.ok().body(response);
    	
    }


    @PutMapping("/{id}")
    public ResponseEntity<AuthenticationResponse> updateNote (@RequestBody NotesDto noteDto, @PathVariable (required = true) String id ) throws Exceptions{
    	
//    	Notes note = mapper.convertValue(noteDto, Notes.class);
    	notesService.updateNote(noteDto, id);
    	
    	String message = String.format("Successfully updated user Details!");
		
		AuthenticationResponse response  = setResponse(Boolean.TRUE, HttpStatus.OK, message , noteDto);
		return ResponseEntity.ok().body(response);
    }

    @DeleteMapping("/{id}")
    public void deleteNoteById(@PathVariable String id) throws Exceptions {
        notesService.deleteNote(id);
    }

}
