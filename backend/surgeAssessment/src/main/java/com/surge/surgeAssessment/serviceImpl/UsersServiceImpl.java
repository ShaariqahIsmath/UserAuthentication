package com.surge.surgeAssessment.serviceImpl;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.surge.surgeAssessment.dao.User;
import com.surge.surgeAssessment.dao.UserType;
import com.surge.surgeAssessment.dto.UserDto;
import com.surge.surgeAssessment.exception.ErrorList;
import com.surge.surgeAssessment.exception.Exceptions;
import com.surge.surgeAssessment.exception.UserNotFoundException;
import com.surge.surgeAssessment.repository.UsersRepository;
import com.surge.surgeAssessment.service.UsersService;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

@Service
public class UsersServiceImpl implements UsersService, UserDetailsService{

	private final UsersRepository usersRepository;

	@Autowired
	private final EmailServiceImpl emailServiceImpl;

	@Autowired
	protected ObjectMapper mapper;


	private static final Logger LOGGER = LoggerFactory.getLogger(UsersServiceImpl.class);

	public UsersServiceImpl(UsersRepository usersRepository, EmailServiceImpl emailServiceImpl) {
		this.usersRepository = usersRepository;
		this.emailServiceImpl = emailServiceImpl;
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UserNotFoundException {
		Optional<User> user = usersRepository.findByEmail(email);
		Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
		if (!user.isPresent()) {
			throw new UserNotFoundException("User not found");
		} else if (user.get().getAccountType().equals(UserType.STUDENT.getUserValue())) {
			authorities.add(new SimpleGrantedAuthority(UserType.STUDENT.getUserValue()));
		} else {
			authorities.add(new SimpleGrantedAuthority(UserType.ADMIN.getUserValue()));
		}
		return new org.springframework.security.core.userdetails.User(user.get().getEmail(), user.get().getPassword(), authorities);
	}


//	@EventListener(ApplicationReadyEvent.class)
	@Override
	public void addUser(UserDto userDto) {

			User user = mapper.convertValue(userDto, User.class);
			user.setEmail(userDto.getEmail());

			String password = UUID.randomUUID().toString();
			user.setPassword(new BCryptPasswordEncoder().encode(password));

//			emailServiceImpl.sendSimpleMessage("shammarah.ismath@gmail.com", "Your password", "Thank you for signing up. Here's your password: password :/");


		user.setStatus(true);
	        
	        usersRepository.save(user);
	}


	@Override
	public Page<User> getAllUsers(int limit, int offset) throws Exceptions{
				
		 Pageable pageable = PageRequest
	                .of(offset, limit);
	        Page<User> users = usersRepository.findAll(pageable);
	        
			if (users.isEmpty()) {
				LOGGER.error("getAllUsersRequest : User is not found");
				throw new Exceptions(ErrorList.NO_RESULTS_FOUND, "Users not found");
			} 
	        return users;
	
	}

	@Override
	public User getUserById(String id) throws Exceptions {
		Optional<User> existingUser = usersRepository.findById(new ObjectId(id));
		if (!existingUser.isPresent()) {
			LOGGER.error("getByIdResponse : User is not found");
			throw new Exceptions(ErrorList.NO_RESULTS_FOUND, "User is not found");
		} else {				
			return existingUser.get();
		}
	}

	@Override
	public void updateUser(UserDto userDto, String id) throws Exceptions {
		Optional<User> existingUser = usersRepository.findById(new ObjectId(id));
		if(!existingUser.isPresent()) {
			LOGGER.error("updateResponse : User not found");
			throw new Exceptions(ErrorList.NO_RESULTS_FOUND, "User not found");
		}else {

			User updatedUser = existingUser.get();

			if(userDto.getFirstName() == null){
				updatedUser.setFirstName(updatedUser.getFirstName());

			}else if(userDto.getLastName() == null) {
				updatedUser.setLastName(updatedUser.getLastName());

			}else if(userDto.getEmail() == null){
				updatedUser.setEmail(updatedUser.getEmail());

			}else if(userDto.getPassword() == null){
				updatedUser.setPassword(new BCryptPasswordEncoder().encode(updatedUser.getPassword()));

			}else if(userDto.getDateOfBirth() == null){
				updatedUser.setDateOfBirth(updatedUser.getDateOfBirth());

			}else if(userDto.getMobileNumber() == null){
				updatedUser.setMobileNumber(updatedUser.getMobileNumber());

			}

			updatedUser.setFirstName(userDto.getFirstName());
			updatedUser.setLastName(userDto.getLastName());
			updatedUser.setEmail(userDto.getEmail());
			updatedUser.setDateOfBirth(userDto.getDateOfBirth());
			updatedUser.setMobileNumber(userDto.getMobileNumber());
			updatedUser.setPassword(new BCryptPasswordEncoder().encode(userDto.getPassword()));
			updatedUser.setStatus(false);

			usersRepository.save(updatedUser);
			
		}
	}

	

}
