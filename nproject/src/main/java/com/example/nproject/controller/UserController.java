package com.example.nproject.controller;

import com.example.nproject.dto.UserDto;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/metasystem/users")
public class UserController {

    private final Map<Long, UserDto> userMap = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    @PostMapping
    public UserDto createUser(@RequestBody UserDto userDto) {
        Long id = idGenerator.getAndIncrement();
        userDto.setId(id);
        userMap.put(id, userDto);
        return userDto;
    }

    @GetMapping
    public List<UserDto> getAllUsers() {
        return new ArrayList<>(userMap.values());
    }

    @GetMapping("/{id}")
    public UserDto getUser(@PathVariable Long id) {
        return userMap.get(id);
    }

    @PutMapping("/{id}")
    public UserDto updateUser(@PathVariable Long id, @RequestBody UserDto userDto) {
        if (userMap.containsKey(id)) {
            userDto.setId(id);
            userMap.put(id, userDto);
            return userDto;
        }
        return null; // Or throw exception
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userMap.remove(id);
    }
}
