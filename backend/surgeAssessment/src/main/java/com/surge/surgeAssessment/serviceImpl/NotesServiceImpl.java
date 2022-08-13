package com.surge.surgeAssessment.serviceImpl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.surge.surgeAssessment.dao.Notes;
import com.surge.surgeAssessment.dto.NotesDto;
import com.surge.surgeAssessment.exception.ErrorList;
import com.surge.surgeAssessment.exception.Exceptions;
import com.surge.surgeAssessment.repository.NotesRepository;
import com.surge.surgeAssessment.service.NotesService;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NotesServiceImpl implements NotesService {

	private NotesRepository notesRepository;

	@Autowired
	protected ObjectMapper mapper;

	private static final Logger LOGGER = LoggerFactory.getLogger(NotesServiceImpl.class);

	public NotesServiceImpl(NotesRepository notesRepository) {
		this.notesRepository = notesRepository;
	}

	@Override
	public void addNote(NotesDto notesDto) throws Exceptions {
		
		  Notes newNote = mapper.convertValue(notesDto, Notes.class);
		  newNote.setHeading(notesDto.getHeading());
		  newNote.setDescription(notesDto.getDescription());
		  
		  notesRepository.save(newNote);
	
	}

	@Override
	public List<Notes> getAllNotes() throws Exceptions {
		
		List<Notes> notess = notesRepository.findAll();
		if(notess.isEmpty()) {
			throw new Exceptions(ErrorList.NO_RESULTS_FOUND, "No Note not found");

		}
		 return notess;
	}

	@Override
	public void updateNote(NotesDto notesDto, String id) throws Exceptions {
		
		Optional<Notes> existingNote = notesRepository.findById(new ObjectId(id));
		if(!existingNote.isPresent()) {
			throw new Exceptions(ErrorList.NO_RESULTS_FOUND, "Note not found");
		}else {
			
			Notes updatedNote = existingNote.get();
			if(notesDto.getHeading() == null) {
				updatedNote.setHeading(updatedNote.getHeading());
			}else if(notesDto.getDescription() == null) {
				updatedNote.setDescription(updatedNote.getDescription());
			}

			updatedNote.setHeading(notesDto.getHeading());
			updatedNote.setDescription(notesDto.getDescription());
			
			notesRepository.save(updatedNote);
			
		}
	}

	@Override
	public void deleteNote(String id) throws Exceptions {
		Optional<Notes> existingNote = notesRepository.findById(new ObjectId(id));
		if(!existingNote.isPresent()) {
			throw new Exceptions(ErrorList.NO_RESULTS_FOUND, "Note not found");
		}else {
			notesRepository.deleteById(new ObjectId(id));
		}
		
	}
	
	

}
