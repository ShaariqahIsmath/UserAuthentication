package com.surge.surgeAssessment.service;


import com.surge.surgeAssessment.dao.User;
import com.surge.surgeAssessment.dto.UserDto;
import com.surge.surgeAssessment.exception.Exceptions;
import org.springframework.data.domain.Page;


public interface UsersService {


    void addUser(UserDto userDto) throws Exceptions;

    Page<User> getAllUsers(int limit, int offset) throws Exceptions;

    User getUserById(String id) throws Exceptions;

    void updateUser(UserDto userDto, String id) throws Exceptions;


}
