package com.bookmyshow.Dtos.ResponseDtos;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserResponseDto {
    private String name;
    private int age;
    private String mobNo;
    private String statusCode;
    private String statusMessage;

}
