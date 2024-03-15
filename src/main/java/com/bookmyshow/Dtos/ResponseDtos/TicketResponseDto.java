package com.bookmyshow.Dtos.ResponseDtos;


import lombok.*;

import java.time.LocalTime;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class  TicketResponseDto {

    private String responseMessage;

    private LocalTime showTime;
    private Date showDate;

    private String movieName;

    private String theaterName;

    private String bookedSeats;

    private String location;

    private int totalPrice;


}
