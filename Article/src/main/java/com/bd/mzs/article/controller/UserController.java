package com.bd.mzs.article.controller;

import com.bd.mzs.article.controller.dto.UserDTO;
import com.bd.mzs.article.entity.User;
import com.bd.mzs.article.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@RestController
@Slf4j
public class UserController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("addUser")
    public ResponseEntity<User> addUser(@Valid @RequestBody UserDTO userDTO) {
        return ResponseEntity.ok(userService.saveUser(userDTO));
    }

    @GetMapping("getUsers")
    public Page<User> list(@RequestParam(name = "page", defaultValue = "0") int page,
                           @RequestParam(name = "size", defaultValue = "10") int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<User> pageResult = userService.getUsersPage(pageRequest);
        List<User> users = pageResult
                .stream()
                .collect(toList());
        return new PageImpl<>(users, pageRequest, pageResult.getTotalElements());
    }

    @DeleteMapping("deleteUser/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable int id){

        Optional<User> user = userService.getById(id);

        if(!user.isPresent()){
            log.error("User with id" + id + " does not exist.");
            return ResponseEntity.notFound().build();
        }
        userService.deleteUser(id);
        return ResponseEntity.ok(user.get());
    }
    @GetMapping("getUserById/{userId}")
    public ResponseEntity<User> userById(@PathVariable  int userId){

        Optional<User> user = userService.getById(userId);

        if(!user.isPresent()){
            log.error("User with id" + userId + " does not exist.");
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user.get());
    }
}
