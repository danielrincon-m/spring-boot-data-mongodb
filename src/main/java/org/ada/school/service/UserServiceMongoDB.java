package org.ada.school.service;

import org.ada.school.dto.UserDto;
import org.ada.school.model.IUser;
import org.ada.school.repository.UserDocument;
import org.ada.school.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceMongoDB implements UserService {

    private final UserRepository userRepository;

    public UserServiceMongoDB(@Autowired UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public IUser create(UserDto userDto) {
        UserDocument userDocument = new UserDocument(userDto);
        return userRepository.save(userDocument);
    }

    @Override
    public IUser findById(String id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public List<IUser> all() {
        return new ArrayList<>(userRepository.findAll());
    }

    @Override
    public boolean deleteById(String id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public IUser update(UserDto userDto, String id) {
        Optional<UserDocument> userDocumentOptional = userRepository.findById(id);
        if (userDocumentOptional.isPresent()) {
            UserDocument userDocument = userDocumentOptional.get();
            userDocument.update(userDto);
            return userRepository.save(userDocument);
        }
        return null;
    }

    @Override
    public List<IUser> findUsersWithNameOrLastNameLike(String queryText) {
        return userRepository.findByNameLikeOrLastNameLike(queryText, queryText);
    }

    @Override
    public List<IUser> findUsersCreatedAfter(Date startDate) {
        return userRepository.findByCreatedAtAfter(startDate);
    }
}
