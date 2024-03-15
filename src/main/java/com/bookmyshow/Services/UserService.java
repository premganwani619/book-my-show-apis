package com.bookmyshow.Services;

import com.bookmyshow.Transformers.UserTransformer;
import com.bookmyshow.Dtos.RequestDto.AddUserDto;
import com.bookmyshow.Dtos.ResponseDtos.UserResponseDto;
import com.bookmyshow.Exception.NoUserFoundException;
import com.bookmyshow.Models.User;
import com.bookmyshow.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public String addUser(AddUserDto userDto)  {
        User user = UserTransformer.convertDtoToEntity(userDto);
        userRepository.save(user);
        return "User has been added successfully ";
    }

    public UserResponseDto getOldestUser()throws NoUserFoundException {
        //Prevent you from exposing the PK
        //Prevents Infinite recursion incase it occurs
        List<User> users = userRepository.findAll();
        Integer maxAge = 0;

        User userAns = null;

        for(User user: users)
        {
            if(user.getAge()>maxAge){
                maxAge = user.getAge();
                userAns = user;
            }
        }

        if(userAns==null){
            throw new NoUserFoundException("No user Found");
        }

        //We need to transform the UserEntity to the userResponse
        UserResponseDto userResponseDto = UserTransformer.convertEntityToDto(userAns);

        return userResponseDto;
    }

    public List<User> getAllUserGreaterThan(Integer age){
        List<User> users = userRepository.findUserWithAgeGreater(age);
        return users;
    }
}
