package org.ada.school.service;

import org.ada.school.dto.UserDto;
import org.ada.school.model.IUser;

import java.util.List;

public interface UserService {
    IUser create(UserDto userDto);

    IUser findById(String id);

    List<IUser> all();

    boolean deleteById(String id);

    IUser update(UserDto userDto, String id);
}
