package org.ada.school.service;

import org.ada.school.dto.UserDto;
import org.ada.school.model.IUser;
import org.ada.school.model.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class UserServiceHashMap implements UserService {

    private final HashMap<String, User> usersMap = new HashMap<>();


    @Override
    public IUser create(UserDto userDto) {
        User user = new User(userDto);
        usersMap.put(user.getId(), user);
        return user;
    }

    @Override
    public IUser findById(String id) {
        if (usersMap.containsKey(id)) {
            return usersMap.get(id);
        }
        return null;
    }

    @Override
    public List<IUser> all() {
        return new ArrayList<>(usersMap.values());
    }

    @Override
    public boolean deleteById(String id) {
        return usersMap.remove(id) != null;
    }

    @Override
    public IUser update(UserDto userDto, String id) {
        if (usersMap.containsKey(id)) {
            User user = usersMap.get(id);
            user.update(userDto);
            return user;
        } else {
            return null;
        }
    }

    @Override
    public List<IUser> findUsersWithNameOrLastNameLike(String queryText) {
        return null;
    }

    @Override
    public List<IUser> findUsersCreatedAfter(Date startDate) {
        return null;
    }
}
