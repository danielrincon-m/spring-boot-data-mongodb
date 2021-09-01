package org.ada.school.controller;

import org.ada.school.dto.UserDto;
import org.ada.school.model.IUser;
import org.ada.school.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(@Autowired UserService userService) {
        this.userService = userService;
    }


    @GetMapping
    public ResponseEntity<List<IUser>> all() {
        return ResponseEntity.ok(userService.all());
    }

    @GetMapping("/{id}")
    public ResponseEntity<IUser> findById(@PathVariable String id) {
        return ResponseEntity.ok(userService.findById(id));
    }

    @PostMapping
    public ResponseEntity<IUser> create(@RequestBody UserDto userDto) {
        return ResponseEntity.ok(userService.create(userDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<IUser> update(@RequestBody UserDto userDto, @PathVariable String id) {
        return ResponseEntity.ok(userService.update(userDto, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable String id) {
        return ResponseEntity.ok(userService.deleteById(id));
    }

    @GetMapping("/like/{nameLike}")
    public ResponseEntity<List<IUser>> findByNameOrLastNameLike(@PathVariable String nameLike) {
        return ResponseEntity.ok(userService.findUsersWithNameOrLastNameLike(nameLike));
    }

    @GetMapping("/after")
    public ResponseEntity<List<IUser>> createdAfter(@RequestParam String date) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return ResponseEntity.ok(userService.findUsersCreatedAfter(formatter.parse(date)));
    }
}
