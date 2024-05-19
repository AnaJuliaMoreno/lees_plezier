package com.example.leesplezier.services;

import com.example.leesplezier.dtos.UserDto;
import com.example.leesplezier.exceptions.RecordNotFoundException;
import com.example.leesplezier.models.Role;
import com.example.leesplezier.models.User;
import com.example.leesplezier.repositories.UserRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    public List<UserDto> getAllUsers() {
        List<UserDto> collection = new ArrayList<>();
        List<User> list = userRepository.findAll();
        for (User user : list) {
            collection.add(transferToDto(user));
        }
        return collection;
    }

    public UserDto getUser(String username) {
        UserDto dto = new UserDto();
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isPresent()) {
            dto = transferToDto(user.get());
        } else {
            throw new UsernameNotFoundException(username);
        }
        return dto;
    }

    public boolean userExists(String username) {
        return userRepository.existsById(username);
    }

//    public boolean userIsVolunteer(String username) {
//        if (!userRepository.existsById(username)) throw new RecordNotFoundException("No user found");
//        var optionalUser = userRepository.findById(username).get();
//        for (Role r : optionalUser.getRole()) {
//            if (!r.getRole().equalsIgnoreCase("volunteer"))
//                throw new BadRequestException("Only volunteers can create a session");
//
//        }
//        return true;
//    }

    public String createUser(UserDto userDto) {

        User newUser = userRepository.save(transferToUser(userDto));

        return newUser.getUsername();
    }

    public void deleteUser(String username) {
        userRepository.deleteById(username);
    }

    public void updateUser(String username, UserDto newUser) {
        if (!userRepository.existsById(username)) throw new RecordNotFoundException("No user found");
        User user = userRepository.findById(username).get();
        user.setPassword(newUser.getPassword());
        userRepository.save(user);
    }

//    public Set<Role> getAllAuthorities(String username) {
//        if (!userRepository.existsById(username)) throw new UsernameNotFoundException(username);
//        User user = userRepository.findById(username).get();
//        UserDto userDto = transferToDto(user);
//        return userDto.getRole();
//    }

    //    public void addRole(String username, String role) {
//
//        if (!userRepository.existsById(username)) throw new UsernameNotFoundException(username);
//        User user = userRepository.findById(username).get();
//
//        user.addRole(new Role(username, role));
//        userRepository.save(user);
//    }
//
//    public void removeAuthority(String username, String role) {
//        if (!userRepository.existsById(username)) throw new UsernameNotFoundException(username);
//        User user = userRepository.findById(username).get();
//        Role1 authorityToRemove = user.getRole().stream().filter((a) -> a.getRole().equalsIgnoreCase(role)).findAny().get();
//        user.removeRole(authorityToRemove);
//        userRepository.save(user);
//    }
    public List<UserDto> getAllVolunteerUsers() {
        List<User> users = userRepository.findAll();
        List<UserDto> volunteerUserDtos = new ArrayList<>();

        for (User user : users) {
            if (Role.ROLE_VOLUNTEER == user.getRole()) { // Using enum comparison
                volunteerUserDtos.add(transferToDto(user));
            }
        }

        return volunteerUserDtos;
    }

    public List<UserDto> getAllParentUsers() {
        List<User> users = userRepository.findAll();
        List<UserDto> parentUserDtos = new ArrayList<>();

        for (User user : users) {
            if (user.getRole() == Role.ROLE_PARENT) { // Using enum comparison
                parentUserDtos.add(transferToDto(user));
            }
        }

        return parentUserDtos;
    }

    public static UserDto transferToDto(User user) {

        UserDto dto = new UserDto();

        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setLastName(user.getLastName());
        dto.setEmail(user.getEmail());
        dto.setPassword(user.getPassword());
        dto.setRole(user.getRole());



        return dto;
    }

    public User transferToUser(UserDto userDto) {

        User user = new User();

        user.setUsername(userDto.getUsername());
        user.setLastName(userDto.getLastName());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setEmail(userDto.getEmail());
        user.setRole(userDto.getRole());
    userDto.setId(user.getId());

        return user;
    }
}
