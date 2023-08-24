package com.praveentechie.producertoconsumer.service;

import com.praveentechie.producertoconsumer.dto.UserDto;

import java.util.List;

public interface UserService {
    List<UserDto> findAllUsers();

    UserDto findUserByEmail(String email);

    UserDto saveUser(UserDto userDto);

    UserDto findUserByName(String userName);
    //public boolean forgotPwd(String email);

    public boolean forgotPwd(String email) ;
}
