package com.bookmyshow.Transformers;

import com.bookmyshow.Dtos.RequestDto.AddUserDto;
import com.bookmyshow.Dtos.ResponseDtos.UserResponseDto;
import com.bookmyshow.Models.User;

public class UserTransformer {
    public static User convertDtoToEntity(AddUserDto userDto){

        User userObj = User.builder().age(userDto.getAge()).email(userDto.getEmailId()).mobNo(userDto.getMobNo())
                .name(userDto.getName()).build();
        return userObj;
    }

    public static UserResponseDto convertEntityToDto(User user){

        UserResponseDto userResponseDto = UserResponseDto.builder().age(user.getAge()).name(user.getName())
                .mobNo(user.getMobNo()).build();
        return userResponseDto;
    }
}
