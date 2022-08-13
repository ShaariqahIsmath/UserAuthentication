package com.surge.surgeAssessment.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.surge.surgeAssessment.dao.AuthenticationResponse;
import com.surge.surgeAssessment.dao.User;
import com.surge.surgeAssessment.dto.UserDto;
import com.surge.surgeAssessment.exception.Exceptions;
import com.surge.surgeAssessment.service.UsersService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value= "/api/users")
public class UsersController {

	@Autowired
	private UsersService usersService;
	
	@Autowired
	protected ObjectMapper mapper;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UsersController.class);


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
	public ResponseEntity<AuthenticationResponse> addUser(@RequestBody UserDto userDto) throws Exceptions{
		
		usersService.addUser(userDto);
		String message = String.format("Added user");
		AuthenticationResponse response  = setResponse(Boolean.TRUE, HttpStatus.OK ,message, userDto);
		return ResponseEntity.ok().body(response);
	}
	
	

	@GetMapping()
	public ResponseEntity<AuthenticationResponse> getAllUsers(@RequestParam(defaultValue = "20") Integer limit,
          @RequestParam(defaultValue = "0") Integer offset) throws Exceptions{
		
		Page<User> users = usersService.getAllUsers(limit, offset);
		
		List<UserDto> usersList = new ArrayList<>();
		for(User user : users) {
			UserDto userDto = mapper.convertValue(user, UserDto.class);
			usersList.add(userDto);
		}
		
		AuthenticationResponse response  = setResponse(Boolean.TRUE, HttpStatus.OK, usersList);
		return ResponseEntity.ok().body(response);
		
        	  
          }
	
    @GetMapping("/{id}")
    public ResponseEntity<AuthenticationResponse> getUserById (@PathVariable String id) throws Exceptions{
    	
    	User user = usersService.getUserById(id);
    	UserDto userDto = mapper.convertValue(user, UserDto.class);
    	
    	AuthenticationResponse response  = setResponse(Boolean.TRUE, HttpStatus.OK, userDto);
		return ResponseEntity.ok().body(response);
    }


    @PutMapping("/{id}")
    public ResponseEntity<AuthenticationResponse> updateUser (@RequestBody UserDto userDto, @PathVariable (required = true) String id ) throws Exceptions {

//		User updatedUser = mapper.convertValue(userDto, User.class);
		usersService.updateUser(userDto, id);
		String message = String.format("Successfully updated user Details!");
		
		AuthenticationResponse response  = setResponse(Boolean.TRUE, HttpStatus.OK, message , userDto);
		return ResponseEntity.ok().body(response);

    }


}
